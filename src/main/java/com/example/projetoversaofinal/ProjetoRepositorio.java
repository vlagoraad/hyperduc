package com.example.projetoversaofinal;

/** Template Method: subclasse concreta para projetos. */
public class ProjetoRepositorio extends RepositorioArquivo<Projeto> {

    @Override
    protected String getNomeArquivo() {
        return "projetos.dat";
    }

    @Override
    protected Object getId(Projeto item) {
        return item.getId();
    }
}
