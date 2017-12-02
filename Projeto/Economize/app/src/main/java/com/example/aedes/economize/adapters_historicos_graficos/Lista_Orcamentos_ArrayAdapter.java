package com.example.aedes.economize.adapters_historicos_graficos;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.OrcamentoDbHandler;
import com.example.aedes.economize.classes_modelo.Orcamento;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;

/**
 * Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 * histórico de transações
 */

public class Lista_Orcamentos_ArrayAdapter extends ArrayAdapter<Orcamento> {

    private TextView txtListaOrcValor, txtListaOrcTitulo, txtListaOrcAbrangencia;
    private ImageView imgX;
    private OrcamentoDbHandler odbh;
    public Lista_Orcamentos_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Orcamento> objects) {
        super(context, R.layout.item_lista_orcamentos, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater histTransInflater;
        histTransInflater = LayoutInflater.from(getContext());
        View view = histTransInflater.inflate(R.layout.item_lista_orcamentos, parent, false);
        final Orcamento o = getItem(position);
        instanciarCampos(view);
        txtListaOrcTitulo.setText(o.getNome());
        txtListaOrcValor.setText(String.valueOf(o.getValor()));
        txtListaOrcAbrangencia.setText(o.getCatNome());
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarOrcamento(o);
                remove(o);
            }
        });
        return view;
    }

    public void instanciarCampos(View v) {
        txtListaOrcTitulo = (TextView) v.findViewById(R.id.txtListaOrcTitulo);
        txtListaOrcValor = (TextView) v.findViewById(R.id.txtListaOrcValor);
        txtListaOrcAbrangencia = (TextView) v.findViewById(R.id.txtListaOrcAbrangencia);
        imgX = v.findViewById(R.id.listViewOrcamentosDeletar);
        odbh = new OrcamentoDbHandler(getContext(),null,null,1);
    }


    public void deletarOrcamento(final Orcamento o){
        final AlertDialog.Builder temCerteza = new AlertDialog.Builder(this.getContext());
        temCerteza.setTitle("Deletar Orçamento");
        temCerteza.setMessage("Os dados excluídos não poderão ser recuperdados. Deseja continuar?");
        temCerteza.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                        odbh.removerDoBd(o);
            }
        });

        temCerteza.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        temCerteza.show();
    }
}
