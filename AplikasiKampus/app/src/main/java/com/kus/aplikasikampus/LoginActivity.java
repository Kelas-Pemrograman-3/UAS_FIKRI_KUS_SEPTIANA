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

public class LoginActivity extends AppCompatActivity {

    private TextView linkregis;
    private ImageView btnback;

    private EditText npm, password;

    private RequestQueue mRequestQueue;

    private Button btnlogin;

    SharedPreferences mSetting;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mSetting = LoginActivity.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);


        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        linkregis = findViewById(R.id.linkregis);
        btnback = findViewById(R.id.btnback);

        linkregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (LoginActivity.this, Register.class);
                startActivity(i);
                finish();
            }
        });

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, WelcomeActivity.class);
                startActivity(i);
                finish();
            }
        });


        mRequestQueue = Volley.newRequestQueue(this);

        npm = (EditText) findViewById(R.id.txtlognpm);
        password = (EditText) findViewById(R.id.txtlogpassword);


        btnlogin = (Button) findViewById(R.id.btnloglogin);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strnpm = npm.getText().toString();
                String strpassword = password.getText().toString();


                if (strnpm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NPM can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password can't be empty",
                            Toast.LENGTH_LONG).show();
                } else {
                    inputdata(strnpm, strpassword);

                }


            }
        });
    }

    private void getData (String id, String nama, String npm, String email, String notelepon, String tgllahir, String jeniskelamin) {

        SharedPreferences.Editor editor = mSetting.edit();
        editor.putString("_id", id);
        editor.putString("nama", nama);
        editor.putString("npm", npm);
        editor.putString("email", email);
        editor.putString("notelepon", notelepon);
        editor.putString("tgllahir", tgllahir);
        editor.putString("jeniskelamin", jeniskelamin);


        editor.commit();

    }

    private void inputdata(String npm, String password) {

//        final String URL = "/volley/resource/12";
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();


        params.put("npm", npm);
        params.put("password", password);


        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.loginmahasiswa, new JSONObject(params),
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
                                String nama = data.getString("nama");
                                String npm = data.getString("npm");
                                String notelepon = data.getString("notelepon");
                                String tgllahir = data.getString("tgllahir");
                                String jeniskelamin = data.getString("jeniskelamin");

                                getData(id, nama, npm, email, notelepon, tgllahir, jeniskelamin);

                                Intent a = new Intent(LoginActivity.this, MainMenu.class);
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
        Intent i = new Intent(LoginActivity.this, WelcomeActivity.class);
        startActivity(i);
        finish();
    }

}
