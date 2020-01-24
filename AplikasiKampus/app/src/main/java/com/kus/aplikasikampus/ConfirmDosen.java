package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;

public class ConfirmDosen extends AppCompatActivity {

    private TextView linkhelp;
    private ImageView btnback;

    private EditText txtnidn, txtpass;
    private Button logindos;

    private RequestQueue mRequestQueue;
    SharedPreferences dSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_dosen);

        linkhelp = findViewById(R.id.linkhelp);
        btnback = findViewById(R.id.btnback);

        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        dSetting = ConfirmDosen.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ConfirmDosen.this, EditProfileDosen.class);
                startActivity(i);
                finish();
            }
        });

        txtnidn = findViewById(R.id.txtconnpmdos);
        txtpass = findViewById(R.id.txtconpassworddos);

        logindos = findViewById(R.id.btnconlogindos);

        logindos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strnidn = txtnidn.getText().toString();
                String strpassdos = txtpass.getText().toString();


                if (strnidn.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NIDN can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strpassdos.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password can't be empty",
                            Toast.LENGTH_LONG).show();
                } else {
                    logindosen(strnidn, strpassdos);

                }
            }

        });

        mRequestQueue = Volley.newRequestQueue(this);
    }

    private void getDatadosen (String id, String nama, String nidn, String email, String notelepon, String tgllahir, String jeniskelamin) {

        SharedPreferences.Editor editor = dSetting.edit();
        editor.putString("_id", id);
        editor.putString("namadosen", nama);
        editor.putString("nidn", nidn);
        editor.putString("email", email);
        editor.putString("notelepon", notelepon);
        editor.putString("tgllahir", tgllahir);
        editor.putString("jeniskelamin", jeniskelamin);


        editor.commit();

    }

    private void logindosen(String nidn, String passdos) {

//        final String URL = "/volley/resource/12";
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();


        params.put("nidn", nidn);
        params.put("password", passdos);


        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.logindosen, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {


                        try {

                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status == true) {
                                msg = response.getString("pesan");
                            } else {
                                msg = response.getString("pesan");
                                String strdata = response.getString("data");
                                JSONObject data = new JSONObject(strdata);
                                String id = data.getString("_id");
                                String email = data.getString("email");
                                String namadosen = data.getString("namadosen");
                                String nidn = data.getString("nidn");
                                String notelepon = data.getString("notelepon");
                                String tgllahir = data.getString("tgllahir");
                                String jeniskelamin = data.getString("jeniskelamin");

                                getDatadosen(id, namadosen, nidn, email, notelepon, tgllahir, jeniskelamin);

                                Intent a = new Intent(ConfirmDosen.this, ProfileDosen.class);
                                startActivity(a);
                                finish();


                            }

                            Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();


                            VolleyLog.v("Response:%n %s", response.toString(4));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


                VolleyLog.e("Error: ", error.getMessage());
            }
        });

        mRequestQueue.add(req);

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ConfirmDosen.this, EditProfile.class);
        startActivity(i);
        finish();
    }

}
