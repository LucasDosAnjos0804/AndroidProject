package com.example.aedes.economize;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eu II on 02/11/2017.
 */

public class transacaoDbHandler extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "EconomizeDB.db";
    private static final String TABLE_NAME = "Transacao";
    private Double valor = 0.0;
    private boolean recorrente;
    private int catId,tipoOperacao; // 0 pra despesa e 1 pra ganho
    private String dtInicio,dtFim, titulo, usuEmail, descricao, frequencia;
    public transacaoDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, Double valor,
                              boolean recorrente, int catId, int tipoOperacao, String dtInicio, String dtFim, String titulo,
                              String usuEmail, String descricao, String frequencia) {
        super(context, name, factory, version);
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



    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE DABASE IF NOT EXISTS";
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}

