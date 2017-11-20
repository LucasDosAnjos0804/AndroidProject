package com.example.aedes.economize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aedes.economize.Classes_Modelo.Orcamento;

import java.util.ArrayList;

/**
 * Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 * histórico de transações
 */

public class Lista_Categorias_ArrayAdapter extends ArrayAdapter<Orcamento> {

    private TextView txtHistOrcValor, txtHistOrcTitulo, txtHistOrcAbrangencia;

    public Lista_Categorias_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Orcamento> objects) {
        super(context, R.layout.item_lista_orcamentos, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater histTransInflater;
        histTransInflater = LayoutInflater.from(getContext());
        View view = histTransInflater.inflate(R.layout.item_lista_orcamentos, parent, false);
        Orcamento o = getItem(position);
        instanciarCampos(view);
        txtHistOrcTitulo.setText(o.getNome());
        txtHistOrcValor.setText(String.valueOf(o.getValor()));
        txtHistOrcAbrangencia.setText(o.getAbrangencia());
        return view;
    }

    public void instanciarCampos(View v) {
        txtHistOrcTitulo = (TextView) v.findViewById(R.id.txtHistOrcTitulo);
        txtHistOrcValor = (TextView) v.findViewById(R.id.txtHistOrcValor);
        txtHistOrcAbrangencia = (TextView) v.findViewById(R.id.txtHistOrcAbrangencia);
    }
}
