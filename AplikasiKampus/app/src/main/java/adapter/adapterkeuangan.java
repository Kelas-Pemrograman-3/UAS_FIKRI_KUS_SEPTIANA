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


import models.keuanganmodel;
import models.matakuliahModel;
import server.ConfigUrl;


public class adapterkeuangan extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<keuanganmodel> item;


    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapterkeuangan(Activity activity, List<keuanganmodel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
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
            convertView = inflater.inflate(R.layout.contentkeuangan, null);


        TextView nama = (TextView) convertView.findViewById(R.id.txtnamauang);
        TextView semester = (TextView) convertView.findViewById(R.id.txtsemesteruang);
        TextView spp = (TextView) convertView.findViewById(R.id.txtsppuang);



        nama.setText(item.get(position).getNamauang());
        semester.setText(item.get(position).getSemesteruang());
        spp.setText(item.get(position).getSppuang());

        return convertView;

//        ini buat apa coba hayo ??


    }






}


