package com.veiculosmonitor.services;

import com.veiculosmonitor.database.DatabaseConnection;
import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.interfaces.IServicoVendas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VendasService implements IServicoVendas {

    @Override
    public void venderVeiculo(int veiculoId, int compradorId) {
        String vendaSql = "INSERT INTO vendas (veiculo_id, comprador_id, data_venda) VALUES (?, ?, NOW())";
        String updateVeiculoSql = "UPDATE veiculos SET vendido = TRUE WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement vendaStmt = conn.prepareStatement(vendaSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateVeiculoSql)) {

            // Registrar a venda
            vendaStmt.setInt(1, veiculoId);
            vendaStmt.setInt(2, compradorId);
            vendaStmt.executeUpdate();

            // Atualizar o status do veículo para "vendido"
            updateStmt.setInt(1, veiculoId);
            updateStmt.executeUpdate();

            System.out.println("Venda registrada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao registrar a venda: " + e.getMessage());
        }
    }

    @Override
    public void cancelarVenda(int vendaId) {
        String vendaSql = "DELETE FROM vendas WHERE id = ?";
        String updateVeiculoSql = "UPDATE veiculos SET vendido = FALSE WHERE id = (SELECT veiculo_id FROM vendas WHERE id = ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement vendaStmt = conn.prepareStatement(vendaSql);
             PreparedStatement updateStmt = conn.prepareStatement(updateVeiculoSql)) {

            // Atualizar o status do veículo para "não vendido"
            updateStmt.setInt(1, vendaId);
            updateStmt.executeUpdate();

            // Remover a venda
            vendaStmt.setInt(1, vendaId);
            vendaStmt.executeUpdate();

            System.out.println("Venda cancelada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao cancelar a venda: " + e.getMessage());
        }
    }

    @Override
    public void listarVendas() {
        String sql = "SELECT v.id, v.veiculo_id, v.comprador_id, v.data_venda, ve.modelo, ve.placa " +
                "FROM vendas v INNER JOIN veiculos ve ON v.veiculo_id = ve.id";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("Lista de vendas:");
            while (rs.next()) {
                int id = rs.getInt("id");
                int veiculoId = rs.getInt("veiculo_id");
                int compradorId = rs.getInt("comprador_id");
                String dataVenda = rs.getString("data_venda");
                String modelo = rs.getString("modelo");
                String placa = rs.getString("placa");

                System.out.printf("Venda ID: %d, Veículo ID: %d (%s - %s), Comprador ID: %d, Data: %s%n",
                        id, veiculoId, modelo, placa, compradorId, dataVenda);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao listar as vendas: " + e.getMessage());
        }
    }
}
