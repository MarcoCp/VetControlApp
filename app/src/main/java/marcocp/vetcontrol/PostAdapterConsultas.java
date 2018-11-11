package marcocp.vetcontrol;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class PostAdapterConsultas extends ArrayAdapter {

    // Atributos
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = "https://vet.parametaleros.com/api/get/Consultas/idConsultas-Mascotas_Clientes_idClientes-Mascotas_idMascotas-Fecha-Consulta-Descripcion-Costo/?w=Mascotas_Clientes_idClientes:";
    private static final String URL_JSON = "/social_media.json";
    private static final String TAG = "PostAdapterConsulta";
    List<PostConsultas> items;

    public PostAdapterConsultas(Context context, String idCliente) {
        super(context,0);

        // Crear nueva cola de peticiones
        requestQueue= Volley.newRequestQueue(context);

        // Nueva petición JSONObject
        jsArrayRequest = new JsonObjectRequest(
                Request.Method.GET,
                URL_BASE + idCliente,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        items = parseJson(response);
                        notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "Error Respuesta en JSON: " + error.getMessage());

                    }
                }
        );

        // Añadir petición a la cola
        requestQueue.add(jsArrayRequest);
    }

    @Override
    public int getCount() {
        return items != null ? items.size() : 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        // Referencia del view procesado
        View listItemView;

        //Comprobando si el View no existe
        listItemView = null == convertView ? layoutInflater.inflate(
                R.layout.list_consultas,
                parent,
                false) : convertView;


        // Obtener el item actual
        PostConsultas item = items.get(position);

        // Obtener Views
        TextView textoConsulta = (TextView) listItemView.
                findViewById(R.id.textoConsultaConsultas);
        final TextView textoMascotas = (TextView) listItemView.
                findViewById(R.id.textoMascotaConsultas);
        TextView textoDescripcion = (TextView) listItemView.
                findViewById(R.id.textoDescripcionConsultas);
        TextView textoFecha = (TextView) listItemView.
                findViewById(R.id.textoFechaConsultas);
        TextView textoCosto = (TextView) listItemView.
                findViewById(R.id.textoCostoConsultas);

        //Obtener nombre de la Mascota
        //RequestQueue request = Volley.newRequestQueue(parent.getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://vet.parametaleros.com/api/get/Mascotas/Nombre/?w=idMascotas:"+item.getIdMascota().toString(),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");
                            for (int i = 0; i < jsonArray.length(); i++){
                                JSONObject mascota = jsonArray.getJSONObject(i);

                                String nombreMascota = mascota.getString("Nombre");

                                textoMascotas.setText(nombreMascota);
                            }

                        } catch (JSONException e) {
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

        // Actualizar los Views
        textoConsulta.setText(item.getConsulta());
        //textoMascotas.setText(item.getIdMascota());
        textoDescripcion.setText(item.getDescripcion());
        textoFecha.setText("Fecha: "+item.getFecha());
        textoCosto.setText("S/ "+item.getCosto());

        return listItemView;
    }

    public List<PostConsultas> parseJson(JSONObject jsonObject){
        // Variables locales
        List<PostConsultas> posts = new ArrayList<>();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("data");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    PostConsultas post = new PostConsultas(
                            objeto.getString("idConsultas"),
                            objeto.getString("Mascotas_Clientes_idClientes"),
                            objeto.getString("Mascotas_idMascotas"),
                            objeto.getString("Fecha"),
                            objeto.getString("Consulta"),
                            objeto.getString("Descripcion"),
                            objeto.getString("Costo"));

                    posts.add(post);

                } catch (JSONException e) {
                    Log.e(TAG, "Error de parsing: "+ e.getMessage());
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return posts;
    }
}
