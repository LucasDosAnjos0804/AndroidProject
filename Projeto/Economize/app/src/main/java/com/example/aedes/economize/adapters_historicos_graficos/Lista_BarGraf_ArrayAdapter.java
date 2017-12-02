package com.example.aedes.economize.adapters_historicos_graficos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.PieSlice;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;

import java.util.ArrayList;


public class Lista_BarGraf_ArrayAdapter extends ArrayAdapter<Bar> {
    private TextView txtMes,txtValor;
    private FrameLayout cor;
    private TransacaoDbHandler tdbh;

    public Lista_BarGraf_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Bar> bars) {
        super(context, R.layout.item_listview_bargraf, bars);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater itemPieGraphList = LayoutInflater.from(this.getContext());
        View v = itemPieGraphList.inflate(R.layout.item_listview_bargraf,parent,false);
        Bar b = getItem(position);
        instanciarCampos(v);
        txtMes.setText(b.getName());
        txtValor.setText(String.valueOf(b.getGoalValue()));
        cor.setBackgroundColor(b.getColor());

        return v;
    }

    public void instanciarCampos(View v){
        txtMes = (TextView) v.findViewById(R.id.txtListViewBargrafMes);
        txtValor= (TextView) v.findViewById(R.id.txtListViewBarGrafValor);
        cor = (FrameLayout)v.findViewById(R.id.listBarGrafCor);
    }


   }
