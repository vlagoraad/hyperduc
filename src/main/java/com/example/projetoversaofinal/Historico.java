package com.example.projetoversaofinal;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * PADRÃO MEMENTO – Caretaker (zelador).
 *
 * Guarda uma pilha de mementos para cada registro (identificado pelo ID).
 * O Historico nunca lê nem altera o conteúdo dos mementos: só os empilha e
 * devolve. Quem sabe o que existe dentro de um memento é a própria entidade.
 *
 * @param <M> tipo do memento (Atividade.Memento, Projeto.Memento ou Denuncia.Memento)
 */
public class Historico<M> {

    private final Map<Object, Deque<M>> pilhas = new HashMap<>();

    public void salvar(Object id, M memento) {
        pilhas.computeIfAbsent(id, k -> new ArrayDeque<>()).push(memento);
    }

    /** Retorna o último memento salvo para o ID, ou null se não houver. */
    public M desfazer(Object id) {
        Deque<M> pilha = pilhas.get(id);
        return (pilha == null || pilha.isEmpty()) ? null : pilha.pop();
    }

    public boolean podeDesfazer(Object id) {
        Deque<M> pilha = pilhas.get(id);
        return pilha != null && !pilha.isEmpty();
    }
}
