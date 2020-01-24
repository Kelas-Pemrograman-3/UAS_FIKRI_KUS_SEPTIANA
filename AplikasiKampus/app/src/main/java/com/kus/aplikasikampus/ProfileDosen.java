package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

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

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class ProfileDosen extends AppCompatActivity {

    SharedPreferences mSetting;

    private RequestQueue mRequestQueue;

    ProgressDialog pDialog;

    private TextView linkhelp, namados, npmdos, emaildos, notelepondos, tgllahirdos, jeniskelamindos;
    private Button editprofiledos;
    private ImageView btnback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_dosen);

        linkhelp = findViewById(R.id.linkhelp);
        editprofiledos = findViewById(R.id.btnedtprofildos);
        btnback = findViewById(R.id.btnback);

        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);


        editprofiledos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileDosen.this, EditProfileDosen.class);
                startActivity(i);
                finish();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ProfileDosen.this, MainMenuDosen.class);
                startActivity(i);
                finish();
            }
        });

        mSetting = ProfileDosen.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        namados = findViewById(R.id.namedosen);
        npmdos = findViewById(R.id.tfnidn);
        emaildos = findViewById(R.id.tfemaildos);
        notelepondos = findViewById(R.id.tftelpdos);
        tgllahirdos = findViewById(R.id.tfdatedos);
        jeniskelamindos = findViewById(R.id.tfgenderdos);

        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        String strnidn = mSetting.getString("nidn", "");
        String strnamados = mSetting.getString("namadosen", "");
        String stremaildos = mSetting.getString("email", "");
        String strnotelpdos = mSetting.getString("notelepon", "");
        String strtgllahirdos = mSetting.getString("tgllahir", "");
        String strjkdos = mSetting.getString("jeniskelamin", "");

        namados.setText(strnamados);
        npmdos.setText(strnidn);
        emaildos.setText(stremaildos);
        notelepondos.setText(strnotelpdos);
        tgllahirdos.setText(strtgllahirdos);
        jeniskelamindos.setText(strjkdos);



    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ProfileDosen.this, MainMenuDosen.class);
        startActivity(i);
        finish();
    }

}
