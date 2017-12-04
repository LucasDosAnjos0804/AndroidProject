package com.example.aedes.economize.frags_graficos;


import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
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
import com.example.aedes.economize.adapters_historicos_graficos.Lista_BarGraf_ArrayAdapter;
import com.example.aedes.economize.adapters_historicos_graficos.Lista_PieGraf_ArrayAdapter;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Transacao;
import com.example.aedes.economize.frags_formularios.FragNovaTransacao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragGrafGanho extends Fragment {

    private ImageButton btnAnoAnterior, btnAnoSucessor, btnGrafAnterior, btnGrafSucessor;
    private ListView lista;
    private PieGraph pieGraph;
    private BarGraph barGraph;
    private TransacaoDbHandler tdbh;
    private Spinner spnn_grafGanhoAnos;
    private ArrayList<String> valAnos;
    private ArrayAdapter<String> spnn_anosArrayAdapter;
    private AlertDialog alertDialog;

    public FragGrafGanho() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_graf_ganho, container, false);
        instanciarCampos(view);
        shield(view);
        setPieAdapter();
        return view;
    }

    public void shield(View v){
        final FragmentManager fm = getFragmentManager();
        if (spnn_grafGanhoAnos.getSelectedItem() == null){
            Context context = getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Alerta!")
                    .setMessage("Lucro algum foi inserido no sistema.\nInsira um e tente novamente!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            fm.beginTransaction().replace(R.id.content_frame, new FragNovaTransacao()).commit();
                        }
                    });
            alertDialog = builder.create();
            alertDialog.show();
        }else{
            makeBarGraph(v);
            makePieGraph(v);
        }
    }

    public void instanciarCampos(View view) {

        tdbh = new TransacaoDbHandler(this.getContext(), null, null, 1);
        btnAnoAnterior = (ImageButton) view.findViewById(R.id.imgbtn_ano_anterior_ganhos);
        btnAnoSucessor = (ImageButton) view.findViewById(R.id.imgbtn_ano_proximo_ganhos);
        btnGrafAnterior = (ImageButton) view.findViewById(R.id.imgbtn_grafico_anterior_ganhos);
        btnGrafSucessor = (ImageButton) view.findViewById(R.id.imgbtn_grafico_sucessor_ganhos);
        pieGraph = (PieGraph) view.findViewById(R.id.pie_graph_ganhos);
        barGraph = (BarGraph) view.findViewById(R.id.bar_graph_ganhos);
        lista = view.findViewById(R.id.listv_ganhos);

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
        ArrayList<Integer> valAnosInt = new ArrayList<>();
        tdbh = new TransacaoDbHandler(this.getContext(), null, null, 1);

        for (Transacao t : tdbh.getListaTransacoes()) {
            if (t.getTipoOperacao() == 1) {
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
        spnn_grafGanhoAnos.setAdapter(spnn_anosArrayAdapter);
        spnn_grafGanhoAnos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerClickListener();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public void spinnerClickListener() {
        makeBarGraph(this.getView());
        makePieGraph(this.getView());

        if(pieGraph.getVisibility()== getView().VISIBLE){
            setPieAdapter();
        }else{
            setBarAdapter();
        }
    }

    public void mudarGrafico(View view) {
        if (pieGraph.getVisibility() == view.VISIBLE) {
            pieGraph.setVisibility(view.GONE);
            barGraph.setVisibility(view.VISIBLE);
            setBarAdapter();
        } else {
            barGraph.setVisibility(view.GONE);
            pieGraph.setVisibility(view.VISIBLE);
            setPieAdapter();
        }
    }

    public void makeBarGraph(View v) {
        ArrayList<Bar> points = new ArrayList<Bar>();
        String anoSelecionado = spnn_grafGanhoAnos.getSelectedItem().toString();
        String meses[] = getResources().getStringArray(R.array.mesesinhos);
        float ganhosMeses[] = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (Transacao t : tdbh.getListaTransacoes()) {
            String anoTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 4);
            String mesTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 7, t.getDtInicio().length() - 5);

            if (t.getTipoOperacao() == 1 && anoTransacao.equals(anoSelecionado)) {

                ganhosMeses[Integer.valueOf(mesTransacao) - 1] += t.getValor();
            }
        }

        int cores[] = getResources().getIntArray(R.array.coresMeses);
        for (int i = 0; i < meses.length; i++) {
            Bar mes = new Bar();
            mes.setColor(cores[i]);
            ;
            mes.setName(meses[i]);
            mes.setGoalValue(ganhosMeses[i]);
            points.add(mes);
        }
        barGraph.setBars(points);
        barGraph.setDuration(1200);//default if unspecified is 300 ms
        barGraph.setInterpolator(new AccelerateDecelerateInterpolator());//Only use over/undershoot  when not inserting/deleting
        barGraph.setValueStringPrecision(1); //1 decimal place. 0 by default for integers.
        barGraph.animateToGoalValues();
    }

    public void setBarAdapter(){
        lista.setAdapter(new Lista_BarGraf_ArrayAdapter(this.getContext(),barGraph.getBars()));
    }

    public void setPieAdapter(){
        lista.setAdapter(new Lista_PieGraf_ArrayAdapter(this.getContext(),pieGraph.getSlices()));
    }

    public void makePieGraph(View v) {
        ArrayList<PieSlice> pedacos = new ArrayList<>();
        ArrayList<Transacao> transacoes = new ArrayList<>();
        transacoes = tdbh.getListaTransacoes();
        ArrayList<String> categoriasNomes = new ArrayList<>();
        ArrayList<Integer> coresDoGrafico = new ArrayList<>();
        PieSlice sliceDessaCategoria;
        int anoSelecionado = Integer.valueOf(spnn_grafGanhoAnos.getSelectedItem().toString());

        if (transacoes.isEmpty()) {
            PieSlice vazio = new PieSlice();
            vazio.setTitle("vazio");
            vazio.setGoalValue(1);
            vazio.setColor(Color.GRAY);
            pieGraph.addSlice(vazio);
        } else {
            for (Transacao t : transacoes) {
                String anoTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 4);
                if (!categoriasNomes.contains(t.getCatNome()) && Integer.valueOf(anoTransacao) == anoSelecionado && t.getTipoOperacao()==1) {
                    categoriasNomes.add(t.getCatNome());
                }
            }
            Toast.makeText(this.getContext(), String.valueOf(categoriasNomes.size()), Toast.LENGTH_SHORT).show();

            for (String s : categoriasNomes) {
                sliceDessaCategoria = new PieSlice();
                float ganhosNaCategoria = 0;
                for (Transacao t : transacoes) {
                    String anoTransacao = t.getDtInicio().substring(t.getDtInicio().length() - 4);
                    if (t.getCatNome().equals(s) && t.getTipoOperacao() == 1 && Integer.valueOf(anoTransacao) == anoSelecionado) {
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
