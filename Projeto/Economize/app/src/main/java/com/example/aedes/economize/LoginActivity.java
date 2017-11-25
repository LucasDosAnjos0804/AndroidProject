package com.example.aedes.economize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aedes.economize.bdhandlers.UsuarioDbHandler;
import com.example.aedes.economize.classes_modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText etEmailLogin, etSenhaLogin;
    Spinner spnn_usuariosCadastrados;
    TextView etEntrar;
    UsuarioDbHandler udb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        udb = new UsuarioDbHandler(this,null,null,1);

        spnn_usuariosCadastrados = (Spinner)findViewById(R.id.spnn_usuariosCadastrados);
        etEmailLogin = (EditText) findViewById(R.id.et_email_login);
        etSenhaLogin = (EditText) findViewById(R.id.et_senha_login);
        etEntrar = (TextView) findViewById(R.id.entrar_login);
        ArrayList<String> s = getNomesCadastrados();

        ArrayAdapter<String> usuariosArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,s);
        usuariosArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_usuariosCadastrados.setAdapter(usuariosArrayAdapter);
    }

    public ArrayList<String> getNomesCadastrados(){
        ArrayList s = new ArrayList();
        List<Usuario> l = udb.getListaUsuarios();
        for(Usuario u : l){
            if(!u.getNome().equals("admin"))
            s.add(u.getNome());
        }
        return s;
    }

    public void voltar(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logar(View v) {
        boolean usuarioExiste = false;
        List<Usuario> usuarios = udb.getListaUsuarios();

        for(Usuario u : usuarios){
            if(u.getEmail().equals(etEmailLogin.getText().toString())
                    && u.getSenha().equals(etSenhaLogin.getText().toString())){
                usuarioExiste = true;
                Intent intent = new Intent(this, Activity_pos_logagem.class);
                intent.putExtra("usuarioAtual",u.getEmail());
                startActivity(intent);
                break;
            }
        }

        if (usuarioExiste == false) {
            Toast.makeText(this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
        }

    }


}
