package com.example.aedes.economize.bdhandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;

/**
 * Created by Eu II on 02/11/2017.
 */

public class TransacaoDbHandler extends SQLiteOpenHelper{
    SQLiteDatabase db;
    private static final int db_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String nome_tabela = "Transacao";
    private static final String  EmailUsuario = "email",id = "id",catNome = "catNome", tipoOperacao = "tipoOperacao",valor = "valor",recorrente="recorrente";
    private static final String categoriaNome = "nome", dtInicio="dtInicio",dtFim="dtFim", titulo="titulo", usuEmail="usuEmail", descricao="descricao", frequencia="frequencia";
    private Activity_pos_logagem apl;
    public TransacaoDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
        apl = (Activity_pos_logagem)context;
        onCreate(getWritableDatabase());
    }


    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE IF NOT EXISTS " + nome_tabela + "(" + id + " INTEGER PRIMARY KEY AUTOINCREMENT, "+ titulo +" TEXT, " + descricao + " TEXT, " + valor + " DOUBLE, " + tipoOperacao +" INT, " + catNome + " TEXT, " + dtInicio + " DATE, " + dtFim + " DATE, " + usuEmail + " TEXT, " + recorrente + " INT, " + frequencia + " INT, ";
        sql+= "FOREIGN KEY("+usuEmail+") REFERENCES Usuario("+EmailUsuario+"), ";
        sql+= "FOREIGN KEY("+catNome+") REFERENCES Categoria("+categoriaNome+")); ";
        db.execSQL("PRAGMA foreign_keys=1;");
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
        valores.put("catNome",transacao.getCatNome());
        valores.put("dtInicio",transacao.getDtInicio());
        valores.put("dtFim",transacao.getDtFim());
        valores.put("usuEmail",transacao.getUsuEmail());
        valores.put("recorrente",transacao.isRecorrente());
        valores.put("frequencia",transacao.getFrequencia());

        getWritableDatabase().execSQL("PRAGMA foreign_keys = 1");
        getWritableDatabase().insert(nome_tabela,null,valores);

    }

    public void removerDoBd(Transacao t){
        getWritableDatabase().delete(nome_tabela,String.valueOf(t.getId())+"="+id,null);
    }


    public void alterarNoBd(Transacao t){
        String sql = "UPDATE "+nome_tabela;
        sql+=" set "+titulo+"='"+t.getTitulo()+"', ";
        sql+=valor+"="+t.getValor()+", ";
        sql+=tipoOperacao+"="+t.getTipoOperacao()+", ";
        sql+=catNome+"='"+t.getCatNome()+"', ";
        sql+=recorrente+"="+t.isRecorrente()+", ";
        sql+=dtInicio+"='"+t.getDtInicio()+"', ";
        sql+=dtFim+"='"+t.getDtFim()+"', ";
        sql+=descricao+"='"+t.getDescricao()+"' WHERE "+id+"="+apl.gettParaEdicao().getId();
        getWritableDatabase().execSQL(sql);
    }

    public ArrayList<Transacao> getListaTransacoes(){
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM Transacao WHERE "+ usuEmail +"="+ "'"+apl.getUsuarioAtual()+"'", null);
        ArrayList<Transacao> transacoes = new ArrayList<Transacao>();
        while(c.moveToNext()){
            Transacao trans = new Transacao();
            trans.setTitulo(c.getString(c.getColumnIndex("titulo")));
            trans.setDescricao(c.getString(c.getColumnIndex("descricao")));
            trans.setValor(c.getDouble(c.getColumnIndex("valor")));
            trans.setTipoOperacao(c.getInt(c.getColumnIndex("tipoOperacao")));
            trans.setRecorrente(c.getInt(c.getColumnIndex("recorrente"))>0);
            trans.setDtInicio(c.getString(c.getColumnIndex("dtInicio")));
            trans.setCatNome(c.getString(c.getColumnIndex("catNome")));
            trans.setId(c.getInt(c.getColumnIndex("id")));
            trans.setDtFim(c.getString(c.getColumnIndex("dtFim")));
            transacoes.add(trans);
        }
        return transacoes;
    }



}

