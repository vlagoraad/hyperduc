package com.example.projetoversaofinal;

import java.time.LocalDate;


public class DemonstracaoPadroes {

    public static void main(String[] args) {
        System.out.println("=== SINGLETON ===");
        GerenciadorArquivo a = GerenciadorArquivo.getInstancia();
        GerenciadorArquivo b = GerenciadorArquivo.getInstancia();
        System.out.println("Mesma instância? " + (a == b));

        System.out.println("\n=== TEMPLATE METHOD ===");
        AtividadeRepositorio repo = new AtividadeRepositorio();
        repo.remover(999);
        Atividade atv = new Atividade(999, "Revisar README", "Atualizar instruções", LocalDate.now());
        repo.adicionar(atv);
        System.out.println("Gravada e lida de novo: " + repo.buscarPorId(999));
        try {
            repo.adicionar(new Atividade(999, "Duplicada", "-", LocalDate.now()));
        } catch (IllegalArgumentException e) {
            System.out.println("ID duplicado recusado: " + e.getMessage());
        }
        try {
            new DenunciaRepositorio().adicionar(new Denuncia(1, "", "sem motivo", "Projeto 1", LocalDate.now()));
        } catch (IllegalArgumentException e) {
            System.out.println("Hook validar() recusou: " + e.getMessage());
        }

        System.out.println("\n=== STATE ===");
        Historico<Atividade.Memento> historico = new Historico<>();
        System.out.println("Estado inicial: " + atv.getStatus());
        historico.salvar(atv.getId(), atv.salvarEstado());
        atv.atribuir();
        System.out.println("Após atribuir: " + atv.getStatus());
        historico.salvar(atv.getId(), atv.salvarEstado());
        atv.enviarParaRevisao();
        System.out.println("Após enviar p/ revisão: " + atv.getStatus());
        try {
            atv.atribuir();
        } catch (IllegalStateException e) {
            System.out.println("Transição inválida bloqueada: " + e.getMessage());
        }

        System.out.println("\n=== MEMENTO ===");
        historico.salvar(atv.getId(), atv.salvarEstado());
        atv.setTitulo("Título alterado por engano");
        atv.aprovar();
        System.out.println("Depois da alteração: " + atv);
        atv.restaurarEstado(historico.desfazer(atv.getId()));
        System.out.println("Desfez 1x:            " + atv);
        atv.restaurarEstado(historico.desfazer(atv.getId()));
        System.out.println("Desfez 2x:            " + atv);
        repo.atualizar(atv);
        repo.remover(999);
    }
}
