package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Mezua;
import com.jgr14.nbasaresoziala.gui.logeatua.komunitatea.KomunitateaActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterMezua extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Mezua> mezuak;
    private LayoutInflater inflater;

    public AdapterMezua(Activity activity, ArrayList<Mezua> mezuak) {
        this.activity = activity;
        this.mezuak = mezuak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return mezuak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return mezuak.get(arg0);
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
            v = inf.inflate(R.layout.item_mezua, null);
        }
        try {
            final Mezua mezua = mezuak.get(position);

            try {((TextView) v.findViewById(R.id.mezua)).setText(mezua.getMezua());}catch(Exception e){e.printStackTrace();}
            try{
                Erabiltzailea erabiltzailea= Komunitateak.erabiltzaileaLortu(mezua.getErabiltzailea());
                ((TextView) v.findViewById(R.id.nick)).setText(erabiltzailea.getNick());
            }catch (Exception e){}
            try {((TextView) v.findViewById(R.id.eguna)).setText(Egutegia.DateToString(mezua.getEguna()));}catch(Exception e){e.printStackTrace();}
            try{
                if(Datuak.erabiltzailea.isAdministratzailea()){
                    (v.findViewById(R.id.kendu)).setOnClickListener(new View.OnClickListener() {
                        public void onClick(View v) {
                            final ProgressDialog progressDialog = new ProgressDialog(activity);
                            progressDialog.setMessage(activity.getString(R.string.kargatzen));
                            progressDialog.setTitle(activity.getString(R.string.mezua_ezabatu));
                            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                            progressDialog.show();
                            progressDialog.setCancelable(false);

                            new Thread(new Runnable() {
                                public void run() {
                                    final boolean zuzena= Komunitateak.MezuaEzabatu(mezua);
                                    activity.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            progressDialog.dismiss();
                                            if(zuzena){
                                                Toast.makeText(activity,activity.getString(R.string.mezua_ondo_ezabatu_da),Toast.LENGTH_SHORT).show();
                                                activity.startActivity(new Intent(activity, KomunitateaActivity.class));
                                                activity.finish();
                                            }else{
                                                Toast.makeText(activity,activity.getString(R.string.errorea_mezua_ezabatzean),Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    });

                                }
                            }).start();
                        }
                    });
                }else{
                    (v.findViewById(R.id.kendu)).setVisibility(View.GONE);
                }
            }catch (Exception e){

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}