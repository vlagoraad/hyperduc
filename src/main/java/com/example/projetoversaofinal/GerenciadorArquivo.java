package com.example.projetoversaofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

// Singleton: único ponto de leitura e gravação dos arquivos .dat.

public final class GerenciadorArquivo {

    private static GerenciadorArquivo instancia;

    // Privado para que ninguém consiga dar "new" fora daqui.
    private GerenciadorArquivo() {
    }

    // Cria a instância na primeira chamada e devolve sempre a mesma depois.
    public static synchronized GerenciadorArquivo getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorArquivo();
        }
        return instancia;
    }

    // Devolve null quando o arquivo ainda não existe (primeira execução).
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

    // Sobrescreve o arquivo inteiro com a lista atualizada.
    public synchronized void gravar(String caminho, Object dados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            System.err.println("Erro ao gravar " + caminho + ": " + e.getMessage());
        }
    }
}