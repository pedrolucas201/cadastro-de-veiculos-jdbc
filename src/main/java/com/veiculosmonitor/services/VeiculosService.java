package com.veiculosmonitor.services;

import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.repositories.VeiculosRepositorioImpl;

import java.util.List;

public class VeiculosService {
    private final VeiculosRepositorioImpl repositorio = new VeiculosRepositorioImpl();

    public void salvarVeiculo(Veiculo veiculo) {
        repositorio.salvar(veiculo);
    }

    public List<Veiculo> listarVeiculos() {
        return repositorio.buscarTodos();
    }
}
