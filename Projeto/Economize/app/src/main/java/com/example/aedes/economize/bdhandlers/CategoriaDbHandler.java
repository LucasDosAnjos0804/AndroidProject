package com.example.aedes.economize.bdhandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.aedes.economize.classes_modelo.Categoria;

import java.util.ArrayList;

/**
 * Created by Eu II on 18/11/2017.
 */

public class CategoriaDbHandler extends SQLiteOpenHelper {
    private static final String nome_tabela="Categoria", colNome = "nome", colNomeCatMae = "categoria_mae", colTipoOperacao = "tipo_operacao", colDescricao = "descricao", colEmail_criador = "email_criador",colUsuEmail = "email";
    private static final String db_name = "EconomizeDB.db";
    private static final int db_version = 1;


    public CategoriaDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
        onCreate(getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS "+nome_tabela+"("+colNome+" TEXT PRIMARY KEY, ";
        sql+=colNomeCatMae+" TEXT, ";
        sql+=colDescricao+" TEXT, ";
        sql+=colTipoOperacao+" TEXT, ";
        sql+=colEmail_criador+" TEXT, ";
        sql+="FOREIGN KEY("+colEmail_criador+") REFERENCES Usuario("+colUsuEmail+")); ";
        Log.w("CRIANDOTABELACATEGORIA",sql);

        sqLiteDatabase.execSQL("PRAGMA foreign_keys = 1");
        sqLiteDatabase.execSQL(sql);
        inserirCategoriasPadrao(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void inserirCategoriasPadrao(SQLiteDatabase db){
        if(db.rawQuery("SELECT * FROM Categoria",null).getCount()<1) {
            Categoria transportes = new Categoria("Transporte", null, 0, "Categoria relacionada a gsstos com transportes.", "admin");
            Categoria alimentacao = new Categoria("Alimentação", null, 0, "Despesas com suprimentos e nutrição", "admin");
            Categoria entretenimento = new Categoria("Entretenimento", null, 0, "Despesase e ganhos com diferentes formas de entretenimento (festas,cinema,jogos,apostas,etc.)", "admin");
            Categoria domesticos = new Categoria("Domésticos", null, 0, "Despesas relacionadas ao ambiente doméstico, como gastos com eletricidade, gás, televisão, aquecimento, refrigeração, etc.", "admin");
            Categoria roupas = new Categoria("Roupas", null, 0, "Representa despesas com vestimentas em geral", "admin");
            Categoria saude = new Categoria("Saúde", null, 0, "Categoria reservada para transações relacionadas á saúde, como planos, medicamentos e cosméticos", "admin");
            Categoria trabalhoEstudos = new Categoria("Trabalho/Estudos", null, 0, "Ganhos e despesas relativas a trabalho e studos, como mensalidade escolar ,salário, bônus, etc.", "admin");

            adicionarAoBd(transportes);
            adicionarAoBd(alimentacao);
            adicionarAoBd(entretenimento);
            adicionarAoBd(domesticos);
            adicionarAoBd(roupas);
            adicionarAoBd(saude);
            adicionarAoBd(trabalhoEstudos);
        }
    }

    public void adicionarAoBd(Categoria c){
        ContentValues valores = new ContentValues();
        valores.put(colNome,c.getNome());
        valores.put(colNomeCatMae,c.getNomeCatMae());
        valores.put(colDescricao,c.getDescricao());
        valores.put(colTipoOperacao,c.getTipoOperacao());
        valores.put(colEmail_criador,c.getEmail_criador());
        getWritableDatabase().execSQL("PRAGMA foreign_keys = 1");
        getWritableDatabase().insertOrThrow(nome_tabela,null,valores);
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
