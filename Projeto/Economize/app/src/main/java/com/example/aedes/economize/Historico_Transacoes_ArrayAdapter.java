package com.example.aedes.economize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;

/**
 Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 histórico de transações
 */

public class Historico_Transacoes_ArrayAdapter extends ArrayAdapter<Transacao> {

    private TextView txtTitulo,txtCat,txtValor, txtData;

    public Historico_Transacoes_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Transacao> objects) {
        super(context, R.layout.item_historico_transacoes, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater histTransInflater;
        histTransInflater = LayoutInflater.from(getContext());
        View view = histTransInflater.inflate(R.layout.item_historico_transacoes,parent,false);
        Transacao t = getItem(position);
        instanciarCampos(view);
        txtTitulo.setText(t.getTitulo());
        txtValor.setText(String.valueOf(t.getValor()));
        txtData.setText(t.getDtInicio());
        txtCat.setText(t.getCatNome());
        return view;
    }

    public void instanciarCampos(View v){
        txtTitulo =(TextView)v.findViewById(R.id.txtHistTituloTrans);
        txtValor=(TextView)v.findViewById(R.id.txtHistValorTrans);
        txtData=(TextView)v.findViewById(R.id.txtHistDataTrans);
        txtCat=(TextView)v.findViewById(R.id.txtHistCatTrans);
    }
}
