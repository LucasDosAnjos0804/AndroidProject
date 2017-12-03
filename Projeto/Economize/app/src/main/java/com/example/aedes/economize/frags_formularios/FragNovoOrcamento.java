package com.example.aedes.economize.frags_formularios;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.Service;
import android.content.Context;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.text.Layout;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;


import com.echo.holographlibrary.Line;
import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.classes_modelo.Categoria;
import com.example.aedes.economize.classes_modelo.Orcamento;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.bdhandlers.OrcamentoDbHandler;
import com.example.aedes.economize.R;

import java.io.LineNumberReader;
import java.util.ArrayList;


public class FragNovoOrcamento extends Fragment implements View.OnClickListener {
    private Spinner spnn_abrangencia,spnn_categoria;
    private EditText et_nome, et_valor, et_descricao;
    private Activity_pos_logagem apl;

    private LinearLayout ln14,ln15,ln16,ln3;
    private ConstraintLayout cl;


    FloatingActionButton fltAdd, fltDel;
    ArrayList<String> valAbrangencia, valCategoria;
    CategoriaDbHandler cdbh;
    ArrayAdapter<String> listasSpinnersAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_frag_novo_orcamento, container, false);
        instanciarCampos(v);
        //customizarTela(cl);
        return v;
    }

    @SuppressLint("ResourceType")
    public void instanciarCampos(View v) {
        apl =  (Activity_pos_logagem) getActivity();
        valCategoria = new ArrayList<>();
        valAbrangencia = new ArrayList<>();
        cdbh = new CategoriaDbHandler(v.getContext(), null, null, 1);

        valAbrangencia.add("Mensal");
        valAbrangencia.add("Anual");
        valAbrangencia.add("Personalizado");
        spnn_abrangencia = (Spinner) v.findViewById(R.id.spnn_OrcAbrangencia);
        listasSpinnersAdapter = new ArrayAdapter<String>(v.getContext(), android.R.layout.simple_spinner_item, valAbrangencia);
        listasSpinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_abrangencia.setAdapter(listasSpinnersAdapter);

        for (Categoria cat : cdbh.getListaCategorias()) {
            valCategoria.add(cat.getNome());
        }
        spnn_categoria = (Spinner)v.findViewById(R.id.spnn_OrcCategoria);
        listasSpinnersAdapter = new ArrayAdapter<String>(v.getContext(),android.R.layout.simple_spinner_item,valCategoria);
        listasSpinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_categoria.setAdapter(listasSpinnersAdapter);

        et_nome = (EditText) v.findViewById(R.id.et_OrcNome);
        et_descricao = (EditText) v.findViewById(R.id.et_OrcDescricao);
        et_valor = (EditText) v.findViewById(R.id.et_OrcValor);
        fltAdd = (FloatingActionButton) v.findViewById(R.id.fltb_adicionar);
        fltDel = (FloatingActionButton) v.findViewById(R.id.fltb_deletar);
        cl = (ConstraintLayout) v.findViewById(R.layout.fragment_frag_novo_orcamento);

        ln3 = (LinearLayout) v.findViewById(R.id.linearLayout3);
        ln14 = (LinearLayout) v.findViewById(R.id.linearLayout14);
        ln15 = (LinearLayout) v.findViewById(R.id.linearLayout15);
        ln16 = (LinearLayout) v.findViewById(R.id.linearLayout16);

        fltAdd.setOnClickListener(this);
        fltDel.setOnClickListener(this);
    }

    public void customizarTela(ConstraintLayout layout){
        Context context = getContext();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        int height = display.getHeight();

        layout.setMinHeight(height);
    }

    public void cadastrarOrcamento() {
        Orcamento o = new Orcamento();
        o.setNome(et_nome.getText().toString());
        o.setValor(Double.parseDouble(et_valor.getText().toString()));
        o.setAbrangencia(spnn_abrangencia.getSelectedItem().toString());
        o.setDescricao(et_descricao.getText().toString());
        o.setUsuEmail(apl.getUsuarioAtual());
        o.setCatNome(spnn_categoria.getSelectedItem().toString());
        OrcamentoDbHandler odbh = new OrcamentoDbHandler(this.getContext(), null, null, 1);
        odbh.adicionarAoBd(o);
        Toast.makeText(this.getContext(), "Orcamento cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fltb_adicionar:
                cadastrarOrcamento();
        }
    }
}