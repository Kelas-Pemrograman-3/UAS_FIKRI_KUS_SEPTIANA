package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import adapter.adapter;
import adapter.adapterkeuangan;
import models.keuanganmodel;
import models.matakuliahModel;
import server.ConfigUrl;

public class Keuangan extends AppCompatActivity {

    private ImageView btnback;

    ProgressDialog pDialog;

    adapterkeuangan adapterkeuangan;
    ListView list2;

    ArrayList<keuanganmodel> newsList2 = new ArrayList<keuanganmodel>();

    private RequestQueue mRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keuangan);
        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        btnback = findViewById(R.id.btnback);


        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Keuangan.this, MainMenu.class);
                startActivity(i);
                finish();
            }
        });


        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list2 = (ListView) findViewById(R.id.array_List2);
        newsList2.clear();
        adapterkeuangan = new adapterkeuangan(Keuangan.this, newsList2, mRequestQueue, pDialog);
        list2.setAdapter(adapterkeuangan);

        getAllData();


    }

    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading...");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, ConfigUrl.getkeuangan, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            if (status == false) {
                                String data = response.getString("data");
                                JSONArray jsonArray = new JSONArray(data);
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                                    keuanganmodel uang = new keuanganmodel();
                                    uang.set_id(jsonObject.getString("_id"));
                                    uang.setNamauang(jsonObject.getString("namauang"));
                                    uang.setSemesteruang(jsonObject.getString("semesteruang"));
                                    uang.setSppuang(jsonObject.getString("sppuang"));

                                    newsList2.add(uang);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterkeuangan.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        /* Add your Requests to the RequestQueue to execute */
        mRequestQueue.add(req);
    }

    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(Keuangan.this, MainMenu.class);
        startActivity(i);
        finish();
    }

}
