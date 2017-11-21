package com.example.aedes.economize.frags_graficos;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;
import com.echo.holographlibrary.Line;
import com.echo.holographlibrary.LineGraph;
import com.echo.holographlibrary.LinePoint;
import com.echo.holographlibrary.PieGraph;
import com.echo.holographlibrary.PieSlice;
import com.example.aedes.economize.R;

import java.util.ArrayList;

public class FragGrafico_first extends Fragment {

    /* TODO: ########## APENAS UM TESTE DOS GRÁFICOS */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_grafico_first, container, false);
        //fazer os gráficos
        makeLineGraph(v);
        makeBarGraph(v);
        makePieGraph(v);

        return v;
    }

    private void makeLineGraph(View v){
        Line l = new Line();
        LinePoint p = new LinePoint();
        p.setX(0);
        p.setY(5);
        l.addPoint(p);
        p = new LinePoint();
        p.setX(8);
        p.setY(8);
        l.addPoint(p);
        p = new LinePoint();
        p.setX(10);
        p.setY(4);
        l.addPoint(p);
        l.setColor(Color.parseColor("#FFBB33"));

        LineGraph li = (LineGraph)v.findViewById(R.id.line_graph);
        li.addLine(l);
        li.setRangeY(0, 10);
        li.setLineToFill(0);
    }

    private void makeBarGraph(View v){
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

        BarGraph g = (BarGraph)v.findViewById(R.id.bar_graph);
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

    private void makePieGraph(View v){
        PieGraph pg = (PieGraph)v.findViewById(R.id.pie_graph);
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
        //pg.setAnimationListener(getAnimationListener());//optional
        pg.animateToGoalValues();
    }

}
