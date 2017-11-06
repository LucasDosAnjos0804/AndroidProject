package com.example.aedes.economize;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText etEmailLogin, etSenhaLogin;
    TextView etEntrar;
    usuarioDbHandler udb;
    String usuarios = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmailLogin = (EditText) findViewById(R.id.et_email_login);
        etSenhaLogin = (EditText) findViewById(R.id.et_senha_login);
        etEntrar = (TextView) findViewById(R.id.entrarLogin);
        Cursor c = new usuarioDbHandler(this, null, null, 1).getDb();
        c.moveToFirst();
        while (!c.isAfterLast()) {
            usuarios += c.getString(c.getColumnIndex("nome")) + "\n";
            c.moveToNext();
        }

        etEntrar.setText(usuarios);
    }

    public void voltar(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logar(View v) {
        boolean usuarioExiste = false;
        udb = new usuarioDbHandler(this, null, null, 1);
        Cursor usuarios = udb.getDb();
        usuarios.moveToFirst();
        while (!usuarios.isAfterLast()) {
            if (usuarios.getString(usuarios.getColumnIndex("email")).equals(etEmailLogin.getText().toString())
                    && usuarios.getString(usuarios.getColumnIndex("senha")).equals(etSenhaLogin.getText().toString())) {
                usuarioExiste = true;
                Intent intent = new Intent(this, Activity_pos_logagem.class);
                startActivity(intent);
                break;
            }

            usuarios.moveToNext();

        }

        if (usuarioExiste == false) {
            Toast.makeText(this, "E-mail ou senha incorretos", Toast.LENGTH_SHORT).show();
        }

    }


}
