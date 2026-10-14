package com.example.projetoversaofinal;


public class ColaboradorRepositorio extends RepositorioArquivo<Colaborador> {

    @Override
    protected String getNomeArquivo() {
        return "colaboradores.dat";
    }

    @Override
    protected Object getId(Colaborador item) {
        return item.getId();
    }

    
    @Override
    protected void validar(Colaborador item) {
        if (item.getId() == null || item.getId().isBlank()) {
            throw new IllegalArgumentException("O ID do colaborador é obrigatório.");
        }
        if (item.getNome() == null || item.getNome().isBlank()) {
            throw new IllegalArgumentException("O nome do colaborador é obrigatório.");
        }
    }
}
