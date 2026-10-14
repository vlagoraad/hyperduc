package com.example.projetoversaofinal;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AtividadeInsercao {
    private Stage stage;
    private final AtividadeRepositorio repositorio = new AtividadeRepositorio();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public AtividadeInsercao(Stage stage) {
        this.stage = stage;
    }

    public Scene criarCena() {
        Label lblTitulo = new Label("Inserir Nova Atividade");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label lblId = new Label("ID:");
        TextField txtId = new TextField();
        txtId.setPromptText("Digite o ID da atividade");

        Label lblTituloProjeto = new Label("Título:");
        TextField txtTitulo = new TextField();
        txtTitulo.setPromptText("Digite o título da atividade");

        Label lblDescricao = new Label("Descrição:");
        TextArea txtDescricao = new TextArea();
        txtDescricao.setPromptText("Digite a descrição da atividade");
        txtDescricao.setPrefRowCount(4);
        txtDescricao.setWrapText(true);

        Label lblData = new Label("Data de Criação:");
        TextField txtData = new TextField();
        txtData.setPromptText("dd/MM/yyyy");

        
        Label lblStatus = new Label("Status inicial:");
        Label lblStatusValor = new Label("Aberta");


        Button btnDataAtual = new Button("Usar Data Atual");
        btnDataAtual.setOnAction(e -> {
            txtData.setText(LocalDate.now().format(formatter));
        });

        Button btnSalvar = new Button("Salvar");
        Button btnLimpar = new Button("Limpar");
        Button btnVoltar = new Button("Voltar");

        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnLimpar.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-weight: bold;");
        btnVoltar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");

        btnSalvar.setOnAction(e -> {
            try {
                if (txtId.getText().trim().isEmpty()) {
                    mostrarAlerta("Erro", "O campo ID não pode estar vazio!", Alert.AlertType.ERROR);
                    return;
                }

                int id = Integer.parseInt(txtId.getText().trim());

                if (txtTitulo.getText().trim().isEmpty()) {
                    mostrarAlerta("Erro", "O campo Título não pode estar vazio!", Alert.AlertType.ERROR);
                    return;
                }

                if (txtDescricao.getText().trim().isEmpty()) {
                    mostrarAlerta("Erro", "O campo Descrição não pode estar vazio!", Alert.AlertType.ERROR);
                    return;
                }

                if (txtData.getText().trim().isEmpty()) {
                    mostrarAlerta("Erro", "O campo Data não pode estar vazio!", Alert.AlertType.ERROR);
                    return;
                }

                LocalDate data = LocalDate.parse(txtData.getText().trim(), formatter);

                Atividade novaAtividade = new Atividade(id, txtTitulo.getText().trim(), txtDescricao.getText().trim(), data);
                repositorio.adicionar(novaAtividade);

                mostrarAlerta("Sucesso", "Atividade adicionada com sucesso!", Alert.AlertType.INFORMATION);

                limparCampos(txtId, txtTitulo, txtDescricao, txtData);

            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "ID deve ser um número inteiro válido!", Alert.AlertType.ERROR);
            } catch (DateTimeParseException ex) {
                mostrarAlerta("Erro", "Data inválida! Use o formato dd/MM/yyyy", Alert.AlertType.ERROR);
            } catch (IllegalArgumentException ex) {
                mostrarAlerta("Erro", ex.getMessage(), Alert.AlertType.ERROR);
            } catch (Exception ex) {
                mostrarAlerta("Erro", "Erro ao salvar atividade: " + ex.getMessage(), Alert.AlertType.ERROR);
            }
        });

        btnLimpar.setOnAction(e -> limparCampos(txtId, txtTitulo, txtDescricao, txtData));

        btnVoltar.setOnAction(e -> {
            Programa app = new Programa();
            stage.setScene(app.criarMenuPrincipal(stage));
            stage.setTitle("HYPERDUC! - Sistema de Gerenciamento");
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(lblId, 0, 1);
        grid.add(txtId, 1, 1);
        grid.add(lblTituloProjeto, 0, 2);
        grid.add(txtTitulo, 1, 2);
        grid.add(lblDescricao, 0, 3);
        grid.add(txtDescricao, 1, 3);
        grid.add(lblData, 0, 4);
        grid.add(lblStatus, 0, 5);
        grid.add(lblStatusValor, 1, 5);
        HBox hboxData = new HBox(10);
        hboxData.getChildren().addAll(txtData, btnDataAtual);
        grid.add(hboxData, 1, 4);

        HBox hboxBotoes = new HBox(10);
        hboxBotoes.setAlignment(Pos.CENTER);
        hboxBotoes.getChildren().addAll(btnSalvar, btnLimpar, btnVoltar);
        grid.add(hboxBotoes, 0, 6, 2, 1);

        Scene scene = new Scene(grid, 600, 500);
        return scene;
    }

    private void limparCampos(TextField txtId, TextField txtTitulo, TextArea txtDescricao, TextField txtData) {
        txtId.clear();
        txtTitulo.clear();
        txtDescricao.clear();
        txtData.clear();
    }

    private void mostrarAlerta(String titulo, String mensagem, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensagem);
        alert.showAndWait();
    }
}
