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
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariaAlineazioa extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Jokalaria> jokalariak;
    private LayoutInflater inflater;

    public AdapterJokalariaAlineazioa(Activity activity, ArrayList<Jokalaria> jokalariak) {
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
            v = inf.inflate(R.layout.item_jokalaria_alineazioa, null);
        }
        try {
            final Jokalaria jok = jokalariak.get(position);

            ((TextView) v.findViewById(R.id.izena)).setText(jok.izenOsoa());
            ((TextView) v.findViewById(R.id.posizioa)).setText(" "+jok.getPosizioa());
            ((TextView) v.findViewById(R.id.dorsala)).setText("#"+jok.getDortsala());

            try {
                Picasso.with(activity.getApplicationContext()).load(jok.getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(activity.getApplicationContext()).load(jok.getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}