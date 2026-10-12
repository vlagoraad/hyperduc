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
import java.util.function.Consumer;

/**
 * Tela de atualização de atividades.
 *  - Edita título, descrição e data.
 *  - Muda o status apenas pelas transições do padrão STATE (botões).
 *  - Permite desfazer a última alteração com o padrão MEMENTO.
 */
public class AtividadeAtualizacao {

    // Caretaker do Memento: estático para o histórico sobreviver à troca de telas
    private static final Historico<Atividade.Memento> HISTORICO = new Historico<>();

    private final Stage stage;
    private final AtividadeRepositorio repositorio = new AtividadeRepositorio();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private Atividade atual; // atividade carregada na tela

    private TextField txtId;
    private TextField txtTitulo;
    private TextArea txtDescricao;
    private TextField txtData;
    private Label lblEstado;

    public AtividadeAtualizacao(Stage stage) {
        this.stage = stage;
    }

    public Scene criarCena() {
        Label lblTitulo = new Label("Atualizar Atividade");
        lblTitulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        TextField txtBuscaId = new TextField();
        txtBuscaId.setPromptText("Digite o ID para buscar");
        Button btnBuscar = new Button("Buscar");
        btnBuscar.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");

        txtId = new TextField();
        txtId.setEditable(false);
        txtId.setStyle("-fx-background-color: #f0f0f0;");
        txtTitulo = new TextField();
        txtDescricao = new TextArea();
        txtDescricao.setWrapText(true);
        txtDescricao.setPrefRowCount(4);
        txtData = new TextField();
        txtData.setPromptText("dd/MM/yyyy");
        lblEstado = new Label("-");
        lblEstado.setStyle("-fx-font-weight: bold; -fx-text-fill: #9C27B0;");

        // ---- botões de transição (State) ----
        Button btnAtribuir = new Button("Atribuir");
        Button btnRevisao = new Button("Enviar p/ Revisão");
        Button btnAprovar = new Button("Aprovar");
        Button btnReprovar = new Button("Reprovar");
        Button btnCancelar = new Button("Cancelar");
        btnAtribuir.setOnAction(e -> aplicarTransicao(Atividade::atribuir));
        btnRevisao.setOnAction(e -> aplicarTransicao(Atividade::enviarParaRevisao));
        btnAprovar.setOnAction(e -> aplicarTransicao(Atividade::aprovar));
        btnReprovar.setOnAction(e -> aplicarTransicao(Atividade::reprovar));
        btnCancelar.setOnAction(e -> aplicarTransicao(Atividade::cancelar));

        Button btnSalvar = new Button("Salvar Dados");
        Button btnDesfazer = new Button("↶ Desfazer Última Alteração");
        Button btnVoltar = new Button("Voltar");
        btnSalvar.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");
        btnDesfazer.setStyle("-fx-background-color: #FF9800; -fx-text-fill: white; -fx-font-weight: bold;");
        btnVoltar.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;");

        btnBuscar.setOnAction(e -> {
            try {
                carregar(Integer.parseInt(txtBuscaId.getText().trim()));
            } catch (NumberFormatException ex) {
                mostrarAlerta("Erro", "ID inválido!", Alert.AlertType.ERROR);
            }
        });

        btnSalvar.setOnAction(e -> salvarDados());
        btnDesfazer.setOnAction(e -> desfazer());
        btnVoltar.setOnAction(e -> {
            stage.setScene(new Programa().criarMenuPrincipal(stage));
            stage.setTitle("HYPERDUC! - Sistema de Gerenciamento");
        });

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        grid.add(lblTitulo, 0, 0, 2, 1);
        grid.add(new Label("ID da Atividade:"), 0, 1);
        grid.add(new HBox(10, txtBuscaId, btnBuscar), 1, 1);
        grid.add(new Separator(), 0, 2, 2, 1);
        grid.add(new Label("ID:"), 0, 3);
        grid.add(txtId, 1, 3);
        grid.add(new Label("Título:"), 0, 4);
        grid.add(txtTitulo, 1, 4);
        grid.add(new Label("Descrição:"), 0, 5);
        grid.add(txtDescricao, 1, 5);
        grid.add(new Label("Data de Criação:"), 0, 6);
        grid.add(txtData, 1, 6);
        grid.add(new Label("Status atual:"), 0, 7);
        grid.add(lblEstado, 1, 7);
        grid.add(new Label("Mudar status:"), 0, 8);
        grid.add(new HBox(6, btnAtribuir, btnRevisao, btnAprovar, btnReprovar, btnCancelar), 1, 8);
        grid.add(new HBox(10, btnSalvar, btnDesfazer, btnVoltar), 0, 9, 2, 1);

        return new Scene(grid, 750, 560);
    }

