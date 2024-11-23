package com.veiculosmonitor;

import com.veiculosmonitor.gui.VeiculoMonitorGUI;
import javafx.application.Application;

import java.nio.charset.Charset;

public class Main {
    public static void main(String[] args) {
        // Define o encoding UTF-8 para evitar problemas com caracteres especiais
        System.setProperty("file.encoding", "UTF-8");
        Charset charset = Charset.defaultCharset();
        System.out.println("Default Charset = " + charset); // Log do charset para verificar

        // Inicia a aplicação JavaFX
        Application.launch(VeiculoMonitorGUI.class, args);
    }
}
