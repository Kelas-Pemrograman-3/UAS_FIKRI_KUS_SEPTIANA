package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import java.util.List;

import adapter.adapter;
import models.mahasiswamodel;
import models.matakuliahModel;
import server.ConfigUrl;

public class Profile extends AppCompatActivity {

    SharedPreferences mSetting;

    private RequestQueue mRequestQueue;



    ProgressDialog pDialog;

    private TextView linkhelp, txtnama, txtnpm, txtemail, txtnotelp, txttgllahir, txtjeniskelamin;
    private Button editprfile;
    private ImageView btnback;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();
        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        mSetting = Profile.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        linkhelp = findViewById(R.id.linkhelp);
        editprfile = findViewById(R.id.btnprofedit);
        btnback = findViewById(R.id.btnback);

        txtnama = findViewById(R.id.txvprofnama);
        txtnpm = findViewById(R.id.txvprofnpm);
        txtemail = findViewById(R.id.txvprofemail);
        txtnotelp = findViewById(R.id.txvproftelp);
        txttgllahir = findViewById(R.id.txvprofdate);
        txtjeniskelamin = findViewById(R.id.txvprofgender);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);



        String npm = mSetting.getString("npm", "");
        String nama = mSetting.getString("nama", "");
        String email = mSetting.getString("email", "");
        String notelp = mSetting.getString("notelepon", "");
        String tgllahir = mSetting.getString("tgllahir", "");
        String jk = mSetting.getString("jeniskelamin", "");


        txtnama.setText(nama);
        txtnpm.setText(npm);
        txtemail.setText(email);
        txtnotelp.setText(notelp);
        txttgllahir.setText(tgllahir);
        txtjeniskelamin.setText(jk);






        editprfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent j = new Intent(Profile.this, EditProfile.class);
                startActivity(j);
                finish();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, MainMenu.class);
                startActivity(i);
                finish();
            }
        });


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
        Intent i = new Intent(Profile.this, MainMenu.class);
        startActivity(i);
        finish();
    }

}