    private void carregar(int id) {
        atual = repositorio.buscarPorId(id);
        if (atual == null) {
            mostrarAlerta("Não encontrado", "Nenhuma atividade com ID " + id, Alert.AlertType.WARNING);
            return;
        }
        preencherCampos();
    }

    private void preencherCampos() {
        txtId.setText(String.valueOf(atual.getId()));
        txtTitulo.setText(atual.getTitulo());
        txtDescricao.setText(atual.getDescricao());
        txtData.setText(atual.getDataCriacaoFormatada());
        lblEstado.setText(atual.getStatus());
    }

    /** Edita os dados guardando antes um memento (para poder desfazer). */
    private void salvarDados() {
        if (!temAtividadeCarregada()) return;
        try {
            LocalDate data = LocalDate.parse(txtData.getText().trim(), formatter);
            if (txtTitulo.getText().trim().isEmpty() || txtDescricao.getText().trim().isEmpty()) {
                mostrarAlerta("Erro", "Todos os campos são obrigatórios!", Alert.AlertType.ERROR);
                return;
            }
            HISTORICO.salvar(atual.getId(), atual.salvarEstado());   // Memento
            atual.setTitulo(txtTitulo.getText().trim());
            atual.setDescricao(txtDescricao.getText().trim());
            atual.setDataCriacao(data);
            repositorio.atualizar(atual);                             // Template Method
            mostrarAlerta("Sucesso", "Atividade atualizada!", Alert.AlertType.INFORMATION);
        } catch (DateTimeParseException ex) {
            mostrarAlerta("Erro", "Data inválida! Use dd/MM/yyyy", Alert.AlertType.ERROR);
        }
    }

    /** Aplica uma transição do State. Se for inválida, o estado lança IllegalStateException. */
    private void aplicarTransicao(Consumer<Atividade> transicao) {
        if (!temAtividadeCarregada()) return;
        Atividade.Memento antes = atual.salvarEstado();
        try {
            transicao.accept(atual);                // State decide se pode
            HISTORICO.salvar(atual.getId(), antes); // só guarda se a transição deu certo
            repositorio.atualizar(atual);
            preencherCampos();
        } catch (IllegalStateException ex) {
            mostrarAlerta("Transição inválida", ex.getMessage(), Alert.AlertType.WARNING);
        }
    }

    /** Restaura o último memento (dados + status). */
    private void desfazer() {
        if (!temAtividadeCarregada()) return;
        Atividade.Memento anterior = HISTORICO.desfazer(atual.getId());
        if (anterior == null) {
            mostrarAlerta("Aviso", "Não há alterações para desfazer.", Alert.AlertType.INFORMATION);
            return;
        }
        atual.restaurarEstado(anterior);
        repositorio.atualizar(atual);
        preencherCampos();
    }

    private boolean temAtividadeCarregada() {
        if (atual == null) {
            mostrarAlerta("Aviso", "Busque uma atividade primeiro.", Alert.AlertType.WARNING);
            return false;
        }
        return true;
    }

    private void mostrarAlerta(String titulo, String msg, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
