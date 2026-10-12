package com.example.projetoversaofinal;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Programa extends Application {
    private Stage primaryStage;

    @Override
    public void start(Stage stage) {
        this.primaryStage = stage;
        primaryStage.setScene(criarMenuPrincipal(primaryStage));
        primaryStage.setTitle("HYPERDUC! - Sistema de Gerenciamento");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public Scene criarMenuPrincipal(Stage stage) {
        Label lblTitulo = new Label("HYPERDUC!");
        lblTitulo.setStyle("-fx-font-size: 32px; -fx-font-weight: bold; -fx-text-fill: #2196F3;");

        Label lblSubtitulo = new Label("Plataforma de Gestão de Projetos Colaborativos");
        lblSubtitulo.setStyle("-fx-font-size: 14px; -fx-text-fill: #666666;");

        Separator sep1 = new Separator();

        Label lblSecaoProjetos = new Label("📁 GERENCIAR PROJETOS");
        lblSecaoProjetos.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Button btnInserirProjeto = new Button("➕ Inserir Projeto");
        Button btnConsultarProjeto = new Button("🔍 Consultar Projetos");
        Button btnAtualizarProjeto = new Button("✏️ Atualizar Projeto");
        Button btnExcluirProjeto = new Button("🗑️ Excluir Projeto");

        Separator sep2 = new Separator();

        Label lblSecaoUsuarios = new Label("👥 GERENCIAR USUÁRIOS");
        lblSecaoUsuarios.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Button btnInserirUsuario = new Button("➕ Inserir Usuário");
        Button btnConsultarUsuario = new Button("🔍 Consultar Usuários");
        Button btnAtualizarUsuario = new Button("✏️ Atualizar Usuário");
        Button btnExcluirUsuario = new Button("🗑️ Excluir Usuário");

        Separator sep3 = new Separator();

        Label lblSecaoAtividades = new Label("✅ GERENCIAR ATIVIDADES");
        lblSecaoAtividades.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Button btnInserirAtividade = new Button("➕ Inserir Atividade");
        Button btnConsultarAtividade = new Button("🔍 Consultar Atividades");
        Button btnAtualizarAtividade = new Button("✏️ Atualizar Atividade");
        Button btnExcluirAtividade = new Button("🗑️ Excluir Atividade");

        Separator sep4 = new Separator();

        Label lblSecaoDenuncias = new Label("🚩 GERENCIAR DENÚNCIAS");
        lblSecaoDenuncias.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #333333;");

        Button btnInserirDenuncia = new Button("➕ Registrar Denúncia");
        Button btnConsultarDenuncia = new Button("🔍 Consultar Denúncias");
        Button btnAtualizarDenuncia = new Button("⚖️ Analisar Denúncia");
        Button btnExcluirDenuncia = new Button("🗑️ Excluir Denúncia");

        for (Button b : new Button[]{btnInserirDenuncia, btnConsultarDenuncia, btnAtualizarDenuncia, btnExcluirDenuncia}) {
            b.setPrefSize(350, 45);
            b.setStyle("-fx-background-color: #E91E63; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        }

        btnInserirDenuncia.setOnAction(e -> trocarTela(stage, new DenunciaInsercao(stage).criarCena(), "Registrar Denúncia"));
        btnConsultarDenuncia.setOnAction(e -> trocarTela(stage, new DenunciaConsulta(stage).criarCena(), "Consultar Denúncias"));
        btnAtualizarDenuncia.setOnAction(e -> trocarTela(stage, new DenunciaAtualizacao(stage).criarCena(), "Analisar Denúncia"));
        btnExcluirDenuncia.setOnAction(e -> trocarTela(stage, new DenunciaExclusao(stage).criarCena(), "Excluir Denúncia"));

        Separator sep5 = new Separator();

        Button btnSair = new Button("❌ Sair do Sistema");

        double larguraBotao = 350;
        double alturaBotao = 45;

        btnInserirProjeto.setPrefWidth(larguraBotao);
        btnConsultarProjeto.setPrefWidth(larguraBotao);
        btnAtualizarProjeto.setPrefWidth(larguraBotao);
        btnExcluirProjeto.setPrefWidth(larguraBotao);

        btnInserirProjeto.setPrefHeight(alturaBotao);
        btnConsultarProjeto.setPrefHeight(alturaBotao);
        btnAtualizarProjeto.setPrefHeight(alturaBotao);
        btnExcluirProjeto.setPrefHeight(alturaBotao);

        btnInserirUsuario.setPrefWidth(larguraBotao);
        btnConsultarUsuario.setPrefWidth(larguraBotao);
        btnAtualizarUsuario.setPrefWidth(larguraBotao);
        btnExcluirUsuario.setPrefWidth(larguraBotao);

        btnInserirUsuario.setPrefHeight(alturaBotao);
        btnConsultarUsuario.setPrefHeight(alturaBotao);
        btnAtualizarUsuario.setPrefHeight(alturaBotao);
        btnExcluirUsuario.setPrefHeight(alturaBotao);

        btnInserirAtividade.setPrefWidth(larguraBotao);
        btnConsultarAtividade.setPrefWidth(larguraBotao);
        btnAtualizarAtividade.setPrefWidth(larguraBotao);
        btnExcluirAtividade.setPrefWidth(larguraBotao);

        btnInserirAtividade.setPrefHeight(alturaBotao);
        btnConsultarAtividade.setPrefHeight(alturaBotao);
        btnAtualizarAtividade.setPrefHeight(alturaBotao);
        btnExcluirAtividade.setPrefHeight(alturaBotao);

        btnSair.setPrefWidth(larguraBotao);
        btnSair.setPrefHeight(alturaBotao);

        btnInserirProjeto.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnConsultarProjeto.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnAtualizarProjeto.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnExcluirProjeto.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        btnInserirUsuario.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnConsultarUsuario.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnAtualizarUsuario.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnExcluirUsuario.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        btnInserirAtividade.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnConsultarAtividade.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnAtualizarAtividade.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");
        btnExcluirAtividade.setStyle("-fx-background-color: #9C27B0; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        btnSair.setStyle("-fx-background-color: #9E9E9E; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold;");

        btnInserirProjeto.setOnAction(e -> abrirProjetoInsercao(stage));
        btnConsultarProjeto.setOnAction(e -> abrirProjetoConsulta(stage));
        btnAtualizarProjeto.setOnAction(e -> abrirProjetoAtualizacao(stage));
        btnExcluirProjeto.setOnAction(e -> abrirProjetoExclusao(stage));
        
        btnInserirUsuario.setOnAction(e -> abrirColaboradorInsercao(stage));
        btnConsultarUsuario.setOnAction(e -> abrirColaboradorConsulta(stage));
        btnAtualizarUsuario.setOnAction(e -> abrirColaboradorAtualizacao(stage));
        btnExcluirUsuario.setOnAction(e -> abrirColaboradorExclusao(stage));

        btnInserirAtividade.setOnAction(e -> abrirAtividadeInsercao(stage));
        btnConsultarAtividade.setOnAction(e -> abrirAtividadeConsulta(stage));
        btnAtualizarAtividade.setOnAction(e -> abrirAtividadeAtualizacao(stage));
        btnExcluirAtividade.setOnAction(e -> abrirAtividadeExclusao(stage));

        btnSair.setOnAction(e -> stage.close());

        Label lblInfo = new Label("Desenvolvido por: Amanda Freire e Valentina Lago");
        lblInfo.setStyle("-fx-font-size: 11px; -fx-text-fill: #999999; -fx-font-style: italic;");

        Label lblAno = new Label("PUC-PR - Engenharia de Software - 2026");
        lblAno.setStyle("-fx-font-size: 11px; -fx-text-fill: #999999; -fx-font-style: italic;");

        VBox vbox = new VBox(12);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));
        vbox.getChildren().addAll(
                lblTitulo,
                lblSubtitulo,
                sep1,

                lblSecaoProjetos,
                btnInserirProjeto,
                btnConsultarProjeto,
                btnAtualizarProjeto,
                btnExcluirProjeto,
                sep2,

                lblSecaoUsuarios,
                btnInserirUsuario,
                btnConsultarUsuario,
                btnAtualizarUsuario,
                btnExcluirUsuario,
                sep3,

                lblSecaoAtividades,
                btnInserirAtividade,
                btnConsultarAtividade,
                btnAtualizarAtividade,
                btnExcluirAtividade,
                sep4,

                lblSecaoDenuncias,
                btnInserirDenuncia,
                btnConsultarDenuncia,
                btnAtualizarDenuncia,
                btnExcluirDenuncia,
                sep5,

                btnSair,
                new Label(""),
                lblInfo,
                lblAno
        );

        // menu ficou mais alto com a seção de denúncias: usa rolagem
        ScrollPane scroll = new ScrollPane(vbox);
        scroll.setFitToWidth(true);
        return new Scene(scroll, 470, 850);
    }

    private void trocarTela(Stage stage, Scene cena, String titulo) {
        stage.setScene(cena);
        stage.setTitle(titulo + " - HYPERDUC!");
    }

    private void abrirProjetoInsercao(Stage stage) {
        ProjetoInsercao tela = new ProjetoInsercao(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Inserir Projeto - HYPERDUC!");
    }

    private void abrirProjetoConsulta(Stage stage) {
        ProjetoConsulta tela = new ProjetoConsulta(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Consultar Projetos - HYPERDUC!");
    }

    private void abrirProjetoAtualizacao(Stage stage) {
        ProjetoAtualizacao tela = new ProjetoAtualizacao(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Atualizar Projeto - HYPERDUC!");
    }

    private void abrirProjetoExclusao(Stage stage) {
        ProjetoExclusao tela = new ProjetoExclusao(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Excluir Projeto - HYPERDUC!");
    }

    private void abrirColaboradorInsercao(Stage stage) {
        ColaboradorInsercao tela = new ColaboradorInsercao();
        tela.start(stage);
    }

    private void abrirColaboradorConsulta(Stage stage) {
        ColaboradorConsulta tela = new ColaboradorConsulta();
        tela.start(stage);
    }

    private void abrirColaboradorAtualizacao(Stage stage) {
        ColaboradorAtualizacao tela = new ColaboradorAtualizacao();
        tela.start(stage);
    }

    private void abrirColaboradorExclusao(Stage stage) {
        ColaboradorExclusao tela = new ColaboradorExclusao();
        tela.start(stage);
    }

    // ================== ATIVIDADES ==================
    private void abrirAtividadeInsercao(Stage stage) {
        AtividadeInsercao tela = new AtividadeInsercao(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Inserir Atividade - HYPERDUC!");
    }

    private void abrirAtividadeConsulta(Stage stage) {
        AtividadeConsulta tela = new AtividadeConsulta(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Consultar Atividade - HYPERDUC!");
    }

    private void abrirAtividadeAtualizacao(Stage stage) {
        AtividadeAtualizacao tela = new AtividadeAtualizacao(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Atualizar Atividade - HYPERDUC!");
    }

    private void abrirAtividadeExclusao(Stage stage) {
        AtividadeExclusao tela = new AtividadeExclusao(stage);
        stage.setScene(tela.criarCena());
        stage.setTitle("Excluir Atividade - HYPERDUC!");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
