package com.example.aedes.economize.frags_formularios;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.classes_modelo.Categoria;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.R;


public class FragNovaCategoria extends Fragment implements View.OnClickListener {

    private EditText et_nome, et_descricao;
    private RadioGroup novaCatRg;
    private Spinner spnn_cat;
    private Activity_pos_logagem apl;
    private RadioButton checkedRb;
    private FloatingActionButton fb_adicionar, fb_deletar;
    private CategoriaDbHandler cdbh;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_nova_categoria, container, false);
        instanciarCampos(view);
        cdbh = new CategoriaDbHandler(this.getContext(),null,null,1);
        mostrarNumeroCategoriasNoNome();
        return view;
    }

    public void cadastrarCategoria() {
        if (novaCatRg.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this.getContext(), "Tipo de operação Lucro/Despesa n]ao selecionado.", Toast.LENGTH_SHORT).show();
            return;
        }
        checkedRb = (RadioButton) novaCatRg.findViewById(novaCatRg.getCheckedRadioButtonId());
        Categoria cat = new Categoria();
        cat.setNome(et_nome.getText().toString());
        cat.setDescricao(et_descricao.getText().toString());
        cat.setNomeCatMae("LELEELELELEELL");
        cat.setEmail_criador(apl.getUsuarioAtual());
        if (checkedRb.getText().toString().equals("Lucro")) {
            cat.setTipoOperacao(1);
        } else {
            cat.setTipoOperacao(-1);
        }
        cdbh.adicionarAoBd(cat);
        mostrarNumeroCategoriasNoNome();
    }

    public void mostrarNumeroCategoriasNoNome(){
        et_nome.setText(String.valueOf(cdbh.getWritableDatabase().rawQuery("SELECT * FROM Categoria",null).getCount()));
    }

    public void instanciarCampos(View v) {
        apl = (Activity_pos_logagem) getActivity();
        fb_adicionar = (FloatingActionButton) v.findViewById(R.id.fltb_adicionar);
        fb_deletar = (FloatingActionButton) v.findViewById(R.id.fltb_deletar);
        et_nome = (EditText) v.findViewById(R.id.et_CatNome);
        et_descricao = (EditText) v.findViewById(R.id.et_CatDescricao);
        novaCatRg = (RadioGroup) v.findViewById(R.id.novaCatRadioGroup);
        spnn_cat = (Spinner) v.findViewById(R.id.spnn_CatCategoria);
        fb_adicionar.setOnClickListener(this);
        fb_deletar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.fltb_adicionar:
                cadastrarCategoria();
        }
    }
}
