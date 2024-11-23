package com.veiculosmonitor.interfaces;

import com.veiculosmonitor.entities.Veiculo;
import java.util.List;

public interface IVeiculosRepositorio {
    void salvar(Veiculo veiculo);
    Veiculo buscarPorId(int id);
    List<Veiculo> buscarTodos();
    void atualizar(Veiculo veiculo);
    void excluir(int id);
}
