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
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.PuntuazioaJokalariaJardunaldia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariarenPuntuak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<PuntuazioaJokalariaJardunaldia> jokalariak;
    private LayoutInflater inflater;

    public AdapterJokalariarenPuntuak(Activity activity, ArrayList<PuntuazioaJokalariaJardunaldia> jokalariak) {
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
            v = inf.inflate(R.layout.item_jokalaria_puntuak, null);
        }
        try {
            final PuntuazioaJokalariaJardunaldia jok = jokalariak.get(position);

            try{((TextView) v.findViewById(R.id.izena)).setText(jok.getJokalaria().izenOsoa());}catch (Exception e){}
            //((TextView) v.findViewById(R.id.posizioa)).setText(" "+jok.getJokalaria().getPosizioa());
            try{((TextView) v.findViewById(R.id.puntuak)).setText(jok.getPuntuak()+"");}catch (Exception e){}

            try {
                Picasso.with(activity.getApplicationContext()).load(jok.getJokalaria().getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(activity.getApplicationContext()).load(jok.getJokalaria().getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}