package DbHandlers;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

import Classes_Modelo.Orcamento;

/**
 * Created by Eu II on 15/11/2017.
 */

public class OrcamentoDbHandler extends SQLiteOpenHelper {
    private static final int db_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String TABLE_NAME = "Orcamento";

    private static final String colNome = "nome", colDescricao= "descricao", colAbrangencia= "abrangencia", colValor = "valor",colUsuEmail = "email_criador", colCategoria = "categoria";
    public OrcamentoDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, db_name, factory, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" +
                colNome + "TEXT, " +
                colDescricao + "TEXT, " +
                colAbrangencia + "TEXT, " +
                colValor + "DOUBLE, " +
                colUsuEmail + "TEXT, " +
                colCategoria + "INT); ";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void adicionarAoBd(){

    }

    public void removerDoBd(){

    }

    public void alterarNoBd(){

    }

    public ArrayList<Orcamento> getListaOrcamentos(){
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM ORCAMENTO;",null);
        ArrayList<Orcamento> orcamentos = new ArrayList<>();

        while(c.moveToNext()){
            Orcamento o = new Orcamento();
            o.setNome(c.getString(c.getColumnIndex("nome")));
            o.setDescricao(c.getString(c.getColumnIndex("descricao")));
            o.setAbrangencia(c.getString(c.getColumnIndex("abrangencia")));
            o.setUsuEmail(c.getString(c.getColumnIndex("email_criador")));
            o.setValor(c.getDouble(c.getColumnIndex("valor")));
            o.setCatId(c.getInt(c.getColumnIndex("categoria")));
            orcamentos.add(o);
        }
        return orcamentos;
    }
}
