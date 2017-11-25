package com.example.aedes.economize.adapters_historicos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.aedes.economize.R;
import com.example.aedes.economize.classes_modelo.Categoria;

import java.util.ArrayList;

/**
 * Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 * histórico de transações
 */

public class Lista_Categorias_ArrayAdapter extends ArrayAdapter<Categoria> {

    private TextView txtListaCatNome;

    public Lista_Categorias_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Categoria> objects) {
        super(context, R.layout.item_lista_orcamentos, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater histTransInflater;
        histTransInflater = LayoutInflater.from(getContext());
        View view = histTransInflater.inflate(R.layout.item_lista_categorias, parent, false);
         Categoria cat = getItem(position);
        instanciarCampos(view);
        txtListaCatNome.setText(cat.getNome().toString());
        return view;
    }

    public void instanciarCampos(View v) {
        txtListaCatNome = (TextView)v.findViewById(R.id.txtListCatNome);
    }
}
