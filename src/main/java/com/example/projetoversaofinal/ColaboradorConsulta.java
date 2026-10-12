package com.example.projetoversaofinal;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ColaboradorConsulta {

    public void start(Stage stage) {
        Label lblTitulo = new Label("Consultar Colaboradores");

        ListView<Colaborador> lista = new ListView<>();
        lista.setItems(FXCollections.observableArrayList(new ColaboradorRepositorio().listar()));

        Button btnVoltar = new Button("Voltar");
        btnVoltar.setOnAction(e -> new Programa().start(stage));

        VBox vbox = new VBox(12, lblTitulo, lista, btnVoltar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        stage.setScene(new Scene(vbox, 400, 500));
    }
}
