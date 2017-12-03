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
import android.widget.Toast;

import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.bdhandlers.OrcamentoDbHandler;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Categoria;
import com.example.aedes.economize.classes_modelo.Orcamento;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;

/**
 * Classe ArrayAdapter para adaptar tuplas de Transações do bd na ListView do
 * histórico de transações
 */

public class Lista_Categorias_ArrayAdapter extends ArrayAdapter<Categoria> {

    private TextView txtListaCatNome;
    private ImageView imgX;
    private CategoriaDbHandler cdbh;
    OrcamentoDbHandler odbh;
    TransacaoDbHandler tdbh;

    public Lista_Categorias_ArrayAdapter(@NonNull Context context, @NonNull ArrayList<Categoria> objects) {
        super(context, R.layout.item_lista_orcamentos, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater listCatInflater;
        listCatInflater = LayoutInflater.from(getContext());
        View view = listCatInflater.inflate(R.layout.item_lista_categorias, parent, false);
         final Categoria cat = getItem(position);
         instanciarCampos(view);
        txtListaCatNome.setText(cat.getNome().toString());
        imgX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletarCategoria(cat);
            }
        });
        return view;
    }

    public void instanciarCampos(View v) {
        txtListaCatNome = (TextView)v.findViewById(R.id.txtListCatNome);
        imgX = v.findViewById(R.id.imgViewDeletarCat);
        cdbh = new CategoriaDbHandler(this.getContext(),null,null,1);
        odbh = new OrcamentoDbHandler(this.getContext(),null,null,1);
        tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
    }

    public void deletarCategoria(final Categoria cat){
        final AlertDialog.Builder temCerteza = new AlertDialog.Builder(this.getContext());
        temCerteza.setTitle("Deletar Categoria");
        temCerteza.setMessage("Todos os orçamentos e transações relacionados a esta cateogoria serão exclu~idos. Deseja continuar?");
        temCerteza.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                for(Transacao t : tdbh.getListaTransacoes()){
                    if(t.getCatNome().equals(cat.getNome())){
                     tdbh.removerDoBd(t);
                    }
                }

                for(Orcamento o : odbh.getListaOrcamentos()){
                    if(o.getCatNome().equals(cat.getNome())){
                        odbh.removerDoBd(o);
                    }
                }
                cdbh.removerdoBd(cat);
                remove(cat);
                sucesso();
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

    public void sucesso(){
        Toast.makeText(this.getContext(),"Dados removidos com sucesso!",Toast.LENGTH_SHORT).show();
    }
}
