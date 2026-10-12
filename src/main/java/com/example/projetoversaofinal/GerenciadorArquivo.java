package com.example.projetoversaofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * PADRÃO SINGLETON
 *
 * Ponto único de acesso aos arquivos .dat do sistema. Toda leitura e gravação
 * em disco passa por esta instância, o que evita que duas partes do sistema
 * abram o mesmo arquivo ao mesmo tempo e concentra o tratamento de erros de I/O
 * em um só lugar.
 */
public final class GerenciadorArquivo {

    // única instância da classe
    private static GerenciadorArquivo instancia;

    // construtor privado: ninguém de fora consegue dar "new GerenciadorArquivo()"
    private GerenciadorArquivo() {
    }

    // ponto global de acesso (criação preguiçosa e thread-safe)
    public static synchronized GerenciadorArquivo getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorArquivo();
        }
        return instancia;
    }

    /** Lê o objeto gravado no arquivo. Retorna null se o arquivo ainda não existe. */
    public synchronized Object ler(String caminho) {
        File arquivo = new File(caminho);
        if (!arquivo.exists()) {
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(arquivo))) {
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler " + caminho + ": " + e.getMessage());
            return null;
        }
    }

    /** Grava o objeto no arquivo, sobrescrevendo o conteúdo anterior. */
    public synchronized void gravar(String caminho, Object dados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            System.err.println("Erro ao gravar " + caminho + ": " + e.getMessage());
        }
    }
}
