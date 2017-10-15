package com.example.aedes.economize;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SingUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
    }

    public void voltar(View v){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}
