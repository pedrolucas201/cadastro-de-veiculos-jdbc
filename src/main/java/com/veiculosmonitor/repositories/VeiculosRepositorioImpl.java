package com.veiculosmonitor.repositories;

import com.veiculosmonitor.database.DatabaseConnection;
import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.interfaces.IVeiculosRepositorio;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculosRepositorioImpl implements IVeiculosRepositorio {

    @Override
    public void salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculos (placa, modelo, tipo) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getTipo());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Veiculo buscarPorId(int id) {
        String sql = "SELECT * FROM veiculos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setTipo(rs.getString("tipo"));
                return veiculo;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Veiculo> buscarTodos() {
        String sql = "SELECT * FROM veiculos";
        List<Veiculo> veiculos = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setId(rs.getInt("id"));
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return veiculos;
    }

    @Override
    public void atualizar(Veiculo veiculo) {
        String sql = "UPDATE veiculos SET placa = ?, modelo = ?, tipo = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getTipo());
            stmt.setInt(4, veiculo.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void excluir(int id) {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
