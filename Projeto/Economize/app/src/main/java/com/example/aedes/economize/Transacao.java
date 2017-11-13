package com.example.aedes.economize;

/**
 * Created by Eu II on 07/11/2017.
 */

public class Transacao {
    private Double valor = 0.0;
    private boolean recorrente;
    private int catId,tipoOperacao; // 0 pra despesa e 1 pra ganho
    private String dtInicio,dtFim, titulo, usuEmail, descricao, frequencia;

    public Transacao() {
    }

    public Transacao(Double valor, boolean recorrente, int catId, int tipoOperacao, String dtInicio, String dtFim, String titulo, String usuEmail, String descricao, String frequencia) {
        this.valor = valor;
        this.recorrente = recorrente;
        this.catId = catId;
        this.tipoOperacao = tipoOperacao;
        this.dtInicio = dtInicio;
        this.dtFim = dtFim;
        this.titulo = titulo;
        this.usuEmail = usuEmail;
        this.descricao = descricao;
        this.frequencia = frequencia;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public boolean isRecorrente() {
        return recorrente;
    }

    public void setRecorrente(boolean recorrente) {
        this.recorrente = recorrente;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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
}
