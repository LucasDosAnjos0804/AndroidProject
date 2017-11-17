package com.example.aedes.economize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.aedes.economize.Classes_Modelo.Transacao;

import java.util.ArrayList;

/**
 Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 histórico de transações
 */

public class Historico_Transacoes_ArrayAdapter extends ArrayAdapter<Transacao> {

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


        return view;
    }
}
