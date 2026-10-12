package com.example.projetoversaofinal;

/** Template Method: subclasse concreta para atividades. */
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
