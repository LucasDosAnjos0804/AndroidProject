package com.example.aedes.economize.classes_modelo;

/**
 * Created by Eu II on 15/11/2017.
 */

public class Orcamento {
    private String nome, descricao, abrangencia, usuEmail;
    private String catNome;
    private double valor;
    private int id;

    public Orcamento(){
    }

    public Orcamento(String nome, String descricao, String abrangencia, String usuEmail, String catNome, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.abrangencia = abrangencia;
        this.usuEmail = usuEmail;
        this.catNome= catNome;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getAbrangencia() {
        return abrangencia;
    }

    public void setAbrangencia(String abrangencia) {
        this.abrangencia = abrangencia;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getCatNome() {
        return catNome;
    }

    public void setCatNome(String catNome) {
        this.catNome = catNome;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public void setId(int id){this.id = id;}

    public int getId(){return id;}
}
