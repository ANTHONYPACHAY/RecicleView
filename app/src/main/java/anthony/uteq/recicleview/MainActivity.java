package anthony.uteq.recicleview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import anthony.uteq.recicleview.utiles.Methods;
import anthony.uteq.recicleview.utiles.MyCardAdapter;
import anthony.uteq.recicleview.utiles.MyLogs;
import anthony.uteq.recicleview.utiles.SuperItem;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recView;
    private RecyclerView.Adapter adapter;
    private List<SuperItem> elements;

    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniciarVistas();
        MyLogs.setLabel("MyLogs");
        getDataVolley();
    }

    private void iniciarVistas(){
        //recyclerview del activity main
        recView = findViewById(R.id.idlistview);
    }
    private void setValues(List<SuperItem> data){
        //para indicar la posición del poblamiento de los datos {lista o celda}
        //para celdas usariamos gridLayoutManager
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this);
        recView.setLayoutManager(manager);
        //asignar información
        elements = data;
        //agrega los elementos al adaptador
        adapter = new MyCardAdapter(MainActivity.this,elements);
        //ubicar adaptador
        recView.setAdapter(adapter);
    }

    private List<SuperItem> embebedData(){
        //generar datos, solo para pruebas
        List<SuperItem> data = new ArrayList<>();
        for (int i=0;i<15;i++){
            SuperItem item = new SuperItem();
            item.setTitle("Salidos "+i);
            item.setDate_published("23-04-2000 00:00:00");
            item.setCover("https://revistas.uteq.edu.ec/public/journals/2/cover_issue_82_es_ES.jpg");
            data.add(item);
        }
        return data;
    }
    private void getDataVolley(){
        queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "https://revistas.uteq.edu.ec/ws/issues.php?j_id=2",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        MyLogs.info("ws todo bien");
                        List<SuperItem> data = processResponse(response.toString());
                        setValues(data);
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        MyLogs.error("we error");
                        MessageToast("Error en Volley");
                    }
                }
        );
        queue.add(request);
    }
    private void MessageToast(String message){
        Toast toast= Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
    private List<SuperItem> processResponse(String response){
        List<SuperItem> data = new ArrayList<>();
        JsonArray jarr = Methods.stringToJsonArray(response);
        if(jarr.size() > 0){
            for(int ind = 0; ind <jarr.size(); ind++) {
                JsonObject jso = Methods.JsonElementToJSO(jarr.get(ind));
                if(jso.size() > 0) {
                    Gson gson = new Gson();
                    SuperItem item = gson.fromJson(jso.toString(), SuperItem.class);
                    data.add(item);
                }
            }
        }else{
            MessageToast("No hay registros.");
        }
        return data;
    }
}