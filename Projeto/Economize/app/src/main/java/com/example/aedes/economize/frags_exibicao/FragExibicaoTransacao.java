package com.example.aedes.economize.frags_exibicao;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.aedes.economize.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragExibicaoTransacao extends Fragment {


    private EditText etNome, etValor, etGrupo, etCategoria, etrecorrencia, etDataOcorrencia, etDataInicio, etDataFim, etDescricao;
    public FragExibicaoTransacao() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_frag_exibicao_transacao, container, false);
    }

    private void instanciarComponentes(View v){

    }

}
