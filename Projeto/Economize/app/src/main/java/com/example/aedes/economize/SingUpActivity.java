package com.example.aedes.economize;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.aedes.economize.classes_modelo.Usuario;
import com.example.aedes.economize.bdhandlers.UsuarioDbHandler;

public class SingUpActivity extends AppCompatActivity {
    EditText etNome, etEmail, etSenha1, etSenha2;
    String nome, email, senha1, senha2;
    UsuarioDbHandler udb;
    boolean temErros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        udb = new UsuarioDbHandler(this, null, null, 1);
    }

    public void cadastrar(View view) {
        temErros = false;

        etNome = (EditText) findViewById(R.id.et_CatNome);
        etEmail = (EditText) findViewById(R.id.et_email);
        etSenha1 = (EditText) findViewById(R.id.et_senha_1);
        etSenha2 = (EditText) findViewById(R.id.et_senha_2);

        nome = etNome.getText().toString();
        email = etEmail.getText().toString();
        senha1 = etSenha1.getText().toString();
        senha2 = etSenha2.getText().toString();


        if (nome.equals("") || email.equals("") || senha1.equals("") || senha2.equals("")) {
            Toast.makeText(this, "Todos os campos devem ser preenchidos.", Toast.LENGTH_SHORT).show();
            temErros = true;
        }

        if (!senha1.equals(senha2)) {
            Toast.makeText(this, "Senhas incompatíveis.", Toast.LENGTH_SHORT).show();
            if (!temErros) {
                temErros = true;
            }
        }

        if (!temErros) {
            Boolean erroNoBd = false;
            try {
                Usuario u = new Usuario(nome, email, senha1);
                udb.adicionarAoBd(u);
            } catch (SQLiteConstraintException e) {
                erroNoBd = true;
            }
            if (erroNoBd) {
                Toast.makeText(this, "O E-mail informado já está cadastrado", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Cadastro feito com sucesso!", Toast.LENGTH_SHORT).show();
            }
        }

    }

    public void voltar(View v) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
