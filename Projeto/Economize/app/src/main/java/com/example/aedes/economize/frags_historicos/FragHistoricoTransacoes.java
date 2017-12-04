package com.example.aedes.economize.frags_historicos;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.adapters_historicos_graficos.Historico_Transacoes_ArrayAdapter;
import com.example.aedes.economize.R;

import java.util.ArrayList;

import com.example.aedes.economize.classes_modelo.Transacao;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.frags_edicoes.FragEdicaoTransacao;
import com.example.aedes.economize.frags_exibicao.FragExibicaoTransacao;
import com.example.aedes.economize.frags_formularios.FragNovaTransacao;


public class FragHistoricoTransacoes extends Fragment{

    private Activity_pos_logagem apl;
    private TransacaoDbHandler tdbh;
    private ListView lista;
    private MenuItem itemMenuEditar,itemMenuDescricao;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_historico_transacoes, container, false);

        TransacaoDbHandler t = new TransacaoDbHandler(getContext(), null, null, 1);
        ArrayList<Transacao> transacoes = t.getListaTransacoes();
        Historico_Transacoes_ArrayAdapter hta = new Historico_Transacoes_ArrayAdapter(getContext(), transacoes);
        lista = (ListView) view.findViewById(R.id.listv_his_transacoes);

        lista.setAdapter(hta);

       lista.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                editarItemSelectionado(i);
                return true;
            }
        });

        return view;
    }

    public void editarItemSelectionado(int i) {
        tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
        Transacao transSelecionada = tdbh.getListaTransacoes().get(i);
        apl = (Activity_pos_logagem) this.getContext();
        apl.settParaEdicao(transSelecionada);
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new FragEdicaoTransacao()).commit();
    }
}






