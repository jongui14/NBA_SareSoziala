package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ArrayAdapterJokalariarenInformazioGuztia extends ArrayAdapter<Jokalaria> {
    
    private Context context;
    private int resourceId;
    private List<Jokalaria> jokalariak, tempItems, suggestions;
    
    public ArrayAdapterJokalariarenInformazioGuztia(@NonNull Context context, int resourceId, ArrayList<Jokalaria> jokalariak) {
        super(context, resourceId, jokalariak);
        this.jokalariak = jokalariak;
        this.context = context;
        this.resourceId = resourceId;
        tempItems = new ArrayList<>(jokalariak);
        suggestions = new ArrayList<>();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) context).getLayoutInflater();
                v = inflater.inflate(resourceId, parent, false);
            }
            final Jokalaria jok = jokalariak.get(position);

            try{((TextView) v.findViewById(R.id.izena)).setText(jok.izenOsoa());}catch(Exception e){e.printStackTrace();}
            try{((TextView) v.findViewById(R.id.posizioa)).setText(" "+jok.getPosizioa());}catch(Exception e){e.printStackTrace();}
            try{((TextView) v.findViewById(R.id.dorsala)).setText("#"+jok.getDortsala());}catch(Exception e){e.printStackTrace();}

            try{((TextView) v.findViewById(R.id.altuera)).setText(jok.getAltuera()+"m");}catch(Exception e){e.printStackTrace();}
            try{((TextView) v.findViewById(R.id.pisua)).setText(jok.getPisua()+"kg");}catch(Exception e){e.printStackTrace();}
            try{((TextView) v.findViewById(R.id.jaiotze_data)).setText(Egutegia.DateToString(jok.getJaiotzeData()));}catch (Exception e){}
            try{((TextView) v.findViewById(R.id.nazionalitatea)).setText(jok.getNazionalitatea());}catch(Exception e){e.printStackTrace();}

            try {
                Picasso.with(context).load(jok.getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(context).load(jok.getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return v;
    }
    @Nullable
    @Override
    public Jokalaria getItem(int position) {
        return jokalariak.get(position);
    }
    @Override
    public int getCount() {
        return jokalariak.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @NonNull
    @Override
    public Filter getFilter() {
        return jokalariaFilter;
    }
    private Filter jokalariaFilter = new Filter() {
        @Override
        public CharSequence convertResultToString(Object resultValue) {
            Jokalaria jokalaria = (Jokalaria) resultValue;
            return jokalaria.izenOsoa();
        }
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            if (charSequence != null) {
                suggestions.clear();
                for (Jokalaria jokalaria: tempItems) {
                    if (jokalaria.izenOsoa().toLowerCase().startsWith(charSequence.toString().toLowerCase())) {
                        suggestions.add(jokalaria);
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = suggestions;
                filterResults.count = suggestions.size();
                return filterResults;
            } else {
                return new FilterResults();
            }
        }
        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            ArrayList<Jokalaria> tempValues = (ArrayList<Jokalaria>) filterResults.values;
            if (filterResults != null && filterResults.count > 0) {
                clear();
                for (Jokalaria jokalariaObj : tempValues) {
                    add(jokalariaObj);
                    notifyDataSetChanged();
                }
            } else {
                clear();
                notifyDataSetChanged();
            }
        }
    };
}