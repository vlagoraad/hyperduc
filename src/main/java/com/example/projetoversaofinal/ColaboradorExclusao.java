package com.example.projetoversaofinal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ColaboradorExclusao {

    public void start(Stage stage) {
        Label lblTitulo = new Label("Excluir Colaborador");

        TextField txtId = new TextField();
        txtId.setPromptText("ID a excluir");

        Button btnExcluir = new Button("Excluir");
        Button btnVoltar = new Button("Voltar");

        btnExcluir.setOnAction(e -> {
            boolean removido = new ColaboradorRepositorio().remover(txtId.getText().trim());

            Alert alert;

            if (removido) {
                alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("Colaborador removido com sucesso.");
            } else {
                alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Nenhum colaborador encontrado com esse ID.");
            }

            alert.show();
        });

        btnVoltar.setOnAction(e -> new Programa().start(stage));

        VBox vbox = new VBox(12, lblTitulo, txtId, btnExcluir, btnVoltar);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));

        stage.setScene(new Scene(vbox, 400, 300));
    }
}
