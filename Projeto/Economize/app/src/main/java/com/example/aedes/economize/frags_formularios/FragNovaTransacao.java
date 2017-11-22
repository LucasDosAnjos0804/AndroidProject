package com.example.aedes.economize.frags_formularios;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aedes.economize.NT_recorente;
import com.example.aedes.economize.R;
import com.example.aedes.economize.T_recorrente;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;


public class FragNovaTransacao extends Fragment{

    private EditText et_valor, et_titulo, et_dtInicio,et_dtFim, et_descricao;
    private RadioButton rd_ganho,rd_despesa;
    private CheckBox chb_recorrente;
    private Spinner spnn_ocorrencia,spnn_categoria;
    private FloatingActionButton fltb_adicionar, fltb_cancelar;
    private ArrayList<String> valCategoria;
    private TransacaoDbHandler tdbh;
    private ArrayAdapter<String> spinnersAdapter;
    Transacao transacao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_nova_transacao, container, false);
        instanciarCampos(view);
        return  view;
    }

    public void instanciarCampos(View view){
        spnn_categoria = (Spinner)view.findViewById(R.id.spnn_transCategoria);
        valCategoria= new ArrayList<>();
        tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
        for(Transacao t : tdbh.getListaTransacoes()){
            valCategoria.add(t.getTitulo());
        }
        spinnersAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,valCategoria);
        spinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnn_categoria.setAdapter(spinnersAdapter);

        et_titulo = (EditText)view.findViewById(R.id.et_transTitulo);
        et_valor= (EditText)view.findViewById(R.id.et_transValor);
        et_dtInicio = (EditText)view.findViewById(R.id.et_transDtInicio);
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
                chbClicked(view);
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

    public void chbClicked(View v){//todo OnClick do checkbox -Pagamento Recorrente- isso deve ficar na activity
        boolean checked =((CheckBox) v).isChecked();
        LayoutInflater layoutInflater = (LayoutInflater) v.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        FragmentManager fragmentManager = getFragmentManager();
        switch (v.getId()){
            case R.id.chb_trans_paga_recorrente:
                if (checked){//quando um orçamento é recorrente
                    fragmentManager.beginTransaction().replace(R.id.framelayout_datarec_datanaorec, new T_recorrente()).commit();//uma outra forma de fazer isso!!
                    //View filho = layoutInflater != null ? layoutInflater.inflate(R.layout.fragment_t_recorrente,
                      //      (ViewGroup) v.findViewById(R.id.frag_id_t_recorrente)) : null;
                    //linearLayoutTrTnr.addView(filho);
                }else{
                    fragmentManager.beginTransaction().replace(R.id.framelayout_datarec_datanaorec, new NT_recorente()).commit();
                    //View filho = layoutInflater != null ? layoutInflater.inflate(R.layout.fragment_nt_recorente,
                      //      (ViewGroup) v.findViewById(R.id.frag_id_nt_recorrente)) : null;
                    //linearLayoutTrTnr.addView(filho);
                }
                break;
        }
    }


}
