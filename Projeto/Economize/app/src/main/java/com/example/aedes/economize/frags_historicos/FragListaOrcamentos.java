package com.example.aedes.economize.frags_historicos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aedes.economize.classes_modelo.Orcamento;
import com.example.aedes.economize.bdhandlers.OrcamentoDbHandler;
import com.example.aedes.economize.adapters_historicos.Lista_Orcamentos_ArrayAdapter;
import com.example.aedes.economize.R;

import java.util.ArrayList;


public class FragListaOrcamentos extends Fragment {

    private ArrayList<Orcamento> orcamentos;
    private ListView listaOrcamentos;
    private Lista_Orcamentos_ArrayAdapter load;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_frag_historico_orcamentos, container, false);
        listaOrcamentos = (ListView)view.findViewById(R.id.listv_his_orcamentos);
        orcamentos = new ArrayList<>();
        OrcamentoDbHandler odbh = new OrcamentoDbHandler(this.getContext(),null,null,1);
        orcamentos = odbh.getListaOrcamentos();
        load = new Lista_Orcamentos_ArrayAdapter(this.getContext(),orcamentos);
        listaOrcamentos.setAdapter(load);
        return view;
    }


}
