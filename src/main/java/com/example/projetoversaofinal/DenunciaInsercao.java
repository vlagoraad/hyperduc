package com.example.projetoversaofinal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DenunciaInsercao {
    private final Stage stage;
    private final DenunciaRepositorio repositorio = new DenunciaRepositorio();

    public DenunciaInsercao(Stage stage) {
        this.stage = stage;
    }

    public Scene criarCena() {
        Label lblTitulo = new Label("Registrar Denúncia");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField txtId = new TextField();
        txtId.setPromptText("Número da denúncia");
        TextField txtMotivo = new TextField();
        txtMotivo.setPromptText("Ex.: plágio, conduta inadequada, spam");
        TextField txtAlvo = new TextField();
        txtAlvo.setPromptText("Ex.: Projeto 3, Atividade 12, Colaborador C01");
        TextArea txtDescricao = new TextArea();
        txtDescricao.setPromptText("Descreva o ocorrido");
        txtDescricao.setPrefRowCount(4);
        txtDescricao.setWrapText(true);

        Button btnSalvar = new Button("Registrar");
        Button btnVoltar = new Button("Voltar");
        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnVoltar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");

        btnSalvar.setOnAction(e -> {
            try {
                int id = Integer.parseInt(txtId.getText().trim());
                Denuncia d = new Denuncia(id, txtMotivo.getText().trim(), txtDescricao.getText().trim(),
                        txtAlvo.getText().trim(), LocalDate.now());
                repositorio.adicionar(d); // valida motivo e ID duplicado
                mostrarAlerta("Sucesso", "Denúncia registrada com status \"Aberta\".", Alert.AlertType.INFORMATION);
                txtId.clear();
                txtMotivo.clear();
                txtAlvo.clear();
                txtDescricao.clear();
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "O número deve ser inteiro!", Alert.AlertType.ERROR);
            } catch (IllegalArgumentException ex) {
                mostrarAlerta("Erro", ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        btnVoltar.setOnAction(e -> {
            stage.setScene(new Programa().criarMenuPrincipal(stage));
            stage.setTitle("HYPERDUC! - Sistema de Gerenciamento");
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25));
        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(new Label("Número:"), 0, 1);
        grid.add(txtId, 1, 1);
        grid.add(new Label("Motivo:"), 0, 2);
        grid.add(txtMotivo, 1, 2);
        grid.add(new Label("Alvo:"), 0, 3);
        grid.add(txtAlvo, 1, 3);
        grid.add(new Label("Descrição:"), 0, 4);
        grid.add(txtDescricao, 1, 4);
        grid.add(new HBox(10, btnSalvar, btnVoltar), 0, 5, 2, 1);

        return new Scene(grid, 600, 450);
    }

    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
