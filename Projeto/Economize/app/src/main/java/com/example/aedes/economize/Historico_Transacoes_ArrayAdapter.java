package com.example.aedes.economize;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import Classes_Modelo.Transacao;

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

        TextView transTit = (TextView) view.findViewById(R.id.txtTituloTrans);
        TextView transData = (TextView)view.findViewById(R.id.txtDataTrans);
        TextView transValor= (TextView)view.findViewById(R.id.txtValorTrans);
        TextView transCategoria = (TextView)view.findViewById(R.id.txtCatTrans);

        transTit.setText( t.getTitulo());
        transData.setText( t.getDtInicio());
        transValor.setText( String.valueOf(t.getValor()));
        transCategoria.setText("Indeterminado");
        return view;
    }
}
