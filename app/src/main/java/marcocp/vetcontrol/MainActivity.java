package marcocp.vetcontrol;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    EditText dni;
    Button consultar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dni = (EditText)findViewById(R.id.dni);
        consultar = (Button)findViewById(R.id.consultar);
        requestQueue = Volley.newRequestQueue(this);

        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                        "https://vet.parametaleros.com/api/get/Clientes/idClientes-Nombres-Apellidos-Telefono-DNI/?w=DNI:"+dni.getText().toString(),null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONArray jsonArray = response.getJSONArray("data");
                                    for (int i = 0; i < jsonArray.length(); i++){
                                        JSONObject cliente = jsonArray.getJSONObject(i);

                                        String idClientes = cliente.getString("idClientes");
                                        String Nombres = cliente.getString("Nombres");
                                        String Apellidos = cliente.getString("Apellidos");
                                        String Telefono = cliente.getString("Telefono");
                                        String DNI = cliente.getString("DNI");

                                        if (DNI.equals(dni.getText().toString())){
                                            Toast.makeText(getApplicationContext(),"Bienvenido "+Nombres,Toast.LENGTH_LONG).show();

                                            Intent intent = new Intent(getApplicationContext(),DetallesActivity.class);
                                            intent.putExtra("idClientes",idClientes);
                                            intent.putExtra("Nombres",Nombres);
                                            intent.putExtra("Apellidos",Apellidos);
                                            intent.putExtra("Telefono",Telefono);
                                            startActivity(intent);
                                        }
                                    }

                                } catch (JSONException e) {
                                    Toast.makeText(getApplicationContext(),"Su DNI no esta registrado",Toast.LENGTH_LONG).show();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Log.i("Volley", error.toString());
                            }
                        }

                );
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
