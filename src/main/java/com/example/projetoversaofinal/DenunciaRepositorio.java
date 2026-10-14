package com.example.projetoversaofinal;


public class DenunciaRepositorio extends RepositorioArquivo<Denuncia> {

    @Override
    protected String getNomeArquivo() {
        return "denuncias.dat";
    }

    @Override
    protected Object getId(Denuncia item) {
        return item.getId();
    }

    
    @Override
    protected void validar(Denuncia item) {
        if (item.getMotivo() == null || item.getMotivo().isBlank()) {
            throw new IllegalArgumentException("O motivo da denúncia é obrigatório.");
        }
    }
}
