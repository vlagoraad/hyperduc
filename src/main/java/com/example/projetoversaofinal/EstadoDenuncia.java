package com.example.projetoversaofinal;

import java.io.Serializable;

/**
 * PADRÃO STATE – ciclo de vida da Denúncia.
 *
 *   Aberta --analisar--> Em análise --julgarProcedente--> Procedente
 *   Aberta / Em análise --arquivar--> Arquivada
 */
public abstract class EstadoDenuncia implements Serializable {
    private static final long serialVersionUID = 1L;

    public abstract String getNome();

    public void analisar(Denuncia denuncia)         { negar("analisar"); }
    public void julgarProcedente(Denuncia denuncia) { negar("julgar procedente"); }
    public void arquivar(Denuncia denuncia)         { negar("arquivar"); }

    protected void negar(String acao) {
        throw new IllegalStateException("Não é possível " + acao + " uma denúncia no estado \"" + getNome() + "\".");
    }

    @Override
    public String toString() {
        return getNome();
    }

    // ===================== estados concretos =====================

    public static class Aberta extends EstadoDenuncia {
        public String getNome() { return "Aberta"; }
        public void analisar(Denuncia d) { d.setEstado(new EmAnalise()); }
        public void arquivar(Denuncia d) { d.setEstado(new Arquivada()); }
    }

    public static class EmAnalise extends EstadoDenuncia {
        public String getNome() { return "Em análise"; }
        public void julgarProcedente(Denuncia d) { d.setEstado(new Procedente()); }
        public void arquivar(Denuncia d) { d.setEstado(new Arquivada()); }
    }

    public static class Procedente extends EstadoDenuncia {
        public String getNome() { return "Procedente"; }
    }

    public static class Arquivada extends EstadoDenuncia {
        public String getNome() { return "Arquivada"; }
    }
}
