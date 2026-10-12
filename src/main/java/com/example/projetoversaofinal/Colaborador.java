package com.example.projetoversaofinal;

import java.io.Serializable;

public class Colaborador implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String nome;
    private String email;
    private String funcao;

    public Colaborador(String id, String nome, String email, String funcao) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.funcao = funcao;
    }

    public String getId() { return id; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }
    public String getFuncao() { return funcao; }

    public void setNome(String nome) { this.nome = nome; }
    public void setEmail(String email) { this.email = email; }
    public void setFuncao(String funcao) { this.funcao = funcao; }

    @Override
    public String toString() {
        return id + " - " + nome + " - " + email + " - " + funcao;
    }
}
