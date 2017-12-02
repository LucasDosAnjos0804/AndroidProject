package com.example.aedes.economize.frags_historicos;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.aedes.economize.adapters_historicos_graficos.Historico_Transacoes_ArrayAdapter;
import com.example.aedes.economize.R;

import java.util.ArrayList;

import com.example.aedes.economize.classes_modelo.Transacao;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;


public class FragHistoricoTransacoes extends Fragment {



    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_historico_transacoes, container, false);

        TransacaoDbHandler t = new TransacaoDbHandler(getContext(),null,null,1);
        ArrayList<Transacao> transacoes = t.getListaTransacoes();
        Historico_Transacoes_ArrayAdapter hta = new Historico_Transacoes_ArrayAdapter(getContext(),transacoes);
        ListView l = (ListView)view.findViewById(R.id.listv_his_transacoes);

        l.setAdapter(hta);







        /*  ArrayList<Transacao> transacaos= new ArrayList<>();

        Transacao t1 = new Transacao();

        t1.setTitulo("T1");
        t1.setDtInicio("21/12/17");
        t1.setValor(-40.0);

        Transacao t2 = new Transacao();

        t2.setTitulo("T2");
        t2.setDtInicio("13/11/16");
        t2.setValor(-30.0);

        Transacao t3 = new Transacao();

        t3.setTitulo("T3");
        t3.setDtInicio("09/06/17");
        t3.setValor(-250.0);

        transacaos.add(t1);
        transacaos.add(t2);
        transacaos.add(t3);

        Historico_Transacoes_ArrayAdapter hta = new Historico_Transacoes_ArrayAdapter(getContext(),transacaos);
        ListView l = (ListView)view.findViewById(R.id.listv_his_transacoes);

        l.setAdapter(hta);
*/

        return view;
    }
}
