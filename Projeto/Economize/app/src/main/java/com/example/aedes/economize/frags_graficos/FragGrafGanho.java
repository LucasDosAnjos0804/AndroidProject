package com.example.aedes.economize.frags_graficos;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Transacao;

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
    private TransacaoDbHandler tdbh;
    private Spinner spnn_grafGanhoAnos;
    private ArrayList<String> valAnos;
    private ArrayAdapter<String> spnn_anosArrayAdapter;


    public FragGrafGanho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_graf_ganho, container, false);
        instanciarCampos(view);
        makeBarGraph(view);
        makePieGraph(view);
        return view;
    }

    public void instanciarCampos(View view){
        tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
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

        spnn_grafGanhoAnos = (Spinner) view.findViewById(R.id.spnn_anos_ganhos);
        valAnos = new ArrayList<>();
        tdbh = new TransacaoDbHandler(this.getContext(), null, null, 1);

        for(Transacao t : tdbh.getListaTransacoes()){
            String ano =t.getDtInicio().substring(t.getDtInicio().length()-4);
            if(!valAnos.contains(ano)){
                valAnos.add(ano);
            }
        }

        spnn_anosArrayAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,valAnos);
        spnn_anosArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_grafGanhoAnos.setAdapter(spnn_anosArrayAdapter);
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

    public void makeBarGraph(View v){
        ArrayList<Bar> points = new ArrayList<Bar>();
        Bar d = new Bar();
        d.setColor(Color.parseColor("#99CC00"));
        d.setName("Test1");
        d.setValue(10);
        Bar d2 = new Bar();
        d2.setColor(Color.parseColor("#FFBB33"));
        d2.setName("Test2");
        d2.setValue(20);
        points.add(d);
        points.add(d2);

        BarGraph g = (BarGraph)v.findViewById(R.id.bar_graph_ganhos);
        g.setBars(points);

        for (Bar b : barGraph.getBars()) {
            b.setGoalValue((float) Math.random() * 1000);
            b.setValuePrefix("$");//display the prefix throughout the animation
        }
        barGraph.setDuration(1200);//default if unspecified is 300 ms
        barGraph.setInterpolator(new AccelerateDecelerateInterpolator());//Only use over/undershoot  when not inserting/deleting
        barGraph.setValueStringPrecision(1); //1 decimal place. 0 by default for integers.
        barGraph.animateToGoalValues();

    }

    public void makePieGraph(View v){
        PieGraph pg = (PieGraph)v.findViewById(R.id.pie_graph_ganhos);
        PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#99CC00"));
        slice.setValue(2);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#FFBB33"));
        slice.setValue(3);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#AA66CC"));
        slice.setValue(8);
        pg.addSlice(slice);
        //Animação do gráfico de pizza
        for (PieSlice s : pg.getSlices())
            s.setGoalValue((float)Math.random() * 10);
        pg.setDuration(1000);//default if unspecified is 300 ms
        pg.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
        pg.animateToGoalValues();


    }
}
