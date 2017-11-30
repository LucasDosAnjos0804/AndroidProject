package com.example.aedes.economize.frags_formularios;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aedes.economize.Activity_pos_logagem;
import com.example.aedes.economize.R;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Categoria;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;


public class FragNovaTransacao extends Fragment{

    private LinearLayout linearLayoutDt, linearLayoutDtInicio, linearLayoutDtFim;
    private EditText et_valor, et_titulo, et_dtInicio, et_dtInicioRecorrente,et_dtFim, et_descricao;
    private RadioButton rd_ganho,rd_despesa;
    private CheckBox chb_recorrente;
    private Spinner spnn_ocorrencia,spnn_categoria;
    private FloatingActionButton fltb_adicionar, fltb_cancelar;
    private ArrayList<String> valCategoria;
    private TransacaoDbHandler tdbh;
    private CategoriaDbHandler cdbh;
    private ArrayAdapter<String> spinnersAdapter;
    private Transacao transacao;
    private Activity_pos_logagem apl;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_nova_transacao, container, false);
        instanciarCampos(view);
        return  view;
    }

    public void instanciarCampos(View view){
        apl = (Activity_pos_logagem)getActivity();
        spnn_categoria = (Spinner)view.findViewById(R.id.spnn_transCategoria);
        valCategoria= new ArrayList<>();
        cdbh = new CategoriaDbHandler(this.getContext(),null,null,1);
        for(Categoria cat : cdbh.getListaCategorias()){
            valCategoria.add(cat.getNome());
        }

        linearLayoutDt = (LinearLayout)view.findViewById(R.id.linearLayoutDT);
        linearLayoutDtInicio = (LinearLayout)view.findViewById(R.id.linearLayoutDtInicio);
        linearLayoutDtFim = (LinearLayout)view.findViewById(R.id.linearLayoutDtFim);

        spinnersAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,valCategoria);
        spinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnn_categoria.setAdapter(spinnersAdapter);

        et_titulo = (EditText)view.findViewById(R.id.et_transTitulo);
        et_valor= (EditText)view.findViewById(R.id.et_transValor);
        et_dtInicio = (EditText)view.findViewById(R.id.et_transDtInicio);
        et_dtInicioRecorrente = (EditText)view.findViewById(R.id.et_transDtInicioRecorrente);
        et_dtFim = (EditText)view.findViewById(R.id.et_transDtFim);
        et_descricao= (EditText)view.findViewById(R.id.et_transDescricao);
        rd_ganho= (RadioButton) view.findViewById(R.id.rd_transLucro);
        rd_despesa= (RadioButton) view.findViewById(R.id.rd_transDespesa);
        chb_recorrente = (CheckBox)view.findViewById(R.id.chb_trans_paga_recorrente);
        spnn_ocorrencia = (Spinner)view.findViewById(R.id.spnn_transOcorrencia);
        fltb_adicionar = (FloatingActionButton)view.findViewById(R.id.fltb_adicionar);
        fltb_cancelar = (FloatingActionButton)view.findViewById(R.id.fltb_deletar);
        fltb_adicionar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                criarTransacao();
            }
        });
        chb_recorrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chbClicked(view,linearLayoutDt,linearLayoutDtInicio,linearLayoutDtFim);
            }
        });
    }

    public void criarTransacao(){
        int i = 0;
        if(rd_ganho.isChecked()){
            i = 1;
        }

        if(rd_despesa.isChecked()){
            i = -1;
        }

        if(i==0){
            Toast.makeText(this.getContext(),"Tipo de Operação Lucro/Despesa não selecionado.",Toast.LENGTH_SHORT).show();
            return;
        }else{
            transacao = new Transacao();
            transacao.setTitulo(et_titulo.getText().toString());
            transacao.setValor((Double.parseDouble(et_valor.getText().toString())));
            transacao.setDescricao(et_descricao.getText().toString());
            transacao.setTipoOperacao(i);
            transacao.setCatNome(spnn_categoria.getSelectedItem().toString());
            transacao.setDtInicio(et_dtInicio.getText().toString());
            transacao.setFrequencia(null);
            transacao.setUsuEmail(apl.getUsuarioAtual());

            if(chb_recorrente.isChecked()){
                transacao.setRecorrente(true);
                transacao.setDtInicio(et_dtInicioRecorrente.getText().toString());
                transacao.setDtFim(et_dtFim.getText().toString());
            }else{
                transacao.setRecorrente(false);
                transacao.setDtInicio(et_dtInicio.getText().toString());
                transacao.setDtFim(null);
            }

            cadastrarTransacao(transacao);
            Toast.makeText(this.getContext(),"Transação adicionada com sucesso!",Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarTransacao(Transacao t){
        TransacaoDbHandler tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
        tdbh.adicionarAoBd(t);

    }
    public void chbClicked(View v, LinearLayout linearLayoutDt, LinearLayout linearLayoutDtInicio, LinearLayout linearLayoutDtFim){
        boolean checked =((CheckBox) v).isChecked();

        if (checked){//quando um orçamento é recorrente
            linearLayoutDt.setVisibility(View.GONE);
            linearLayoutDtInicio.setVisibility(View.VISIBLE);
            linearLayoutDtFim.setVisibility(View.VISIBLE);
        }else{
            linearLayoutDt.setVisibility(View.VISIBLE);
            linearLayoutDtInicio.setVisibility(View.GONE);
            linearLayoutDtFim.setVisibility(View.GONE);
        }
    }
}
