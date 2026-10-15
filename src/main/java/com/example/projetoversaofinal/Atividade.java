package com.example.projetoversaofinal;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

// Atividade é o contexto do State (delega as transições ao estado atual)
// e a originadora do Memento (sabe tirar e restaurar o próprio retrato).
public class Atividade implements Serializable {

    private static final long serialVersionUID = 2L;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private int id;
    private String titulo;
    private String descricao;
    private LocalDate dataCriacao;
    private EstadoAtividade estado;

    public Atividade(int id, String titulo, String descricao, LocalDate dataCriacao) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataCriacao = dataCriacao;
        this.estado = new EstadoAtividade.Aberta();
    }


    public void atribuir()          { estado.atribuir(this); }
    public void enviarParaRevisao() { estado.enviarParaRevisao(this); }
    public void aprovar()           { estado.aprovar(this); }
    public void reprovar()          { estado.reprovar(this); }
    public void cancelar()          { estado.cancelar(this); }


    void setEstado(EstadoAtividade estado) {
        this.estado = estado;
    }

    public EstadoAtividade getEstado() {
        return estado;
    }


    public String getStatus() {
        return estado.getNome();
    }


    public Memento salvarEstado() {
        return new Memento(titulo, descricao, dataCriacao, estado);
    }

    public void restaurarEstado(Memento m) {
        this.titulo = m.titulo;
        this.descricao = m.descricao;
        this.dataCriacao = m.dataCriacao;
        this.estado = m.estado;
    }


    public static final class Memento {
        private final String titulo;
        private final String descricao;
        private final LocalDate dataCriacao;
        private final EstadoAtividade estado;

        private Memento(String titulo, String descricao, LocalDate dataCriacao, EstadoAtividade estado) {
            this.titulo = titulo;
            this.descricao = descricao;
            this.dataCriacao = dataCriacao;
            this.estado = estado;
        }
    }


    public int getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public LocalDate getDataCriacao() { return dataCriacao; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public void setDataCriacao(LocalDate dataCriacao) { this.dataCriacao = dataCriacao; }

    public String getDataCriacaoFormatada() {
        return dataCriacao == null ? "Data não encontrada" : dataCriacao.format(FORMATTER);
    }

    @Override
    public String toString() {
        return "Atividade{id=" + id + ", titulo='" + titulo + "', estado=" + getStatus()
                + ", dataCriacao=" + getDataCriacaoFormatada() + "}";
    }
}