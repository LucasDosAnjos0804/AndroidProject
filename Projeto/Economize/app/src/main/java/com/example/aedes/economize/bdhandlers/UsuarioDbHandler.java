package com.example.aedes.economize.bdhandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aedes.economize.classes_modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eu II on 04/11/2017.
 */

public class UsuarioDbHandler extends SQLiteOpenHelper {
    private static final int db_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String nomeTabela = "Usuario";
    private static final String colNome = "nome", colEmail = "email", colSenha = "senha";


    public UsuarioDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
        onCreate(getWritableDatabase());
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("PRAGMA foreign_keys = 1");
        String sql = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " (" + colNome + " TEXT, " + colEmail + " TEXT PRIMARY KEY, " + colSenha + " TEXT);";
        sqLiteDatabase.execSQL(sql);
        inserirAdmin(sqLiteDatabase);

    }

    public void inserirAdmin(SQLiteDatabase db) {
        if (db.rawQuery("SELECT * FROM Usuario", null).getCount() < 1) {
            Usuario admin = new Usuario("admin", "admin", "admin");
            ContentValues valores = new ContentValues();
            valores.put(colNome, admin.getNome());
            valores.put(colEmail, admin.getEmail());
            valores.put(colSenha, admin.getSenha());
            db.insertOrThrow(nomeTabela, null, valores);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void adicionarAoBd(Usuario usuario) throws SQLiteConstraintException {
        getWritableDatabase().execSQL("PRAGMA foreign_keys = 1");
        ContentValues valores = new ContentValues();
        valores.put(colNome, usuario.getNome());
        valores.put(colEmail, usuario.getEmail());
        valores.put(colSenha, usuario.getSenha());
        getWritableDatabase().insertOrThrow(nomeTabela, null, valores);

    }

    public void removerDoBd(Usuario usuario) {

    }

    public void alterarNoBD() {

    }

    public List<Usuario> getListaUsuarios() {
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM " + nomeTabela + ";", null);
        List<Usuario> usuarios = new ArrayList<>();
        while (c.moveToNext()) {
            Usuario u = new Usuario();
            u.setEmail(c.getString(c.getColumnIndex("email")));
            u.setNome(c.getString(c.getColumnIndex("nome")));
            u.setSenha(c.getString(c.getColumnIndex("senha")));
            usuarios.add(u);
        }

        return usuarios;
    }
}
