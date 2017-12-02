package com.example.aedes.economize.adapters_historicos_graficos;

import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.echo.holographlibrary.PieSlice;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;

import java.util.ArrayList;


public class Lista_PieGraf_Perdas_ArrayAdapter extends ArrayAdapter<PieSlice> {
    private TextView txtCatNome,txtCatValor;
    private FrameLayout catCor;
    private TransacaoDbHandler tdbh;

    public Lista_PieGraf_Perdas_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<PieSlice> slices) {
        super(context, R.layout.item__piegraf__ganhos, slices);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater itemPieGraphList = LayoutInflater.from(this.getContext());
        View v = itemPieGraphList.inflate(R.layout.item__piegraf__ganhos,parent,false);
        PieSlice p = getItem(position);
        instanciarCampos(v);
        txtCatNome.setText(p.getTitle());
        txtCatValor.setText(String.valueOf(p.getGoalValue()));
        catCor.setBackgroundColor(p.getColor());

        return v;
    }

    public void instanciarCampos(View v){
        txtCatNome = (TextView) v.findViewById(R.id.txtListPieGrafGanhosCatNome);
        txtCatValor = (TextView) v.findViewById(R.id.txtListPieGrafGanhosValor);
        catCor = (FrameLayout)v.findViewById(R.id.listPieGrafGanhosCatCor);
    }


   }
