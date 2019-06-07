package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.businessLogic.Merkatua;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariaMerkatuanJarri extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Jokalaria> jokalariak;
    private LayoutInflater inflater;

    public AdapterJokalariaMerkatuanJarri(Activity activity, ArrayList<Jokalaria> jokalariak) {
        this.activity = activity;
        this.jokalariak = jokalariak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return jokalariak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return jokalariak.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inf.inflate(R.layout.item_jokalaria_merkatuanjarri, null);
        }
        try {
            final Jokalaria jok = jokalariak.get(position);

            ((TextView) v.findViewById(R.id.izena)).setText(jok.izenOsoa());
            ((TextView) v.findViewById(R.id.posizioa)).setText(" "+jok.getPosizioa());
            ((TextView) v.findViewById(R.id.soldata)).setText(jok.soldatFormatuarekin());
            try {((TextView) v.findViewById(R.id.textView2)).setText(activity.getString(R.string.posizioa).substring(0,3)+": ");}catch(Exception e){e.printStackTrace();}
            try {
                Picasso.with(activity.getApplicationContext()).load(jok.getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(activity.getApplicationContext()).load(jok.getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }

            final EditText merkatuko_prezioaEditText=(EditText)v.findViewById(R.id.merkatuko_prezioa);

            (v.findViewById(R.id.onartu)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage(activity.getString(R.string.kargatzen));
                    progressDialog.setTitle(activity.getString(R.string.jokalariak_merkatuan_jarri));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        public void run() {
                            final int merkatuko_prezioa=Integer.parseInt(merkatuko_prezioaEditText.getText().toString());
                            if(merkatuko_prezioa>0){
                                final boolean zuzena= Merkatua.JokalariaMerkatuanJarri(jok,merkatuko_prezioa);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        if(zuzena){
                                            merkatuko_prezioaEditText.setText("");
                                            Toast.makeText(activity,activity.getString(R.string.jokalaria_merkatuan_jarri_da),Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(activity,activity.getString(R.string.errorea_jokalaria_merkatuan_jartzean),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }else{
                                progressDialog.dismiss();
                            }
                        }
                    }).start();
                }
            });

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}