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
import adapter.adapterdosen;
import models.dosenmodel;
import models.matakuliahModel;
import server.ConfigUrl;

public class AkademikDosen extends AppCompatActivity {

    ProgressDialog pDialog;

    adapterdosen adapterdosen;
    ListView list3;

    ArrayList<matakuliahModel> newsList3 = new ArrayList<matakuliahModel>();

    private RequestQueue mRequestQueue;

    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akademik_dosen);

        btnback = findViewById(R.id.btnback);

        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        list3 = (ListView) findViewById(R.id.array_List3);
        newsList3.clear();
        adapterdosen = new adapterdosen(AkademikDosen.this, newsList3, mRequestQueue, pDialog);
        list3.setAdapter(adapterdosen);

        getAllData();

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AkademikDosen.this, MainMenuDosen.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void getAllData() {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, ConfigUrl.getmatakuliah, null,
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
                                    matakuliahModel matkul = new matakuliahModel();
                                    matkul.set_id(jsonObject.getString("_id"));
                                    matkul.setKodemk(jsonObject.getString("kodemk"));
                                    matkul.setNamamk(jsonObject.getString("namamk"));
                                    matkul.setJam(jsonObject.getString("jam"));
                                    matkul.setHari(jsonObject.getString("hari"));
                                    matkul.setRuangan(jsonObject.getString("ruangan"));
                                    matkul.setDosen(jsonObject.getString("dosen"));

                                    newsList3.add(matkul);

                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapterdosen.notifyDataSetChanged();
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
        Intent i = new Intent(AkademikDosen.this, MainMenuDosen.class);
        startActivity(i);
        finish();
    }

}
