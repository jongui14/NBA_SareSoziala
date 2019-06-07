package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.domain.PuntuazioaErabiltzaileaJardunaldia;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterErabiltzaileenPuntuak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<PuntuazioaErabiltzaileaJardunaldia> erabiltzaileak;
    private LayoutInflater inflater;

    public AdapterErabiltzaileenPuntuak(Activity activity, ArrayList<PuntuazioaErabiltzaileaJardunaldia> erabiltzaileak) {
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
            v = inf.inflate(R.layout.item_klasifikazioa, null);
        }
        try {
            if(position==0){
                (v.findViewById(R.id.fondo)).setBackgroundColor(Koloreak.fondo5);
                ((TextView)v.findViewById(R.id.nick)).setText(activity.getString(R.string.erabiltzaile_nick));
                ((TextView)v.findViewById(R.id.nick)).setTextColor(Koloreak.letras2);
                ((TextView)v.findViewById(R.id.nick)).setTypeface(null, Typeface.ITALIC);
                ((TextView)v.findViewById(R.id.posizioa)).setText("");
                ((TextView)v.findViewById(R.id.puntuak)).setText("");

            }else{
                final PuntuazioaErabiltzaileaJardunaldia erab = erabiltzaileak.get(position);

                try { ((TextView)v.findViewById(R.id.posizioa)).setText(position+"");}catch(Exception e){e.printStackTrace();}
                try { ((TextView)v.findViewById(R.id.nick)).setText(erab.getErabiltzailea().getNick());}catch(Exception e){e.printStackTrace();}
                try { ((TextView)v.findViewById(R.id.puntuak)).setText(erab.getPuntuak()+"");}catch(Exception e){e.printStackTrace();}

            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}