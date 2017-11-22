package com.example.aedes.economize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aedes.economize.classes_modelo.Orcamento;

import java.util.ArrayList;

/**
 * Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 * histórico de transações
 */

public class Lista_Orcamentos_ArrayAdapter extends ArrayAdapter<Orcamento> {

    private TextView txtListaOrcValor, txtListaOrcTitulo, txtListaOrcAbrangencia;

    public Lista_Orcamentos_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Orcamento> objects) {
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
        txtListaOrcTitulo.setText(o.getNome());
        txtListaOrcValor.setText(String.valueOf(o.getValor()));
        txtListaOrcAbrangencia.setText(o.getCatNome());
        return view;
    }

    public void instanciarCampos(View v) {
        txtListaOrcTitulo= (TextView) v.findViewById(R.id.txtListaOrcTitulo);
        txtListaOrcValor = (TextView) v.findViewById(R.id.txtListaOrcValor);
        txtListaOrcAbrangencia= (TextView) v.findViewById(R.id.txtListaOrcAbrangencia);
    }
}
