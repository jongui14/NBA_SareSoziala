package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala.domain.Taldea;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterTaldea extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Taldea> taldeak;
    private LayoutInflater inflater;

    public AdapterTaldea(Activity activity, ArrayList<Taldea> taldeak) {
        this.activity = activity;
        this.taldeak = taldeak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return taldeak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return taldeak.get(arg0);
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
            v = inf.inflate(R.layout.item_taldea, null);
        }
        try {
            final Taldea taldea = taldeak.get(position);

            TextView nombreEquipo = (TextView) v.findViewById(R.id.taldeIzena);
            nombreEquipo.setText(taldea.getIzenOsoa());

            try {
                ImageView imagenEquipo = (ImageView) v.findViewById(R.id.argazkia);
                Picasso.with(activity.getApplicationContext()).load(taldea.getArgazkia()).into(imagenEquipo);
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}