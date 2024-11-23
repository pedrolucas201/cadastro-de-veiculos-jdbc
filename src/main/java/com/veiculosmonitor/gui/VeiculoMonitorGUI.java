package com.veiculosmonitor.gui;

import com.veiculosmonitor.entities.Veiculo;
import com.veiculosmonitor.services.VeiculosImplRepositorio;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.nio.charset.Charset;
import java.util.List;

public class VeiculoMonitorGUI extends Application {

    private VeiculosImplRepositorio veiculosRepositorio = new VeiculosImplRepositorio();
    private TextArea logArea;

    @Override
    public void start(Stage primaryStage) {
        // Configuração UTF-8
        System.setProperty("file.encoding", "UTF-8");
        System.out.println("Default Charset = " + Charset.defaultCharset());

        primaryStage.setTitle("Monitor de Veículos");

        // Campos de entrada
        TextField placaField = new TextField();
        placaField.setPromptText("Placa");

        TextField modeloField = new TextField();
        modeloField.setPromptText("Modelo");

        TextField marcaField = new TextField();
        marcaField.setPromptText("Marca");

        TextField tipoField = new TextField();
        tipoField.setPromptText("Tipo (Carro/Moto/Caminhão)");

        // Botões de ação
        Button addButton = new Button("Adicionar Veículo");
        Button updateButton = new Button("Atualizar Veículo");
        Button deleteButton = new Button("Excluir Veículo");
        Button listButton = new Button("Listar Veículos");

        // Área de log
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setPrefHeight(200);

        // Ações dos botões
        addButton.setOnAction(e -> adicionarVeiculo(placaField, modeloField, marcaField, tipoField));
        updateButton.setOnAction(e -> atualizarVeiculo(placaField, modeloField, marcaField, tipoField));
        deleteButton.setOnAction(e -> excluirVeiculo(placaField));
        listButton.setOnAction(e -> listarVeiculos());

        // Layout dos campos
        GridPane formPane = new GridPane();
        formPane.setPadding(new Insets(10));
        formPane.setHgap(10);
        formPane.setVgap(10);
        formPane.add(new Label("Placa:"), 0, 0);
        formPane.add(placaField, 1, 0);
        formPane.add(new Label("Modelo:"), 0, 1);
        formPane.add(modeloField, 1, 1);
        formPane.add(new Label("Marca:"), 0, 2);
        formPane.add(marcaField, 1, 2);
        formPane.add(new Label("Tipo:"), 0, 3);
        formPane.add(tipoField, 1, 3);

        VBox mainLayout = new VBox(10);
        mainLayout.setPadding(new Insets(10));
        mainLayout.getChildren().addAll(formPane, addButton, updateButton, deleteButton, listButton, logArea);

        // Cena principal
        Scene scene = new Scene(mainLayout, 450, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void adicionarVeiculo(TextField placaField, TextField modeloField, TextField marcaField, TextField tipoField) {
        try {
            String placa = placaField.getText();
            String modelo = modeloField.getText();
            String marca = marcaField.getText();
            String tipo = tipoField.getText();

            if (placa.isEmpty() || modelo.isEmpty() || marca.isEmpty() || tipo.isEmpty()) {
                log("Todos os campos são obrigatórios para adicionar um veículo!");
                return;
            }

            Veiculo veiculo = new Veiculo();
            veiculo.setPlaca(placa);
            veiculo.setModelo(modelo);
            veiculo.setMarca(marca);
            veiculo.setTipo(tipo);

            veiculosRepositorio.salvar(veiculo);
            log("Veículo adicionado: " + veiculo);
            limparCampos(placaField, modeloField, marcaField, tipoField);
        } catch (Exception e) {
            log("Erro ao adicionar veículo: " + e.getMessage());
        }
    }

    private void atualizarVeiculo(TextField placaField, TextField modeloField, TextField marcaField, TextField tipoField) {
        try {
            String placa = placaField.getText();

            if (placa.isEmpty()) {
                log("Placa é obrigatória para atualizar um veículo!");
                return;
            }

            Veiculo veiculo = veiculosRepositorio.buscarPorPlaca(placa);
            if (veiculo == null) {
                log("Veículo com placa " + placa + " não encontrado!");
                return;
            }

            veiculo.setModelo(modeloField.getText());
            veiculo.setMarca(marcaField.getText());
            veiculo.setTipo(tipoField.getText());

            veiculosRepositorio.atualizar(veiculo);
            log("Veículo atualizado: " + veiculo);
            limparCampos(placaField, modeloField, marcaField, tipoField);
        } catch (Exception e) {
            log("Erro ao atualizar veículo: " + e.getMessage());
        }
    }

    private void excluirVeiculo(TextField placaField) {
        try {
            String placa = placaField.getText();

            if (placa.isEmpty()) {
                log("Placa é obrigatória para excluir um veículo!");
                return;
            }

            veiculosRepositorio.excluirPorPlaca(placa);
            log("Veículo com placa " + placa + " excluído.");
            placaField.clear();
        } catch (Exception e) {
            log("Erro ao excluir veículo: " + e.getMessage());
        }
    }

    private void listarVeiculos() {
        try {
            List<Veiculo> veiculos = veiculosRepositorio.buscarTodos();
            if (veiculos.isEmpty()) {
                log("Nenhum veículo cadastrado.");
            } else {
                log("Lista de Veículos:");
                for (Veiculo v : veiculos) {
                    log(v.toString());
                }
            }
        } catch (Exception e) {
            log("Erro ao listar veículos: " + e.getMessage());
        }
    }

    private void log(String mensagem) {
        logArea.appendText(mensagem + "\n");
    }

    private void limparCampos(TextField... campos) {
        for (TextField campo : campos) {
            campo.clear();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
