package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.domain.Transakzioa;

import java.util.ArrayList;

/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterTransakzioa extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Transakzioa> transakzioak;
    private LayoutInflater inflater;

    public AdapterTransakzioa(Activity activity, ArrayList<Transakzioa> transakzioak) {
        this.activity = activity;
        this.transakzioak = transakzioak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return transakzioak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return transakzioak.get(arg0);
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
            v = inf.inflate(R.layout.item_transakzioa, null);
        }
        try {
            final Transakzioa transakzioa = transakzioak.get(position);

            try {
                TextView kantitateaTextView=(TextView) v.findViewById(R.id.kantitatea);
                kantitateaTextView.setText(transakzioa.kantitateaFormatuarekin());
                if(transakzioa.getKantitatea()>0){
                    kantitateaTextView.setTextColor(Color.GREEN);
                }else{
                    kantitateaTextView.setTextColor(Color.RED);
                }
            }catch(Exception e){e.printStackTrace();}

            try {
                ((TextView) v.findViewById(R.id.mezua)).setText(transakzioa.getMezua(activity));
            }catch(Exception e){e.printStackTrace();}

            try {((TextView) v.findViewById(R.id.eguna)).setText(Egutegia.DateToString(transakzioa.getEguna()));}catch(Exception e){e.printStackTrace();}


        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}