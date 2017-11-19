package com.example.aedes.economize.DbHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aedes.economize.Classes_Modelo.Categoria;

import java.util.ArrayList;

/**
 * Created by Eu II on 18/11/2017.
 */

public class CategoriaDbHandler extends SQLiteOpenHelper {
    private static final String nome_tabela="Categoria", colNome = "nome", colNomeCatMae = "categoria_mae", colTipoOperacao = "tipo_operacao", colDescricao = "descricao", colEmail_criador = "email_criador";
    private static final String db_name = "EconomizeDB.db";
    private static final int db_version = 1;

    public CategoriaDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+nome_tabela+"("+colNome+" TEXT, ";
        sql+=colNomeCatMae+" TEXT, ";
        sql+=colDescricao+" TEXT, ";
        sql+=colTipoOperacao+" TEXT, ";
        sql+=colEmail_criador+" TEXT); ";
        sqLiteDatabase.execSQL(sql);
        Log.w("CATEGORY-LA_CRIACIÓN",sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void adicionarAoBd(Categoria c){

        ContentValues valores = new ContentValues();
        valores.put(colNome,c.getNome());
        valores.put(colNomeCatMae,c.getNomeCatMae());
        valores.put(colDescricao,c.getDescricao());
        valores.put(colTipoOperacao,c.getTipoOperacao());
        valores.put(colEmail_criador,c.getEmail_criador());
        getWritableDatabase().insert(nome_tabela,null,valores);
    }

    public ArrayList<Categoria> getListaCategorias(){
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM " + nome_tabela,null);
        ArrayList<Categoria> categorias = new ArrayList<>();
        while(c.moveToNext()){
            Categoria cat = new Categoria();
            cat.setNome(c.getString(c.getColumnIndex(colNome)));
            cat.setDescricao(c.getString(c.getColumnIndex(colDescricao)));
            cat.setNomeCatMae(c.getString(c.getColumnIndex(colNomeCatMae)));
            cat.setTipoOperacao(c.getInt(c.getColumnIndex(colTipoOperacao)));
            cat.setEmail_criador(c.getString(c.getColumnIndex(colEmail_criador)));
            categorias.add(cat);
        }
        return categorias;
    }
}
