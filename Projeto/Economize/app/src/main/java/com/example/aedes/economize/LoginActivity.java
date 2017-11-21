package com.example.aedes.economize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aedes.economize.classes_modelo.Usuario;
import com.example.aedes.economize.bdhandlers.UsuarioDbHandler;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    EditText etEmailLogin, etSenhaLogin;
    TextView etEntrar;
    UsuarioDbHandler udb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmailLogin = (EditText) findViewById(R.id.et_email_login);
        etSenhaLogin = (EditText) findViewById(R.id.et_senha_login);
        etEntrar = (TextView) findViewById(R.id.entrar_login);
        String s = getNomesCadastrados();
        etEntrar.setText(s);
    }

    public String getNomesCadastrados(){
        String s = "";
        List<Usuario> l = new UsuarioDbHandler(this, null, null, 1).getListaUsuarios();
        for(Usuario u : l){
            s += u.getNome() + "\n";
        }
        return s;
    }

    public void voltar(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logar(View v) {
        boolean usuarioExiste = false;
        udb = new UsuarioDbHandler(this, null, null, 1);
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
