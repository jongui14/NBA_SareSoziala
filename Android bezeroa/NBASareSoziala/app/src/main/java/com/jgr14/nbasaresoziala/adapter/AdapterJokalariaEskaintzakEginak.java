package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
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
import com.jgr14.nbasaresoziala.businessLogic.Merkatua;
import com.jgr14.nbasaresoziala.domain.Eskaintza;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariaEskaintzakEginak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Eskaintza> eskaintzak;
    private LayoutInflater inflater;

    public AdapterJokalariaEskaintzakEginak(Activity activity, ArrayList<Eskaintza> eskaintzak) {
        this.activity = activity;
        this.eskaintzak = eskaintzak;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return eskaintzak.size();
    }

    @Override
    public Object getItem(int arg0) {
        return eskaintzak.get(arg0);
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
            v = inf.inflate(R.layout.item_jokalaria_eskaintza, null);
        }
        try {
            final Eskaintza esk = eskaintzak.get(position);

            try {((TextView) v.findViewById(R.id.izena)).setText(esk.getMerkatukoJokalaria().getJokalaria().izenOsoa());}catch(Exception e){e.printStackTrace();}
            try {((TextView) v.findViewById(R.id.posizioa)).setText(" "+esk.getMerkatukoJokalaria().getJokalaria().getPosizioa());}catch(Exception e){e.printStackTrace();}
            try {((TextView) v.findViewById(R.id.soldata)).setText(esk.eskaintzaFormatuarekin());}catch(Exception e){e.printStackTrace();}
            try {((TextView) v.findViewById(R.id.textView2)).setText(activity.getString(R.string.posizioa).substring(0,3)+": ");}catch(Exception e){e.printStackTrace();}

            try {
                Picasso.with(activity.getApplicationContext()).load(esk.getMerkatukoJokalaria().getJokalaria().getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(activity.getApplicationContext()).load(esk.getMerkatukoJokalaria().getJokalaria().getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}