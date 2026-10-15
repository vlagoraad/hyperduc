package com.example.projetoversaofinal;

import java.io.Serializable;
import java.util.ArrayList;

// Template Method: o passo a passo do CRUD (ler a lista, achar pelo ID, alterar,

public abstract class RepositorioArquivo<T extends Serializable> {

    private final GerenciadorArquivo arquivo = GerenciadorArquivo.getInstancia();

    // Passos que cada repositório concreto precisa informar.
    protected abstract String getNomeArquivo();

    protected abstract Object getId(T item);

    // Gancho opcional: por padrão aceita tudo; Denuncia e Colaborador sobrescrevem.
    protected void validar(T item) {
    }

    // Os métodos abaixo são final para que nenhuma subclasse mude a ordem dos passos.
    @SuppressWarnings("unchecked")
    public final ArrayList<T> listar() {
        Object dados = arquivo.ler(getNomeArquivo());
        return dados == null ? new ArrayList<>() : (ArrayList<T>) dados;
    }

    public final T buscarPorId(Object id) {
        for (T item : listar()) {
            if (getId(item).equals(id)) {
                return item;
            }
        }
        return null;
    }

    public final void adicionar(T item) {
        validar(item);
        ArrayList<T> lista = listar();
        for (T existente : lista) {
            if (getId(existente).equals(getId(item))) {
                throw new IllegalArgumentException("Já existe um registro com o ID " + getId(item) + ".");
            }
        }
        lista.add(item);
        arquivo.gravar(getNomeArquivo(), lista);
    }

    public final boolean atualizar(T item) {
        validar(item);
        ArrayList<T> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (getId(lista.get(i)).equals(getId(item))) {
                lista.set(i, item);
                arquivo.gravar(getNomeArquivo(), lista);
                return true;
            }
        }
        return false;
    }

    public final boolean remover(Object id) {
        ArrayList<T> lista = listar();
        boolean removido = lista.removeIf(item -> getId(item).equals(id));
        if (removido) {
            arquivo.gravar(getNomeArquivo(), lista);
        }
        return removido;
    }
}