package com.example.aedes.economize.frags_edicoes;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
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
import com.example.aedes.economize.aedes_tools.Mask;
import com.example.aedes.economize.bdhandlers.CategoriaDbHandler;
import com.example.aedes.economize.bdhandlers.TransacaoDbHandler;
import com.example.aedes.economize.classes_modelo.Categoria;
import com.example.aedes.economize.classes_modelo.Transacao;

import java.util.ArrayList;


public class FragEdicaoTransacao extends Fragment{

    private LinearLayout linearLayoutDt, linearLayoutDtInicio, linearLayoutDtFim, linearLayoutOcorrencia;
    private EditText et_valor, et_titulo, et_dtInicio, et_dtInicioRecorrente,et_dtFim, et_descricao;
    private RadioButton rd_ganho,rd_despesa;
    private CheckBox chb_recorrente;
    private Spinner spnn_ocorrencia,spnn_categoria;
    private FloatingActionButton fltb_adicionar, fltb_cancelar;
    private ArrayList<String> valCategoria, valOcorrencia;
    private TransacaoDbHandler tdbh;
    private CategoriaDbHandler cdbh;
    private ArrayAdapter<String> spinnersAdapter, spnnAdapterOcorrencia;
    private Transacao transacao;
    private Activity_pos_logagem apl;
    AlertDialog alerta;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_frag_nova_transacao, container, false);
        instanciarCampos(view);
        return  view;
    }

    public void instanciarCampos(View view){
        tdbh= new TransacaoDbHandler(this.getContext(),null,null,1);
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
        linearLayoutOcorrencia = (LinearLayout) view.findViewById(R.id.linearLayoutOcorrencia);

        spinnersAdapter = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_spinner_item,valCategoria);
        spinnersAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnn_categoria.setAdapter(spinnersAdapter);

        et_titulo = (EditText)view.findViewById(R.id.et_transTitulo);
        et_valor= (EditText)view.findViewById(R.id.et_transValor);
        et_dtInicio = (EditText)view.findViewById(R.id.et_transDtInicio);
        et_dtInicio.addTextChangedListener(Mask.insert("##/##/####",et_dtInicio));
        et_dtInicioRecorrente = (EditText)view.findViewById(R.id.et_transDtInicioRecorrente);
        et_dtInicioRecorrente.addTextChangedListener(Mask.insert("##/##/####",et_dtInicioRecorrente));
        et_dtFim = (EditText)view.findViewById(R.id.et_transDtFim);
        et_dtFim.addTextChangedListener(Mask.insert("##/##/####",et_dtFim));
        et_descricao= (EditText)view.findViewById(R.id.et_transDescricao);
        rd_ganho= (RadioButton) view.findViewById(R.id.rd_transLucro);
        rd_despesa= (RadioButton) view.findViewById(R.id.rd_transDespesa);
        chb_recorrente = (CheckBox)view.findViewById(R.id.chb_trans_paga_recorrente);

        spnn_ocorrencia = (Spinner)view.findViewById(R.id.spnn_transOcorrencia);
        valOcorrencia = new ArrayList<>();
        valOcorrencia.add("Uma vez");
        valOcorrencia.add("Semanal");
        valOcorrencia.add("Mensal");
        valOcorrencia.add("Semenstral");
        valOcorrencia.add("Anual");
        spnnAdapterOcorrencia = new ArrayAdapter<String>(this.getContext(),android.R.layout.simple_dropdown_item_1line,valOcorrencia);
        spnnAdapterOcorrencia.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spnn_ocorrencia.setAdapter(spnnAdapterOcorrencia);

        fltb_adicionar = (FloatingActionButton)view.findViewById(R.id.fltb_adicionar);
        fltb_cancelar = (FloatingActionButton)view.findViewById(R.id.fltb_deletar);
        chb_recorrente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chbClicked(view,linearLayoutDt,linearLayoutDtInicio,linearLayoutDtFim, linearLayoutOcorrencia);
            }
        });

        preencherCampos();
    }



    public void preencherCampos(){
        Transacao transSelecionada = apl.gettParaEdicao();

        et_titulo.setText(transSelecionada.getTitulo());
        et_valor.setText(String.valueOf(transSelecionada.getValor()));

        if(transSelecionada.getTipoOperacao()==1){
            rd_ganho.setChecked(true);
        }else{
            rd_despesa.setChecked(true);
        }

        for(Categoria cat : cdbh.getListaCategorias()){
            if(cat.getNome().equals(transSelecionada.getCatNome())){
                spnn_categoria.setSelection(cdbh.getListaCategorias().indexOf(cat));
            }
        }

        if(transSelecionada.isRecorrente()==1){
            chb_recorrente.setChecked(true);
            spnn_ocorrencia.setSelection(0);
        }

        et_dtInicio.setText(transSelecionada.getDtInicio());
        et_dtInicioRecorrente.setText(transSelecionada.getDtInicio());

        et_dtFim.setText(transSelecionada.getDtFim());
        et_descricao.setText(transSelecionada.getDescricao());
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
            transacao.setFrequencia(spnn_ocorrencia.getSelectedItem().toString());
            transacao.setUsuEmail(apl.getUsuarioAtual());

            if(chb_recorrente.isChecked()){
                transacao.setRecorrente(true);
                transacao.setDtInicio(et_dtInicioRecorrente.getText().toString());
                transacao.setDtFim(et_dtFim.getText().toString());
            }else{
                transacao.setRecorrente(false);
                transacao.setDtInicio(et_dtInicio.getText().toString());
                transacao.setDtFim(" ");
            }

            cadastrarTransacao(transacao);
            Toast.makeText(this.getContext(),"Transação adicionada com sucesso!",Toast.LENGTH_SHORT).show();
        }
    }

    public void cadastrarTransacao(Transacao t){
        TransacaoDbHandler tdbh = new TransacaoDbHandler(this.getContext(),null,null,1);
        tdbh.adicionarAoBd(t);
    }

    public void chbClicked(View v, LinearLayout linearLayoutDt, LinearLayout linearLayoutDtInicio, LinearLayout linearLayoutDtFim, LinearLayout linearLayoutOcorrencia){
        boolean checked =((CheckBox) v).isChecked();

        if (checked){//quando um orçamento é recorrente
            linearLayoutDt.setVisibility(View.GONE);
            linearLayoutDtInicio.setVisibility(View.VISIBLE);
            linearLayoutDtFim.setVisibility(View.VISIBLE);
            linearLayoutOcorrencia.setVisibility(View.VISIBLE);
        }else{
            linearLayoutDt.setVisibility(View.VISIBLE);
            linearLayoutDtInicio.setVisibility(View.GONE);
            linearLayoutDtFim.setVisibility(View.GONE);
            linearLayoutOcorrencia.setVisibility(View.GONE);
        }
    }

    public boolean validarData(final EditText data){// datas no formato DD/MM/AAAA
        int dia,mes;
        String dt;
        dt = data.getText().toString();
        dia = Integer.parseInt(dt.substring(0,dt.indexOf("/")));
        mes = Integer.parseInt(dt.substring(dt.indexOf("/")+1,dt.lastIndexOf("/")));
        boolean rr = true;

        if((mes==2 && dia>29) || mes>12 || ((mes==4 || mes==6 || mes==9
                || mes==11) && dia>30)){
            Context context = getContext();
            AlertDialog.Builder builder = new AlertDialog.Builder(context).setTitle("Erro!!")
                    .setMessage("Data inexitente!!\nPor favor insira uma Data válida!")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            data.requestFocus();
                        }
                    });
            alerta = builder.create();
            alerta.show();
            rr = false;
        }
        return rr;
    }

    public void iniProcCriTra(){// INIciar PROCesso de CRIação de TRAnsação
        if (chb_recorrente.isChecked()){
            if (validarData(et_dtInicioRecorrente)&&validarData(et_dtFim)){
                criarTransacao();
            }
        }else{
            if (validarData(et_dtInicio)){
                criarTransacao();
            }
        }
    }
}
