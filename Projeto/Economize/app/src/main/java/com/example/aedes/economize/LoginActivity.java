package com.example.aedes.economize;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {
    EditText etLogar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etLogar= (EditText)findViewById(R.id.et_email_login);
    }

    public void voltar(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void logar(View v){
        /*usuarioDbHandler udb = new usuarioDbHandler(this,null,null,1);
        Cursor usuarios = udb.getDb();
        usuarios.moveToFirst();
        while(!usuarios.isAfterLast()){
            if(usuarios.getString(usuarios.getColumnIndex("nome")).equals(etLogar.getText().toString())){
                Intent intent = new Intent(this, Activity_pos_logagem.class);
                startActivity(intent);
                break;
            }else{
                Toast.makeText(this,"Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        }*/

        Intent intent = new Intent(this, Activity_pos_logagem.class);
        startActivity(intent);

    }


}
