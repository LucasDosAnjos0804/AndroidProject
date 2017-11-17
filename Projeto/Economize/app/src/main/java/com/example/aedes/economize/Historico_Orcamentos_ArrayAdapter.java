package com.example.aedes.economize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.aedes.economize.Classes_Modelo.Orcamento;

import java.util.ArrayList;

/**
 Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 histórico de transações
 */

public class Historico_Orcamentos_ArrayAdapter extends ArrayAdapter<Orcamento> {

    public Historico_Orcamentos_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Orcamento> objects) {
        super(context, R.layout.item_lista_orcamentos, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater histTransInflater;
        histTransInflater = LayoutInflater.from(getContext());
        View view = histTransInflater.inflate(R.layout.item_historico_transacoes,parent,false);
        Orcamento o = getItem(position);

        return view;
    }
}
