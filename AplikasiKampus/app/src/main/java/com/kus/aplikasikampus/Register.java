package com.kus.aplikasikampus;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import server.ConfigUrl;

public class Register extends AppCompatActivity {

    private TextView linkhelp;
    private ImageView btnback;
    private Button btnregis;

    private ProgressDialog pDialog;

    private EditText npm, nama, password, email, notelp, date, jk;

    private RequestQueue mRequestQueue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);


        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        linkhelp = findViewById(R.id.linkhelp);
        btnback = findViewById(R.id.btnback);
        btnregis = findViewById(R.id.btnregregis);



        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Register.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });



        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        //tambahin ini juga
        mRequestQueue = Volley.newRequestQueue(this);

        npm = (EditText) findViewById(R.id.txtregnpm);
        nama = (EditText) findViewById(R.id.txtregnama);
        password = (EditText) findViewById(R.id.txtregpass);
        email = (EditText) findViewById(R.id.txtregemail);
        date = findViewById(R.id.txtdate);
        notelp = findViewById(R.id.txtregnotelp);
        jk = findViewById(R.id.txtrefjk);


        btnregis = (Button) findViewById(R.id.btnregregis);

        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strnpm = npm.getText().toString();
                String strnama = nama.getText().toString();
                String strpassword = password.getText().toString();
                String stremail = email.getText().toString();
                String strnotelp = notelp.getText().toString();
                String strdate = date.getText().toString();
                String strjk = jk.getText().toString();

                if (strnpm.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NPM can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strpassword.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Password can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strnama.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Name can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (stremail.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Email can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strnotelp.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Phone number can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strdate.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Birthdate can't be empty",
                            Toast.LENGTH_LONG).show();
                } else if (strjk.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Gender can't be empty",
                            Toast.LENGTH_LONG).show();
                } else {
                    inputdata(strnpm, strpassword, strnama, stremail, strnotelp, strdate, strjk);


                }

            }
        });


    }

    private void inputdata(String npm, String password, String nama, String email, String notelepon, String tgllahir, String jeniskelamin) {

//        final String URL = "/volley/resource/12";
        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();


        params.put("npm", npm);
        params.put("password", password);
        params.put("nama", nama);
        params.put("email", email);
        params.put("notelepon", notelepon);
        params.put("tgllahir", tgllahir);
        params.put("jeniskelamin", jeniskelamin);


//        pDialog.setMessage("Harap sabar");
//        showDialog();

        JsonObjectRequest req = new JsonObjectRequest(ConfigUrl.regismahasiswa, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

//                        hideDialog();

                        try {

                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status == true){
                                msg = response.getString("pesan");
                            } else {
                                msg = response.getString("pesan");
                            }
                            Intent a = new Intent( Register.this, LoginActivity.class);
                            startActivity(a);
                            finish();

                            VolleyLog.v("Response:%n %s", response.toString(4));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

//                hideDialog();

                VolleyLog.e("Error: ", error.getMessage());
            }
        });

// add the request object to the queue to be executed
// ApplicationController.getInstance().addToRequestQueue(req);
        mRequestQueue.add(req);
    }

//    private void showDialog() {
//        if (!pDialog.isShowing())pDialog.show();
//    }
//
//    private void hideDialog() {
//        if (!pDialog.isShowing())pDialog.dismiss();
//    }




    @Override
    public void onBackPressed() {
        Intent i = new Intent(Register.this, LoginActivity.class);
        startActivity(i);
        finish();
    }

}


