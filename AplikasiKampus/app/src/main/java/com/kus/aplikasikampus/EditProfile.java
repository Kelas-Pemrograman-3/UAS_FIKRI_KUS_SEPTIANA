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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import adapter.adapter;
import adapter.adapteredit;
import models.mahasiswamodel;
import models.matakuliahModel;
import server.ConfigUrl;

public class EditProfile extends AppCompatActivity {

    private TextView linkhelp, _id;
    private ImageView btnback;

    private EditText nama, npm, email, notelp, date, jk;
    private Button edit;
    SharedPreferences mSetting;
    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        btnback = findViewById(R.id.btnback);
        linkhelp = findViewById(R.id.linkhelp);

        getSupportActionBar().hide();

        Window g = getWindow();
        g.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.TYPE_STATUS_BAR);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(EditProfile.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

        mSetting = EditProfile.this.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        mRequestQueue = Volley.newRequestQueue(this);
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        nama = findViewById(R.id.txtedtnama);
        npm = findViewById(R.id.txtedtnpm);
        email = findViewById(R.id.txtedtemail);
        notelp = findViewById(R.id.txtedtnotelp);
        date = findViewById(R.id.txtedtdate);
        jk = findViewById(R.id.txtedtjk);
        edit = findViewById(R.id.btneditprof);
        _id = findViewById(R.id.id);

        String s_id = mSetting.getString("_id", "");
        String Npm = mSetting.getString("npm", "");
        String Nama = mSetting.getString("nama", "");
        String Email = mSetting.getString("email", "");
        String Notelp = mSetting.getString("notelepon", "");
        String Tgllahir = mSetting.getString("tgllahir", "");
        String Jk = mSetting.getString("jeniskelamin", "");


        nama.setText(Nama);
        npm.setText(Npm);
        email.setText(Email);
        notelp.setText(Notelp);
        date.setText(Tgllahir);
        jk.setText(Jk);
        _id.setText(s_id);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String sId = _id.getText().toString();
                String strNama = nama.getText().toString();
                String strNpm = npm.getText().toString();
                String strEmail = email.getText().toString();
                String strNotelp = notelp.getText().toString();
                String strDate = date.getText().toString();
                String strJk = jk.getText().toString();

                if (strNama.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Name can't be empty", Toast.LENGTH_LONG).show();
                }else if(strNpm.isEmpty()){
                    Toast.makeText(getApplicationContext(), "NPM can't be empty", Toast.LENGTH_LONG).show();
                }else if(strEmail.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Email can't be empty", Toast.LENGTH_LONG).show();
                }else if(strNotelp.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Phone number can't be empty", Toast.LENGTH_LONG).show();
                }else if(strDate.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Birthdate can't be empty", Toast.LENGTH_LONG).show();
                }else if(strJk.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Gender can't be empty", Toast.LENGTH_LONG).show();
                }
                ubahMatakuliah(sId, strNama, strNpm, strEmail, strNotelp, strDate, strJk);
            }
        });




    }

    private void ubahMatakuliah(String id, String Nama, String Npm, String Email, String Notelp, String Date, String Jk){

        // Post params to be sent to the server
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("_id", id);
        params.put("nama", Nama);
        params.put("npm", Npm);
        params.put("email", Email);
        params.put("notelepon", Notelp);
        params.put("tgllahir", Date);
        params.put("jeniskelamin", Jk);

        pDialog.setMessage("Please wait...");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.PUT, ConfigUrl.updatemahasiswa + id, new JSONObject(params),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if(status == true){
                                msg = response.getString("pesan");
                            }else {
                                msg = response.getString("pesan");
                                Intent i = new Intent(EditProfile.this, Confirm.class);
                                startActivity(i);
                                finish();
                            }
                            Toast.makeText(getApplicationContext(), msg,
                                    Toast.LENGTH_LONG).show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error: ", error.getMessage());
                hideDialog();
            }
        });

        // add the request object to the queue to be executed
        // ApplicationController.getInstance().addToRequestQueue(req);
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
        Intent i = new Intent(EditProfile.this, Profile.class);
        startActivity(i);
        finish();
    }

}
