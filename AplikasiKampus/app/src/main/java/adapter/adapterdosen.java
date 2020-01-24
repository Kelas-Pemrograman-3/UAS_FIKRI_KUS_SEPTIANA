package adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.kus.aplikasikampus.AkademikDosen;
import com.kus.aplikasikampus.Help;
import com.kus.aplikasikampus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import models.matakuliahModel;
import server.ConfigUrl;


public class adapterdosen extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<matakuliahModel> item;


    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapterdosen(Activity activity, List<matakuliahModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
        this.activity = activity;
        this.item = item;
        this.mRequestQueue = mRequestQueue;
        this.pDialog = pDialog;
    }

    @Override
    public int getCount() {
        return item.size();
    }

    @Override
    public Object getItem(int position) {
        return item.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null)
            convertView = inflater.inflate(R.layout.contentdosen, null);


        TextView kodeMk = (TextView) convertView.findViewById(R.id.txtKodeMkdos);
        TextView namaMk = (TextView) convertView.findViewById(R.id.txtNamaMkdos);
        TextView namaDosen = (TextView) convertView.findViewById(R.id.txtNamaDosendos);
        Button btnHapus = (Button) convertView.findViewById(R.id.btnHapus);



        kodeMk.setText(item.get(position).getKodemk());
        namaMk.setText(item.get(position).getNamamk());
        namaDosen.setText(item.get(position).getDosen());

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                builder.setTitle(Html.fromHtml("<font color='#000000'><b>Are you sure to delete this data ?</b></font>"))
                        .setCancelable(false)
                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                hapusData(item.get(position).get_id());
                            }
                        }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        builder.setCancelable(true);
                    }
                }).show();
            }
        });

        return convertView;

//        ini buat apa coba hayo ??


    }

    private void hapusData(String _id) {
        // Pass second argument as "null" for GET requests
        pDialog.setMessage("Loading...");
        showDialog();
        JsonObjectRequest req = new JsonObjectRequest(Request.Method.DELETE, ConfigUrl.deletematkul + _id, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        hideDialog();
                        try {
                            boolean status = response.getBoolean("error");
                            String msg;
                            if (status == true) {
                                msg = response.getString("pesan");
                            } else {
                                msg = response.getString("pesan");
                                Intent i = new Intent(activity, AkademikDosen.class);
                                activity.startActivity(i);
                                activity.finish();
                            }
                            Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
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






}


