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


public class PostAdapterMascotas extends ArrayAdapter {

    // Atributos
    private RequestQueue requestQueue;
    JsonObjectRequest jsArrayRequest;
    private static final String URL_BASE = "https://vet.parametaleros.com/api/get/Mascotas/idMascotas-Clientes_idClientes-Nombre-Especie-Raza-ColorPelo-FechaNacimiento-Foto/?w=Clientes_idClientes:";
    private static final String URL_JSON = "/social_media.json";
    private static final String TAG = "PostAdapterMascotas";
    List<PostMascotas> items;

    public PostAdapterMascotas(Context context,String idCliente) {
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
                R.layout.list_mascotas,
                parent,
                false) : convertView;


        // Obtener el item actual
        PostMascotas item = items.get(position);

        // Obtener Views
        TextView textoNombre = (TextView) listItemView.
                findViewById(R.id.textoNombreMascotas);
        TextView textoEspecie = (TextView) listItemView.
                findViewById(R.id.textoEspecieMascotas);
        TextView textoRaza = (TextView) listItemView.
                findViewById(R.id.textoRazaMascotas);
        TextView textoPelo = (TextView) listItemView.
                findViewById(R.id.textoPeloMascotas);
        TextView textoFecha = (TextView) listItemView.
                findViewById(R.id.textoFechaMascotas);
        final ImageView imagenPost = (ImageView) listItemView.
                findViewById(R.id.imagenPostMascotas);

        // Actualizar los Views
        textoNombre.setText(item.getNombre());
        textoEspecie.setText(item.getEspecie());
        textoRaza.setText(item.getRaza());
        textoPelo.setText("Color de Pelaje:"+item.getColorPelo());
        textoFecha.setText("Fecha de Nacimiento"+item.getFechaNacimiento());

        // Petición para obtener la imagen
        ImageRequest request = new ImageRequest(
                item.getFoto(),
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap bitmap) {
                        imagenPost.setImageBitmap(bitmap);
                    }
                }, 0, 0, null,null,
                new Response.ErrorListener() {
                    public void onErrorResponse(VolleyError error) {
                        imagenPost.setImageResource(R.drawable.ic_launcher_background);
                        Log.d(TAG, "Error en respuesta Bitmap: "+ error.getMessage());
                    }
                });

        // Añadir petición a la cola
        requestQueue.add(request);


        return listItemView;
    }

    public List<PostMascotas> parseJson(JSONObject jsonObject){
        // Variables locales
        List<PostMascotas> posts = new ArrayList<>();
        JSONArray jsonArray= null;

        try {
            // Obtener el array del objeto
            jsonArray = jsonObject.getJSONArray("data");

            for(int i=0; i<jsonArray.length(); i++){

                try {
                    JSONObject objeto= jsonArray.getJSONObject(i);

                    PostMascotas post = new PostMascotas(
                            objeto.getString("idMascotas"),
                            objeto.getString("Clientes_idClientes"),
                            objeto.getString("Nombre"),
                            objeto.getString("Especie"),
                            objeto.getString("Raza"),
                            objeto.getString("ColorPelo"),
                            objeto.getString("FechaNacimiento"),
                            objeto.getString("Foto"));

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
