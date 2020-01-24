package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import org.w3c.dom.Text;

public class MainMenuDosen extends AppCompatActivity {

    private Button akademik, profiledosen;
    private TextView linkhelp, menunamadosen;
    private RequestQueue mRequestQueue;

    SharedPreferences mSetting;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu_dosen);

        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        mSetting = MainMenuDosen.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String tampilnamados = mSetting.getString("namadosen","");

        menunamadosen = findViewById(R.id.txvmenunamados);

        mRequestQueue = Volley.newRequestQueue(this);


        akademik = findViewById(R.id.btnakademikdos);
        linkhelp = findViewById(R.id.linkhelp);
        profiledosen = findViewById(R.id.btnprofildos);

        menunamadosen.setText(tampilnamados);

        akademik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuDosen.this, AkademikDosen.class);
                startActivity(i);
                finish();
            }
        });


        profiledosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainMenuDosen.this, ProfileDosen.class);
                startActivity(i);
                finish();
            }
        });

    }
}
