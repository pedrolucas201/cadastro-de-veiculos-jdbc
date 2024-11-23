package com.veiculosmonitor.services;

import com.veiculosmonitor.entities.Veiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculosImplRepositorio {

    // URL do banco de dados H2 em modo servidor
    private static final String URL = "jdbc:h2:tcp://localhost/~/test";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public void salvar(Veiculo veiculo) {
        String sql = "INSERT INTO veiculos (placa, modelo, marca, tipo) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getMarca());
            stmt.setString(4, veiculo.getTipo());
            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("Veículo salvo com sucesso!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Veiculo> buscarTodos() {
        List<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT * FROM veiculos";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setTipo(rs.getString("tipo"));
                veiculos.add(veiculo);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculos;
    }

    public Veiculo buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM veiculos WHERE placa = ?";
        Veiculo veiculo = null;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                veiculo = new Veiculo();
                veiculo.setPlaca(rs.getString("placa"));
                veiculo.setModelo(rs.getString("modelo"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setTipo(rs.getString("tipo"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return veiculo;
    }

    public void atualizar(Veiculo veiculo) {
        String sql = "UPDATE veiculos SET modelo = ?, marca = ?, tipo = ? WHERE placa = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, veiculo.getModelo());
            stmt.setString(2, veiculo.getMarca());
            stmt.setString(3, veiculo.getTipo());
            stmt.setString(4, veiculo.getPlaca());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void excluirPorPlaca(String placa) {
        String sql = "DELETE FROM veiculos WHERE placa = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, placa);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
