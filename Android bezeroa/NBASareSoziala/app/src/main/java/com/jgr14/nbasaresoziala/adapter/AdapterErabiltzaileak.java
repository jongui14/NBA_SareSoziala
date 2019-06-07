package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.gui.logeatua.komunitatea.ErabiltzaileakActivity;

import java.util.ArrayList;

/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterErabiltzaileak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Erabiltzailea> erabiltzaileak;
    private LayoutInflater inflater;

    public AdapterErabiltzaileak(Activity activity, ArrayList<Erabiltzailea> erabiltzaileak) {
        this.activity = activity;
        this.erabiltzaileak = erabiltzaileak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return erabiltzaileak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return erabiltzaileak.get(arg0);
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
            v = inf.inflate(R.layout.item_erabiltzailea, null);
        }
        try {
            final Erabiltzailea erab = erabiltzaileak.get(position);
            try { ((TextView)v.findViewById(R.id.nick)).setText(erab.getNick());}catch(Exception e){e.printStackTrace();}
            if(erab.isAdministratzailea()){
                (v.findViewById(R.id.kendu)).setVisibility(View.GONE);
            }else{
                (v.findViewById(R.id.kendu)).setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        final ProgressDialog progressDialog = new ProgressDialog(activity);
                        progressDialog.setMessage(activity.getString(R.string.kargatzen));
                        progressDialog.setTitle(activity.getString(R.string.erabiltzaileak_ezabatu));
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.show();
                        progressDialog.setCancelable(false);

                        new Thread(new Runnable() {
                            public void run() {
                                Datuak.komunitatea.getErabiltzaileas().remove(erab);
                                final boolean zuzena= Komunitateak.ErabiltzaileaEzabatu(erab);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        if(zuzena){
                                            Toast.makeText(activity,activity.getString(R.string.erabiltzailea_ondo_ezabatu_da),Toast.LENGTH_SHORT).show();
                                            activity.startActivity(new Intent(activity, ErabiltzaileakActivity.class));
                                            activity.finish();
                                        }else{
                                            Toast.makeText(activity,activity.getString(R.string.errorea_erabiltzailea_ezabatzean),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                            }
                        }).start();
                    }
                });
            }


        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}