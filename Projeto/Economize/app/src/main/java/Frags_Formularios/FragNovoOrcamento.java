package Frags_Formularios;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.aedes.economize.R;

import java.util.ArrayList;
import java.util.List;


public class FragNovoOrcamento extends Fragment {



    private Spinner spnn_abrangencia;
    List<String> valAbrangencia= new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_frag_novo_orcamento, container, false);
        valAbrangencia.add("Mensal");
        valAbrangencia.add("Anual");
        valAbrangencia.add("Personalizado");

        spnn_abrangencia = (Spinner)v.findViewById(R.id.spnn_abrangencia);
        ArrayAdapter<String> listaAbrangencia = new ArrayAdapter<String>(this.getActivity(),android.R.layout.simple_spinner_item,valAbrangencia);
        listaAbrangencia.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnn_abrangencia.setAdapter(listaAbrangencia);


        return v;
    }


}
