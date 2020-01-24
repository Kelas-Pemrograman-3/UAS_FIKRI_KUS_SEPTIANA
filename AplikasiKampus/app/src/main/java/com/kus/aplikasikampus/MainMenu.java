package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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

import server.ConfigUrl;

public class MainMenu extends AppCompatActivity {

    private Button akademik, profil, keuangan;
    private TextView linkhelp, menunama;
    private RequestQueue mRequestQueue;

    SharedPreferences mSetting;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        mSetting = MainMenu.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);



        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        mRequestQueue = Volley.newRequestQueue(this);
        akademik = findViewById(R.id.btnakademik);
        profil = findViewById(R.id.btnprofil);
        linkhelp = findViewById(R.id.linkhelp);
        keuangan = findViewById(R.id.btnkeuangan);
        menunama = findViewById(R.id.txvmenunama);

        String tampilnama = mSetting.getString("nama","");

        menunama.setText(tampilnama);


        akademik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, Akademik.class);
                startActivity(i);
                finish();
            }
        });

        profil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        keuangan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenu.this, Keuangan.class);
                startActivity(i);
                finish();
            }
        });



    }


}
