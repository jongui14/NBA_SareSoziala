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
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.domain.Alineazioa;
import com.jgr14.nbasaresoziala.domain.Alineazioa;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Komunitatea;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterAlineazioak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Alineazioa> alineazioak;
    private LayoutInflater inflater;

    public AdapterAlineazioak(Activity activity, ArrayList<Alineazioa> alineazioak) {
        this.activity = activity;
        this.alineazioak = alineazioak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return alineazioak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return alineazioak.get(arg0);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inf = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inf.inflate(R.layout.item_alineazio_publikoak, null);

        try {
            final Alineazioa alineazioa = alineazioak.get(position);

            try{
                Erabiltzailea erabiltzailea= Komunitateak.erabiltzaileaLortu(alineazioa.getErabiltzailea());
                ((TextView) v.findViewById(R.id.erabiltzailea)).setText(erabiltzailea.getNick());
            }catch (Exception e){((TextView) v.findViewById(R.id.erabiltzailea)).setText("");}

            try {
                Picasso.with(activity.getApplicationContext()).load(alineazioa.getJokalariaByIdJokalaria1().getArgazkia()).into((ImageView) v.findViewById(R.id.jokalaria1));
                Picasso.with(activity.getApplicationContext()).load(alineazioa.getJokalariaByIdJokalaria2().getArgazkia()).into((ImageView) v.findViewById(R.id.jokalaria2));
                Picasso.with(activity.getApplicationContext()).load(alineazioa.getJokalariaByIdJokalaria3().getArgazkia()).into((ImageView) v.findViewById(R.id.jokalaria3));
                Picasso.with(activity.getApplicationContext()).load(alineazioa.getJokalariaByIdJokalaria4().getArgazkia()).into((ImageView) v.findViewById(R.id.jokalaria4));
                Picasso.with(activity.getApplicationContext()).load(alineazioa.getJokalariaByIdJokalaria5().getArgazkia()).into((ImageView) v.findViewById(R.id.jokalaria5));
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}