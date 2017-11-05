package com.example.aedes.economize;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;


public class FragNovoOrcamento extends Fragment {



    private Spinner spnn_abrangencia;
    private String [] valAbrangencia =
            {"Mensal","Anual","Personalizado"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_frag_novo_orcamento, container, false);
        /*spnn_abrangencia = (Spinner)v.findViewById(R.id.spnn_abrangencia);
        ArrayAdapter<String> listaAbrangencia = new ArrayAdapter<String>(this.getActivity(),R.layout.fragment_frag_novo_orcamento,valAbrangencia);
        listaAbrangencia.setDropDownViewResource(R.layout.fragment_frag_novo_orcamento);
        spnn_abrangencia.setAdapter(listaAbrangencia);
*/

        return v;
    }


}
