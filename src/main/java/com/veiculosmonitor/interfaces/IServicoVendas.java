package com.veiculosmonitor.interfaces;

public interface IServicoVendas {
    void venderVeiculo(int veiculoId, int compradorId);
    void cancelarVenda(int vendaId);
    void listarVendas();
}
