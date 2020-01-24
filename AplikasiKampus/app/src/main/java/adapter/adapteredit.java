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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;

import com.kus.aplikasikampus.EditProfile;
import com.kus.aplikasikampus.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import models.mahasiswamodel;
import server.ConfigUrl;


public class adapteredit extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<mahasiswamodel> item;

    private RequestQueue mRequestQueue;
    ProgressDialog pDialog;

    public adapteredit(Activity activity, List<mahasiswamodel> item, RequestQueue mRequestQueue, ProgressDialog pDialog) {
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
            convertView = inflater.inflate(R.layout.activity_edit_profile, null);


        EditText nama = (EditText) convertView.findViewById(R.id.txtedtnama);
        EditText npm = (EditText) convertView.findViewById(R.id.txtedtnpm);
        EditText email = (EditText) convertView.findViewById(R.id.txtedtemail);
        EditText notelp = (EditText) convertView.findViewById(R.id.txtedtnotelp);
        EditText date = (EditText) convertView.findViewById(R.id.txtedtdate);
        EditText jk = (EditText) convertView.findViewById(R.id.txtedtjk) ;
        Button btnedit = (Button) convertView.findViewById(R.id.btneditprof);


        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, EditProfile.class);
                i.putExtra("_id", item.get(position).get_id());
                i.putExtra("nama", item.get(position).getNamamhs());
                i.putExtra("npm", item.get(position).getNpmmhs());
                i.putExtra("email", item.get(position).getEmailmhs());
                i.putExtra("notelepon", item.get(position).getNoteleponmhs());
                i.putExtra("tgllahir", item.get(position).getTgllahirmhs());
                i.putExtra("jeniskelamin", item.get(position).getJeniskelaminmhs());
                i.putExtra("detailorupdate", "update");
                activity.startActivity(i);
                activity.finish();
            }
        });

//        nama.setText(item.get(position).getNamamhs());

//

        return convertView;
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
