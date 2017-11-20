package com.example.aedes.economize.Frags_Listas_Historicos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.aedes.economize.R;

public class FragListaCategorias extends Fragment {


    private EditText txt_listaCatNome;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_lista_categorias, container, false);
        return view;
    }

    public void instanciarCampos(){

    }
}
