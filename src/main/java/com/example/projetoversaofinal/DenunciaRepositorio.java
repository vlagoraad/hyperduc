package com.example.projetoversaofinal;

/** Template Method: subclasse concreta para denúncias. */
public class DenunciaRepositorio extends RepositorioArquivo<Denuncia> {

    @Override
    protected String getNomeArquivo() {
        return "denuncias.dat";
    }

    @Override
    protected Object getId(Denuncia item) {
        return item.getId();
    }

    // hook sobrescrito: toda denúncia precisa de motivo
    @Override
    protected void validar(Denuncia item) {
        if (item.getMotivo() == null || item.getMotivo().isBlank()) {
            throw new IllegalArgumentException("O motivo da denúncia é obrigatório.");
        }
    }
}
