package com.example.aedes.economize.Frags_Formularios;

import android.app.Fragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aedes.economize.R;

import com.example.aedes.economize.Classes_Modelo.Transacao;
import com.example.aedes.economize.DbHandlers.TransacaoDbHandler;


public class FragNovaTransacao extends Fragment{

    private EditText et_valor, et_titulo, et_dtInicio,et_dtFim, et_descricao;
    private RadioButton rd_ganho,rd_despesa;
    private CheckBox chb_recorrente;
    private Spinner spn_ocorrencia;
    private FloatingActionButton fltb_adicionar, fltb_cancelar;
    Transacao transacao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_nova_transacao, container, false);
        instanciarCampos(view);
        return  view;
    }

    public void instanciarCampos(View view){
        et_titulo = (EditText)view.findViewById(R.id.et_transTitulo);
        et_valor= (EditText)view.findViewById(R.id.et_transValor);
        et_dtInicio = (EditText)view.findViewById(R.id.et_transDtInicio);
        et_dtFim = (EditText)view.findViewById(R.id.et_transDtFim);
        et_descricao= (EditText)view.findViewById(R.id.et_transDescricao);
        rd_ganho= (RadioButton) view.findViewById(R.id.rd_transLucro);
        rd_despesa= (RadioButton) view.findViewById(R.id.rd_transDespesa);
        chb_recorrente = (CheckBox)view.findViewById(R.id.chb_trans_paga_recorrente);
        spn_ocorrencia = (Spinner)view.findViewById(R.id.spnn_transOcorrencia);
        fltb_adicionar = (FloatingActionButton)view.findViewById(R.id.fltb_adicionar);
        fltb_cancelar = (FloatingActionButton)view.findViewById(R.id.fltb_deletar);
        fltb_adicionar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                criarTransacao();
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
            transacao.setValor((Double.parseDouble(et_valor.getText().toString())*i));
            transacao.setDescricao(et_descricao.getText().toString());
            transacao.setTipoOperacao(i);
            transacao.setCatId(1);
            transacao.setDtInicio(et_dtInicio.getText().toString());
            transacao.setDtFim(null);
            transacao.setFrequencia(null);
            transacao.setRecorrente(0);
            transacao.setUsuEmail(null);
            cadastrarTransacao(transacao);
            Toast.makeText(this.getContext(),"Transacao adicionada com sucesso!",Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarTransacao(Transacao t){
        TransacaoDbHandler tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
        tdbh.adicionarAoBd(t);

    }


}
