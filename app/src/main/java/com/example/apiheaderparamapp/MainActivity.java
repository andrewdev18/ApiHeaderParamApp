package com.example.apiheaderparamapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView txtResultado;
    RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = findViewById(R.id.txtResult);
        Button btnLoad = findViewById(R.id.btnLoad);
        requestQueue = Volley.newRequestQueue(this);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONRequest();
            }
        });
    }

    private void JSONRequest(){
        String url = "https://fortnite1.p.rapidapi.com/upcoming/get";
        String headerA1 = "authorization";
        String headerA2 = "https://fortnite-api.com/v2/aes";
        String headerB1 = "x-rapidapi-host";
        String headerB2 = "fortnite1.p.rapidapi.com";
        String headerC1 = "x-rapidapi-key";
        String headerC2 = "72ca31b0a3mshb4e387c76c0e6adp174fb2jsn5463d1c259ad";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("data");

                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject object = jsonArray.getJSONObject(i);
                                String name = object.getString("itemId");
                                txtResultado.setText(null);
                                txtResultado.append("Nombre: " + name + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put(headerA1,headerA2);
                headers.put(headerB1,headerB2);
                headers.put(headerC1, headerC2);
                return headers;
            }
        };

        requestQueue.add(request);
    }
}