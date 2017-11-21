package com.example.aedes.economize.bdhandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import com.example.aedes.economize.classes_modelo.Transacao;

/**
 * Created by Eu II on 02/11/2017.
 */

public class TransacaoDbHandler extends SQLiteOpenHelper{
    SQLiteDatabase db;
    private static final int db_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String TABLE_NAME = "Transacao";

    private static final String  catId = "catId", tipoOperacao = "tipoOperacao",valor = "valor",recorrente="recorrente";
    private static final String dtInicio="dtInicio",dtFim="dtFim", titulo="titulo", usuEmail="usuEmail", descricao="descricao", frequencia="frequencia";

    public TransacaoDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
        onCreate(getWritableDatabase());
    }


    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + titulo +" TEXT, " + descricao + " TEXT, " + valor + " DOUBLE, " + tipoOperacao +" INT, " + catId + " INT, " + dtInicio + " DATE, " + dtFim + " DATE, " + usuEmail + " TEXT, " + recorrente + " INT, " + frequencia + " INT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void adicionarAoBd(Transacao transacao){
        ContentValues valores = new ContentValues();
        valores.put("titulo",transacao.getTitulo());
        valores.put("descricao",transacao.getDescricao());
        valores.put("valor",transacao.getValor());
        valores.put("tipoOperacao",transacao.getTipoOperacao());
        valores.put("catId",transacao.getCatId());
        valores.put("dtInicio",transacao.getDtInicio());
        valores.put("dtFim",transacao.getDtFim());
        valores.put("usuEmail",transacao.getUsuEmail());
        valores.put("recorrente",transacao.isRecorrente());
        valores.put("frequencia",transacao.getFrequencia());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_NAME,null,valores);

    }

    public void removerDoBd(){}
    public void alterarNoBd(){}

    public ArrayList<Transacao> getListaTransacoes(){
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM Transacao ;", null);
        ArrayList<Transacao> transacoes = new ArrayList<Transacao>();
        while(c.moveToNext()){
            Transacao trans = new Transacao();
            trans.setTitulo(c.getString(c.getColumnIndex("titulo")));
            trans.setDescricao(c.getString(c.getColumnIndex("descricao")));
            trans.setValor(c.getDouble(c.getColumnIndex("valor")));
            trans.setTipoOperacao(c.getInt(c.getColumnIndex("tipoOperacao")));
            trans.setRecorrente(c.getInt(c.getColumnIndex("titulo")));
            trans.setDtInicio(c.getString(c.getColumnIndex("dtInicio")));
            transacoes.add(trans);
        }
        return transacoes;
    }



}

