package marcocp.vetcontrol;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class ClientesFragment extends Fragment {

    TextView txtsaludo,txtnombre,txttelefono;

/*    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_clientes, container, false);

        txtsaludo=(TextView)view.findViewById(R.id.saludo_cliente);
        txtnombre=(TextView)view.findViewById(R.id.nombre_cliente);
        txttelefono=(TextView)view.findViewById(R.id.telefono_cliente);

        String idclientes = getArguments().getString("idClientes");
        String nombres = getArguments().getString("Nombres");
        String apellidos = getArguments().getString("Apellidos");
        String telefono = getArguments().getString("Telefono");

        txtsaludo.setText("Hola, "+nombres);
        txtnombre.setText(nombres+" "+apellidos);
        txttelefono.setText(telefono);

        return view;
    }
}
