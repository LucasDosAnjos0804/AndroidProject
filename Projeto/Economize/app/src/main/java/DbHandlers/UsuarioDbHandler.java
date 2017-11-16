package DbHandlers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import Classes_Modelo.Usuario;

/**
 * Created by Eu II on 04/11/2017.
 */

public class UsuarioDbHandler extends SQLiteOpenHelper{
    private static final int db_version = 1;
    private static final String db_name = "EconomizeDB.db";
    private static final String nomeTabela = "Usuario";
    private static final String colNome="nome" ,colEmail="email",colSenha="senha";



    public UsuarioDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context,db_name, factory, db_version);
    }

    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " ("+ colNome + " TEXT, " + colEmail + " TEXT, " + colSenha + " TEXT);";
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

    public List<Usuario> getListaUsuarios(){
        Cursor c = getWritableDatabase().rawQuery("SELECT * FROM " + nomeTabela + ";",null);
        List<Usuario> usuarios = new ArrayList<>();
        while(c.moveToNext()){
            Usuario u = new Usuario();
            u.setEmail(c.getString(c.getColumnIndex("email")));
            u.setNome(c.getString(c.getColumnIndex("nome")));
            u.setSenha(c.getString(c.getColumnIndex("senha")));
            usuarios.add(u);
        }

        return usuarios;
    }
}
