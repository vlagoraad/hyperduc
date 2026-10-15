package com.example.projetoversaofinal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public final class GerenciadorArquivo {

    
    private static GerenciadorArquivo instancia;

    
    private GerenciadorArquivo() {
    }

    
    public static synchronized GerenciadorArquivo getInstancia() {
        if (instancia == null) {
            instancia = new GerenciadorArquivo();
        }
        return instancia;
    }

    
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

    
    public synchronized void gravar(String caminho, Object dados) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(dados);
        } catch (IOException e) {
            System.err.println("Erro ao gravar " + caminho + ": " + e.getMessage());
        }
    }
}
