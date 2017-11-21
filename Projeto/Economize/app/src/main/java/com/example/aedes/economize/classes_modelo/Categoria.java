package com.example.aedes.economize.classes_modelo;

/**
 * Created by Eu II on 18/11/2017.
 */

public class Categoria {
    private String nome, nomeCatMae, descricao, email_criador;
    private int tipoOperacao;
    public Categoria() {
    }

    public Categoria(String nome, String nomeCatMae, int tipoOperacao, String descricao, String email_criador) {
        this.nome = nome;
        this.nomeCatMae = nomeCatMae;
        this.tipoOperacao = tipoOperacao;
        this.descricao = descricao;
        this.email_criador = email_criador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeCatMae() {
        return nomeCatMae;
    }

    public void setNomeCatMae(String nomeCatMae) {
        this.nomeCatMae = nomeCatMae;
    }

    public int getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(int tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail_criador() {
        return email_criador;
    }

    public void setEmail_criador(String email_criador) {
        this.email_criador = email_criador;
    }
}
