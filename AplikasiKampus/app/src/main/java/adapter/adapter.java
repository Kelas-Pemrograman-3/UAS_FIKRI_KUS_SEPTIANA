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
import com.kus.aplikasikampus.Help;
import com.kus.aplikasikampus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


import models.matakuliahModel;
import server.ConfigUrl;


public class adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<matakuliahModel> item;


    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapter(Activity activity, List<matakuliahModel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
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
            convertView = inflater.inflate(R.layout.contentmatakuliah, null);


        TextView kodeMk = (TextView) convertView.findViewById(R.id.txtKodeMk);
        TextView namaMk = (TextView) convertView.findViewById(R.id.txtNamaMk);
        TextView namaDosen = (TextView) convertView.findViewById(R.id.txtNamaDosen);



        kodeMk.setText(item.get(position).getKodemk());
        namaMk.setText(item.get(position).getNamamk());
        namaDosen.setText(item.get(position).getDosen());

        return convertView;

//        ini buat apa coba hayo ??


    }






    }


