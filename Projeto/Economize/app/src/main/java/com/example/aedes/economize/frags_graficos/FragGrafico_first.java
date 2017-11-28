package com.example.aedes.economize.frags_graficos;

import android.app.Fragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;

public class FragGrafico_first extends Fragment {
    private TransacaoDbHandler tdbh;
    private ImageButton imgBtnAnterior, imgBtnProx;
    private Spinner spnn_grafAnos;
    private ArrayList<String> valAnos;
    private ArrayAdapter<String> spnn_anosArrayAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_grafico_first, container, false);
        //fazer os gráficos
        instanciarCampos(v);
        makeBarGraph(v);
        makePieGraph(v);

        return v;
    }

    public void instanciarCampos(View v) {
        imgBtnAnterior = (ImageButton) v.findViewById(R.id.imgbtn_anterior21);
        imgBtnProx = (ImageButton) v.findViewById(R.id.imgbtn_proximo21);
        spnn_grafAnos = (Spinner) v.findViewById(R.id.spnn_anos21);
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
        spnn_grafAnos.setAdapter(spnn_anosArrayAdapter);


    }


    private void makeBarGraph(View v) {
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
        Bar d3 = new Bar();
        d3.setColor(Color.GRAY);
        d3.setName("D3bitch");
        d3.setValue(30);
        points.add(d3);


        BarGraph g = (BarGraph) v.findViewById(R.id.bar_graph);
        g.setBars(points);

        // Animação do gráfico de barras
        for (Bar b : g.getBars()) {
            b.setGoalValue((float) Math.random() * 1000);
            b.setValuePrefix("$");//display the prefix throughout the animation
        }
        g.setDuration(1200);//default if unspecified is 300 ms
        g.setInterpolator(new AccelerateDecelerateInterpolator());//Only use over/undershoot  when not inserting/deleting
        g.setValueStringPrecision(1); //1 decimal place. 0 by default for integers.
        g.animateToGoalValues();
    }

    private void makePieGraph(View v) {
        tdbh.getListaTransacoes();
        ArrayList<Transacao> transacoes = new ArrayList<>();
        float gastos = 0, lucros = 0;
        transacoes = tdbh.getListaTransacoes();
        for (Transacao t : transacoes) {
            if (t.getValor() < 0) {
                gastos += t.getValor();
            } else {
                lucros += t.getValor();
            }
        }

        PieGraph pg = (PieGraph) v.findViewById(R.id.pie_graph);
       /* PieSlice slice = new PieSlice();
        slice.setColor(Color.parseColor("#000000"));
        slice.setValue(2);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#FFBB33"));
        slice.setValue(3);
        pg.addSlice(slice);
        slice = new PieSlice();
        slice.setColor(Color.parseColor("#AA66CC"));
        slice.setValue(8);
        pg.addSlice(slice);*/

        PieSlice sliceGastos = new PieSlice();
        PieSlice sliceLucros = new PieSlice();

        sliceGastos.setColor(Color.RED);
        sliceLucros.setColor(Color.GREEN);

        sliceGastos.setValue(gastos);
        sliceLucros.setValue(lucros);


        pg.addSlice(sliceGastos);
        pg.addSlice(sliceLucros);

        if(gastos==0 && lucros ==0){
            PieSlice neutro = new PieSlice();
            neutro.setColor(Color.GRAY);
            neutro.setGoalValue(1);
            pg.addSlice(neutro);
        }
        //Animação do gráfico de pizza
        pg.setDuration(1000);//default if unspecified is 300 ms
        pg.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
        //pg.setAnimationListener(getAnimationListener());//optional
        pg.animateToGoalValues();
    }

}
