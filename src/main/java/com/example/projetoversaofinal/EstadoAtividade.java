package com.example.projetoversaofinal;

import java.io.Serializable;

/**
 * PADRÃO STATE – ciclo de vida da Atividade.
 *
 *   Aberta --atribuir--> Atribuída --enviarParaRevisao--> Revisão --aprovar--> Concluída
 *                            ^                               |
 *                            +----------- reprovar ----------+
 *   Aberta / Atribuída / Revisão --cancelar--> Cancelada
 *
 * Por padrão toda transição é proibida; cada estado concreto libera apenas as
 * transições válidas a partir dele.
 */
public abstract class EstadoAtividade implements Serializable {
    private static final long serialVersionUID = 1L;

    public abstract String getNome();

    public void atribuir(Atividade atividade)          { negar("atribuir"); }
    public void enviarParaRevisao(Atividade atividade) { negar("enviar para revisão"); }
    public void aprovar(Atividade atividade)           { negar("aprovar"); }
    public void reprovar(Atividade atividade)          { negar("reprovar"); }
    public void cancelar(Atividade atividade)          { negar("cancelar"); }

    protected void negar(String acao) {
        throw new IllegalStateException("Não é possível " + acao + " uma atividade no estado \"" + getNome() + "\".");
    }

    @Override
    public String toString() {
        return getNome();
    }

    // ===================== estados concretos =====================

    public static class Aberta extends EstadoAtividade {
        public String getNome() { return "Aberta"; }
        public void atribuir(Atividade a) { a.setEstado(new Atribuida()); }
        public void cancelar(Atividade a) { a.setEstado(new Cancelada()); }
    }

    public static class Atribuida extends EstadoAtividade {
        public String getNome() { return "Atribuída"; }
        public void enviarParaRevisao(Atividade a) { a.setEstado(new Revisao()); }
        public void cancelar(Atividade a) { a.setEstado(new Cancelada()); }
    }

    public static class Revisao extends EstadoAtividade {
        public String getNome() { return "Revisão"; }
        public void aprovar(Atividade a) { a.setEstado(new Concluida()); }
        public void reprovar(Atividade a) { a.setEstado(new Atribuida()); }
        public void cancelar(Atividade a) { a.setEstado(new Cancelada()); }
    }

    public static class Concluida extends EstadoAtividade {
        public String getNome() { return "Concluída"; }
    }

    public static class Cancelada extends EstadoAtividade {
        public String getNome() { return "Cancelada"; }
    }
}
