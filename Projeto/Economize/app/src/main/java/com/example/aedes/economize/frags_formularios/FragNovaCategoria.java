package com.example.aedes.economize.frags_formularios;

import android.app.Fragment;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.classes_modelo.Categoria;

import java.util.ArrayList;


public class FragNovaCategoria extends Fragment implements View.OnClickListener {

    private EditText et_nome, et_descricao;
    private RadioGroup novaCatRg;
    private Spinner spnn_cat;
    private Activity_pos_logagem apl;
    private RadioButton checkedRb;
    private FloatingActionButton fb_adicionar, fb_deletar;
    private CategoriaDbHandler cdbh;
    private ArrayList<String> valCatMae;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_nova_categoria, container, false);
        instanciarCampos(view);
        cdbh = new CategoriaDbHandler(this.getContext(), null, null, 1);
        return view;
    }

    public void cadastrarCategoria() {
        try {
            if (novaCatRg.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this.getContext(), "Tipo de operação Lucro/Despesa n]ao selecionado.", Toast.LENGTH_SHORT).show();
                return;
            }
            checkedRb = (RadioButton) novaCatRg.findViewById(novaCatRg.getCheckedRadioButtonId());

            Categoria cat = new Categoria();
            cat.setNome(et_nome.getText().toString());
            cat.setDescricao(et_descricao.getText().toString());
            cat.setEmail_criador(apl.getUsuarioAtual());
            cat.setNomeCatMae(spnn_cat.getSelectedItem().toString());
            if (checkedRb.getText().toString().equals("Lucro")) {
                cat.setTipoOperacao(1);
            } else {
                cat.setTipoOperacao(-1);
            }
            cdbh.adicionarAoBd(cat);
        } catch (SQLiteConstraintException e) {
            Toast.makeText(this.getContext(),"Já existe uma categoria criada com este nome",Toast.LENGTH_SHORT).show();
        }
    }


    public void instanciarCampos(View v) {
        spnn_cat = (Spinner) v.findViewById(R.id.spnn_CatCategoria);
        apl = (Activity_pos_logagem) getActivity();
        fb_adicionar = (FloatingActionButton) v.findViewById(R.id.fltb_adicionar);
        fb_deletar = (FloatingActionButton) v.findViewById(R.id.fltb_deletar);
        et_nome = (EditText) v.findViewById(R.id.et_CatNome);
        et_descricao = (EditText) v.findViewById(R.id.et_CatDescricao);
        novaCatRg = (RadioGroup) v.findViewById(R.id.novaCatRadioGroup);
        valCatMae = new ArrayList<>();

        fb_adicionar.setOnClickListener(this);
        fb_deletar.setOnClickListener(this);

        cdbh = new CategoriaDbHandler(this.getContext(),null,null,1);
        for(Categoria cat : cdbh.getListaCategorias()){
            if(cat.getEmail_criador().equals(apl.getUsuarioAtual()) && cat.getEmail_criador().equals("admin")){
                valCatMae.add(cat.getNome());
            }
        }
        ArrayAdapter<String> spnn_catAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,valCatMae);
        spnn_catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_cat.setAdapter(spnn_catAdapter);

    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fltb_adicionar:
                cadastrarCategoria();
        }
    }
}
