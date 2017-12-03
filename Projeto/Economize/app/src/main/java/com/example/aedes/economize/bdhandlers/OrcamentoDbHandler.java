package com.example.aedes.economize.bdhandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.classes_modelo.Orcamento;

import java.util.ArrayList;

/**
 * Created by Eu II on 15/11/2017.
 */

public class OrcamentoDbHandler extends SQLiteOpenHelper {
    private static final int db_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String nome_tabela = "Orcamento";

    private static final String colNome = "nome", colDescricao = "descricao", colAbrangencia = "abrangencia", colValor = "valor", colUsuEmail = "email_criador", colCategoria = "categoria";
    private static final String usuEmail = "email", catNome = "nome", id = "id";
    private Activity_pos_logagem apl;
    public OrcamentoDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
        onCreate(getWritableDatabase());
        apl = (Activity_pos_logagem)context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = ("CREATE TABLE IF NOT EXISTS " + nome_tabela + " (" + colNome + " TEXT, " + colDescricao + " TEXT, " + colAbrangencia + " TEXT, " + colValor + " DOUBLE, " + colUsuEmail + " TEXT, " + colCategoria + " TEXT, ");
        sql += id + " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        sql += "FOREIGN KEY(" + colUsuEmail + ") REFERENCES Usuario(" + usuEmail + "), ";
        sql += "FOREIGN KEY(" + colCategoria + ") REFERENCES Categoria(" + catNome + "));";
        sqLiteDatabase.execSQL("PRAGMA foreign_keys=1");
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void adicionarAoBd(Orcamento o) {
        ContentValues valores = new ContentValues();
        valores.put(colNome, o.getNome());
        valores.put(colDescricao, o.getDescricao());
        valores.put(colValor, o.getValor());
        valores.put(colAbrangencia, o.getAbrangencia());
        valores.put(colUsuEmail, o.getUsuEmail());
        valores.put(colCategoria, o.getCatNome());
        getWritableDatabase().execSQL("PRAGMA foreign_keys = 1");
        getWritableDatabase().insert(nome_tabela, null, valores);
    }

    public void removerDoBd(Orcamento o) {
        getWritableDatabase().delete(nome_tabela, id + "=" + o.getId(), null);
    }

    public void alterarNoBd() {

    }

    public ArrayList<Orcamento> getListaOrcamentos() {
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM ORCAMENTO WHERE "+colUsuEmail+"= '" +apl.getUsuarioAtual()+"' ; ", null);
        ArrayList<Orcamento> orcamentos = new ArrayList<>();

        while (c.moveToNext()) {
            Orcamento o = new Orcamento();
            o.setNome(c.getString(c.getColumnIndex("nome")));
            o.setDescricao(c.getString(c.getColumnIndex("descricao")));
            o.setAbrangencia(c.getString(c.getColumnIndex("abrangencia")));
            o.setUsuEmail(c.getString(c.getColumnIndex("email_criador")));
            o.setValor(c.getDouble(c.getColumnIndex("valor")));
            o.setCatNome(c.getString(c.getColumnIndex("categoria")));
            o.setId(c.getInt(c.getColumnIndex("id")));
            orcamentos.add(o);

        }
        return orcamentos;
    }
}
