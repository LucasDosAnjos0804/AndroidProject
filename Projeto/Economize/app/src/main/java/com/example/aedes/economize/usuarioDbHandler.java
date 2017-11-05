package com.example.aedes.economize;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Eu II on 04/11/2017.
 */

public class usuarioDbHandler extends SQLiteOpenHelper{
    private static final int dv_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String nomeTabela = "Usuario";
    private static final String colNome="nome" ,colEmail="email",colSenha="senha";

    public usuarioDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS" + nomeTabela + " ("+ colNome + " TEXT, " + colEmail + " TEXT, " + colSenha + " TEXT);";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void adicionarAoBd(Usuario usuario){
        ContentValues valores = new ContentValues();
        valores.put(colNome,usuario.getNome());
        valores.put(colEmail,usuario.getEmail());
        valores.put(colSenha,usuario.getSenha());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(nomeTabela,null,valores);
    }

    public void removerDoBd(Usuario usuario){

    }

    public void alterarNoBD(){

    }

    public Cursor getDb(){
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM " + nomeTabela + ";",null);
        return c;
    }
}
