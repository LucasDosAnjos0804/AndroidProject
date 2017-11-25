package com.example.aedes.economize.classes_modelo;

/**
 * Created by Eu II on 07/11/2017.
 */

public class Transacao {
    private Double valor = 0.0;
    private boolean recorrente;
    private int id,tipoOperacao; // 0 pra despesa e 1 pra ganho
    private String catNome,dtInicio,dtFim, titulo, usuEmail, descricao, frequencia;

    public Transacao() {
    }

    public Transacao(Double valor, boolean recorrente, String catNome, int tipoOperacao, String dtInicio, String dtFim, String titulo, String usuEmail, String descricao, String frequencia) {
        this.valor = valor;
        this.recorrente = recorrente;
        this.catNome = catNome;
        this.tipoOperacao = tipoOperacao;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.titulo = titulo;
        this.usuEmail = usuEmail;
        this.descricao = descricao;
        this.frequencia = frequencia;
    }

    public String getCatNome() {
        return catNome;
    }

    public void setCatNome(String catNome) {
        this.catNome = catNome;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public int isRecorrente() {
        if(recorrente){return 1;}else{return 0;}
    }

    public void setRecorrente(boolean recorrente) {
        this.recorrente = recorrente;
    }

    public int getTipoOperacao() {
        return tipoOperacao;
    }

    public void setTipoOperacao(int tipoOperacao) {
        this.tipoOperacao = tipoOperacao;
    }

    public String getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtFim() {
        return dtFim;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getUsuEmail() {
        return usuEmail;
    }

    public void setUsuEmail(String usuEmail) {
        this.usuEmail = usuEmail;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void setId(int id){this.id = id;}

    public int getId(){return id;}


}
