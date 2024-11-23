package com.veiculosmonitor.entities;

public class Veiculo {
    private int id;
    private String placa;
    private String modelo;
    private String tipo;
    private String marca;

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPlaca() { return placa; }
    public void setPlaca(String placa) { this.placa = placa; }

    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMarca() { return marca; }
    public void setMarca(String marca) { this.marca = marca; }

    @Override
    public String toString() {
        return "\nVeiculo: " +
                "\nPlaca: " + placa +
                "\nModelo:" + modelo +
                "\nMarca: " + marca +
                "\nTipo: " + tipo;
    }
}
