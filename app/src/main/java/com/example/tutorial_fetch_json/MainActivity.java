package com.example.tutorial_fetch_json;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Create an app to fetch weather data from remote server
 * We will be using volley library to make requests
 */
public class MainActivity extends AppCompatActivity {
    private String TAG = "mutall";
    ArrayList plant_names;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.mylist);

        plant_names = new ArrayList();

        getDataFromServer();


    }


    public void getDataFromServer(){

        //Creating a new request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //string url to remote server
        String sam_url = "http://mutall.co.ke/sam_json/data.json";

        //create a new json array request
        JsonArrayRequest myRequest = new JsonArrayRequest(sam_url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i<response.length(); i++) {
                        JSONObject object = response.getJSONObject(i);
                        String plant_name = object.getString("crop");
                        String soil = object.getString("soil");
                        String temperature = object.getString("temperature");
                        String rainfall = object.getString("rainfall");
                        String image = object.getString("image");

//                        Crop crop = new Crop(plant_name, soil, temperature,rainfall, image);
                        plant_names.add(soil);
                    }
                    ArrayAdapter adp = new ArrayAdapter(MainActivity.this, R.layout.support_simple_spinner_dropdown_item, plant_names);
                    listView.setAdapter(adp);

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("mutall", error.getMessage());
            }
        });
        requestQueue.add(myRequest);

    }
}
