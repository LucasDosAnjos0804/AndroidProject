package com.example.aedes.economize.frags_graficos;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.PieGraph;
import com.example.aedes.economize.R;

import java.security.PrivateKey;
import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragGrafGanho extends Fragment {

    private ImageButton btnAnoAnterior, btnAnoSucessor, btnGrafAnterior, btnGrafSucessor;
    private Spinner spnnAnos;
    private ListView lvListaCategorias;
    private PieGraph pieGraph;
    private BarGraph barGraph;




    public FragGrafGanho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_graf_ganho, container, false);
        instanciarCampos(view);

        return view;
    }

    public void instanciarCampos(View view){
        btnAnoAnterior = (ImageButton) view.findViewById(R.id.imgbtn_ano_anterior);
        btnAnoSucessor = (ImageButton) view.findViewById(R.id.imgbtn_ano_proximo);
        btnGrafAnterior = (ImageButton) view.findViewById(R.id.imgbtn_grafico_anterior);
        btnGrafSucessor = (ImageButton) view.findViewById(R.id.imgbtn_grafico_sucessor);
        pieGraph = (PieGraph) view.findViewById(R.id.pie_graph_ganhos);
        barGraph = (BarGraph) view.findViewById(R.id.bar_graph_ganhos);

        btnGrafSucessor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarGrafico(view);
            }
        });
        btnGrafAnterior.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mudarGrafico(view);
            }
        });
    }

    public void mudarGrafico(View view){
        if (pieGraph.getVisibility() == view.VISIBLE){
            pieGraph.setVisibility(view.GONE);
            barGraph.setVisibility(view.VISIBLE);

        }else {
            barGraph.setVisibility(view.GONE);
            pieGraph.setVisibility(view.VISIBLE);
        }
    }

}
