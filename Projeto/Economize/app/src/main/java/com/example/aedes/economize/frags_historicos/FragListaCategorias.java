package com.example.aedes.economize.frags_historicos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aedes.economize.classes_modelo.Categoria;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.Lista_Categorias_ArrayAdapter;
import com.example.aedes.economize.R;

import java.util.ArrayList;

public class FragListaCategorias extends Fragment {


    private ListView listViewCategorias;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_lista_categorias, container, false);
        instanciarCampos(view);
        return view;
    }

    public void instanciarCampos(View v){

        CategoriaDbHandler cdbh = new CategoriaDbHandler(this.getContext(),null,null,1);
        ArrayList<Categoria> categorias= new ArrayList<>();
        categorias = cdbh.getListaCategorias();
        Lista_Categorias_ArrayAdapter lcad = new Lista_Categorias_ArrayAdapter(this.getContext(),categorias);

        listViewCategorias = (ListView)v.findViewById(R.id.listv_lista_categorias);
        listViewCategorias.setAdapter(lcad);
    }
}
