package com.example.projetoversaofinal;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * PADRÃO TEMPLATE METHOD
 *
 * Define, uma única vez, o algoritmo de persistência (CRUD) usado por todas as
 * entidades. Os métodos públicos são "final": a sequência de passos
 * (ler lista -> localizar -> alterar -> gravar) não pode ser mudada pelas subclasses.
 *
 * As subclasses só preenchem os passos que variam:
 *  - getNomeArquivo()  (operação primitiva, obrigatória)
 *  - getId(item)       (operação primitiva, obrigatória)
 *  - validar(item)     (gancho/hook, opcional)
 *
 * O acesso ao disco é delegado ao Singleton GerenciadorArquivo.
 */
public abstract class RepositorioArquivo<T extends Serializable> {

    private final GerenciadorArquivo arquivo = GerenciadorArquivo.getInstancia();

    // ---------- passos que cada subclasse define ----------
    protected abstract String getNomeArquivo();

    protected abstract Object getId(T item);

    /** Hook: por padrão não valida nada. Subclasses podem sobrescrever. */
    protected void validar(T item) {
    }

    // ---------- métodos-template (algoritmo fixo) ----------
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
