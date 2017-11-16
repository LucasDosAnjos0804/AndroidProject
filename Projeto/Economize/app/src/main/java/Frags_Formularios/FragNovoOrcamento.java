package Frags_Formularios;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.aedes.economize.R;

import java.util.ArrayList;
import java.util.List;

import Classes_Modelo.Orcamento;
import DbHandlers.OrcamentoDbHandler;


public class FragNovoOrcamento extends Fragment implements View.OnClickListener{



    private Spinner spnn_abrangencia;
    private EditText et_nome, et_valor, et_descricao;
    List<String> valAbrangencia= new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag_novo_orcamento, container, false);

        return v;
    }

    public void instanciarCampos(View v){
        valAbrangencia.add("Mensal");
        valAbrangencia.add("Anual");
        valAbrangencia.add("Personalizado");

        spnn_abrangencia = (Spinner)v.findViewById(R.id.spnn_OrcAbrangencia);
        ArrayAdapter<String> listaAbrangencia = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,valAbrangencia);
        listaAbrangencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_abrangencia.setAdapter(listaAbrangencia);

        et_nome = (EditText)v.findViewById(R.id.et_OrcNome);
        et_descricao = (EditText)v.findViewById(R.id.et_OrcDescricao);
        et_valor = (EditText)v.findViewById(R.id.et_OrcValor);
    }

    public void cadastrarOrcamento(){
       Orcamento o = new Orcamento();
       o.setNome(et_nome.getText().toString());
       o.setValor(Double.parseDouble(et_valor.getText().toString()));
       o.setAbrangencia(spnn_abrangencia.getSelectedItem().toString());
       o.setDescricao(et_descricao.getText().toString());
       o.setUsuEmail("none");
       o.setCatId(0);
       OrcamentoDbHandler odbh = new OrcamentoDbHandler(this.getContext(),null,null,1);
        odbh.adicionarAoBd(o);
        Toast.makeText(this.getContext(),"Orcamento cadastrado com sucesso!",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.fltb_adicionar:



        }
    }
}
