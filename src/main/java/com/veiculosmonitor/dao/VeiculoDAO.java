package com.veiculosmonitor.dao;

import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.database.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VeiculoDAO {
    public void salvar(Veiculo veiculo) throws SQLException {
        String sql = "INSERT INTO veiculos (placa, modelo, tipo) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getTipo());
            stmt.executeUpdate();
        }
    }

    public List<Veiculo> buscarTodos() throws SQLException {
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
        }
        return veiculos;
    }

    public void atualizar(Veiculo veiculo) throws SQLException {
        String sql = "UPDATE veiculos SET placa = ?, modelo = ?, tipo = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, veiculo.getPlaca());
            stmt.setString(2, veiculo.getModelo());
            stmt.setString(3, veiculo.getTipo());
            stmt.setInt(4, veiculo.getId());
            stmt.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM veiculos WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
