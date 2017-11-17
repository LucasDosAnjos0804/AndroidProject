package com.example.aedes.economize.Frags_Listas_Historicos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aedes.economize.Classes_Modelo.Orcamento;
import com.example.aedes.economize.DbHandlers.OrcamentoDbHandler;
import com.example.aedes.economize.Historico_Orcamentos_ArrayAdapter;
import com.example.aedes.economize.R;

import java.util.ArrayList;


public class FragHistoricoOrcamentos extends Fragment {

    private ArrayList<Orcamento> orcamentos;
    private ListView listaOrcamentos;
    private Historico_Orcamentos_ArrayAdapter hoad;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_frag_historico_orcamentos, container, false);
        listaOrcamentos = (ListView)view.findViewById(R.id.listv_his_orcamentos);
        orcamentos = new ArrayList<>();
        OrcamentoDbHandler odbh = new OrcamentoDbHandler(this.getContext(),null,null,1);
        orcamentos = odbh.getListaOrcamentos();
        hoad = new Historico_Orcamentos_ArrayAdapter(this.getContext(),orcamentos);
        return view;
    }


}
