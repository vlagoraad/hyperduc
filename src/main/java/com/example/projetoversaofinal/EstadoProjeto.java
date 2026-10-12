package com.example.projetoversaofinal;

import java.io.Serializable;

/**
 * PADRÃO STATE – ciclo de vida do Projeto.
 *
 *   Planejamento --iniciar--> Em andamento --concluir--> Concluído
 *   Planejamento / Em andamento --cancelar--> Cancelado
 */
public abstract class EstadoProjeto implements Serializable {
    private static final long serialVersionUID = 1L;

    public abstract String getNome();

    public void iniciar(Projeto projeto)  { negar("iniciar"); }
    public void concluir(Projeto projeto) { negar("concluir"); }
    public void cancelar(Projeto projeto) { negar("cancelar"); }

    protected void negar(String acao) {
        throw new IllegalStateException("Não é possível " + acao + " um projeto no estado \"" + getNome() + "\".");
    }

    @Override
    public String toString() {
        return getNome();
    }

    // ===================== estados concretos =====================

    public static class Planejamento extends EstadoProjeto {
        public String getNome() { return "Planejamento"; }
        public void iniciar(Projeto p)  { p.setEstado(new EmAndamento()); }
        public void cancelar(Projeto p) { p.setEstado(new Cancelado()); }
    }

    public static class EmAndamento extends EstadoProjeto {
        public String getNome() { return "Em andamento"; }
        public void concluir(Projeto p) { p.setEstado(new Concluido()); }
        public void cancelar(Projeto p) { p.setEstado(new Cancelado()); }
    }

    public static class Concluido extends EstadoProjeto {
        public String getNome() { return "Concluído"; }
    }

    public static class Cancelado extends EstadoProjeto {
        public String getNome() { return "Cancelado"; }
    }
}
