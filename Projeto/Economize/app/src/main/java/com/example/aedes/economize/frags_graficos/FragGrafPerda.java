package com.example.aedes.economize.frags_graficos;


import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragGrafPerda extends Fragment {

    private ImageButton btnAnoAnterior, btnAnoSucessor, btnGrafAnterior, btnGrafSucessor;
    private Spinner spnnAnos;
    private ListView lvListaCategorias;
    private PieGraph pieGraph;
    private BarGraph barGraph;
    private TransacaoDbHandler tdbh;
    private Spinner spnn_grafPerdaAnos;
    private ArrayList<String> valAnos;
    private ArrayAdapter<String> spnn_anosArrayAdapter;

    public FragGrafPerda() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag_graf_perda, container, false);
        instanciarCampos(v);
        makePieGraph(v);
        makeBarGraph(v);
        return v;
    }

    public void instanciarCampos(View view) {
        tdbh = new TransacaoDbHandler(this.getContext(), null, null, 1);
        btnAnoAnterior = (ImageButton) view.findViewById(R.id.imgbtn_ano_anterior_perdas);
        btnAnoSucessor = (ImageButton) view.findViewById(R.id.imgbtn_ano_proximo_perdas);
        btnGrafAnterior = (ImageButton) view.findViewById(R.id.imgbtn_grafico_anterior_perdas);
        btnGrafSucessor = (ImageButton) view.findViewById(R.id.imgbtn_grafico_sucessor_perdas);
        pieGraph = (PieGraph) view.findViewById(R.id.pie_graph_perdas);
        barGraph = (BarGraph) view.findViewById(R.id.bar_graph_perdas);

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

        spnn_grafPerdaAnos = (Spinner) view.findViewById(R.id.spnn_anos_perdas);
        valAnos = new ArrayList<>();
        ArrayList<Integer> valAnosInt = new ArrayList<>();
        tdbh = new TransacaoDbHandler(this.getContext(), null, null, 1);

        for (Transacao t : tdbh.getListaTransacoes()) {
            if (t.getTipoOperacao() == -1) {
                String ano = t.getDtInicio().substring(t.getDtInicio().length() - 4);
                if (!valAnosInt.contains(Integer.valueOf(ano))) {
                    valAnosInt.add(Integer.valueOf(ano));
                }
            }
        }

        Collections.sort(valAnosInt);
        for (int i : valAnosInt) {
            valAnos.add(String.valueOf(i));
        }

        spnn_anosArrayAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_spinner_item, valAnos);
        spnn_anosArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_grafPerdaAnos.setAdapter(spnn_anosArrayAdapter);
        spnn_grafPerdaAnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                           spinnerClickListener();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void mudarGrafico(View view) {
        if (pieGraph.getVisibility() == view.VISIBLE) {
            pieGraph.setVisibility(view.GONE);
            barGraph.setVisibility(view.VISIBLE);


        } else {
            barGraph.setVisibility(view.GONE);
            pieGraph.setVisibility(view.VISIBLE);
        }
    }
    public void spinnerClickListener() {
        makeBarGraph(this.getView());
        makePieGraph(this.getView());
    }

    public void makeBarGraph(View v) {
        ArrayList<Bar> points = new ArrayList<Bar>();
        String anoSelecionado = spnn_grafPerdaAnos.getSelectedItem().toString();
        String meses[] = getResources().getStringArray(R.array.mesesinhos);
        float gastosMeses[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Transacao t : tdbh.getListaTransacoes()) {
            String anoTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 4);
            String mesTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 7, t.getDtInicio().length() - 5);

            if (t.getTipoOperacao() == -1 && anoTransacao.equals(anoSelecionado)) {

                gastosMeses[Integer.valueOf(mesTransacao) - 1] += t.getValor();
            }
        }

        int cores[] = getResources().getIntArray(R.array.coresMeses);
        for (int i = 0; i < meses.length; i++) {
            Bar mes = new Bar();
            mes.setColor(cores[i]);
            ;
            mes.setName(meses[i]);
            mes.setGoalValue(gastosMeses[i]);
            points.add(mes);
        }


        barGraph.setBars(points);

        barGraph.setDuration(1200);//default if unspecified is 300 ms
        barGraph.setInterpolator(new AccelerateDecelerateInterpolator());//Only use over/undershoot  when not inserting/deleting
        barGraph.setValueStringPrecision(1); //1 decimal place. 0 by default for integers.
        barGraph.animateToGoalValues();
    }

    public void makePieGraph(View v) {

        ArrayList<PieSlice> pedacos = new ArrayList<>();
        ArrayList<Transacao> transacoes = new ArrayList<>();
        transacoes = tdbh.getListaTransacoes();
        ArrayList<String> categoriasNomes = new ArrayList<>();
        ArrayList<Integer> coresDoGrafico = new ArrayList<>();
        PieSlice sliceDessaCategoria;
        int anoSelecionado = Integer.valueOf(spnn_grafPerdaAnos.getSelectedItem().toString());

        if (transacoes.isEmpty()) {
            PieSlice vazio = new PieSlice();
            vazio.setTitle("vazio");
            vazio.setGoalValue(1);
            vazio.setColor(Color.GRAY);
            pieGraph.addSlice(vazio);
        } else {
            for (Transacao t : transacoes) {
                String anoTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 4);
                if (!categoriasNomes.contains(t.getCatNome()) && Integer.valueOf(anoTransacao) == anoSelecionado && t.getTipoOperacao()==-1) {
                    categoriasNomes.add(t.getCatNome());
                }
            }
            Toast.makeText(this.getContext(), String.valueOf(categoriasNomes.size()), Toast.LENGTH_SHORT).show();

            for (String s : categoriasNomes) {
                sliceDessaCategoria = new PieSlice();
                float ganhosNaCategoria = 0;
                for (Transacao t : transacoes) {
                    String anoTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 4);
                    if (t.getCatNome().equals(s) && t.getTipoOperacao() == -1 && Integer.valueOf(anoTransacao) == anoSelecionado) {
                        ganhosNaCategoria += t.getValor();
                    }
                }

                do {
                    int cor = Color.argb(255, new Random().nextInt(256), new Random().nextInt(256), new Random().nextInt(256));
                    if (!coresDoGrafico.contains(cor)) {
                        coresDoGrafico.add(cor);
                        sliceDessaCategoria.setColor(cor);
                        break;
                    }
                } while (true);
                sliceDessaCategoria.setTitle(s);
                sliceDessaCategoria.setGoalValue(ganhosNaCategoria);
                pedacos.add(sliceDessaCategoria);
            }

            pieGraph.removeSlices();
            for (PieSlice p : pedacos) {
                pieGraph.addSlice(p);
            }
        }

        pieGraph.setDuration(1000);//default if unspecified is 300 ms
        pieGraph.setInterpolator(new AccelerateDecelerateInterpolator());//default if unspecified is linear; constant speed
        pieGraph.animateToGoalValues();


    }

}
