package marcocp.vetcontrol;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ConsultasFragment extends Fragment {

    // Atributos
    ListView listView;
    ArrayAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_consultas, container, false);

        String idcliente = getArguments().getString("idClientes");
        listView = (ListView) view.findViewById(R.id.listViewConsultas);
        adapter = new PostAdapterConsultas(view.getContext(),idcliente);

        listView.setAdapter(adapter);

        return view;
    }
}
