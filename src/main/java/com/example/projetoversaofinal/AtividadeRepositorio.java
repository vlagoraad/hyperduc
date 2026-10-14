package com.example.projetoversaofinal;


public class AtividadeRepositorio extends RepositorioArquivo<Atividade> {

    @Override
    protected String getNomeArquivo() {
        return "atividades.dat";
    }

    @Override
    protected Object getId(Atividade item) {
        return item.getId();
    }
}
