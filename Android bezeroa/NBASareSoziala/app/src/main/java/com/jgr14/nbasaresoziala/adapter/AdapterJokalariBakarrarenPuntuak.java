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
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.domain.PuntuazioaJokalariaJardunaldia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariBakarrarenPuntuak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<PuntuazioaJokalariaJardunaldia> jokalariak;
    private LayoutInflater inflater;

    public AdapterJokalariBakarrarenPuntuak(Activity activity, ArrayList<PuntuazioaJokalariaJardunaldia> jokalariak) {
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
            v = inf.inflate(R.layout.item_jokalaria_puntuak_bakarra, null);
        }
        try {
            final PuntuazioaJokalariaJardunaldia jok = jokalariak.get(position);

            try{((TextView) v.findViewById(R.id.jardunaldia)).setText((jokalariak.size()-position)+"");}catch (Exception e){}
            try{((TextView) v.findViewById(R.id.puntuak)).setText(jok.getPuntuak()+"");}catch (Exception e){}

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}