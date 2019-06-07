package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


public class ArrayAdapterJokalariarenInformazioa extends ArrayAdapter<Jokalaria> {

    private Context context;
    private int resourceId;
    private int jokalariIzenaID;
    private List<Jokalaria> jokalariak, tempItems, suggestions;

    public ArrayAdapterJokalariarenInformazioa(@NonNull Context context, int resourceId, int jokalariIzenaID, ArrayList<Jokalaria> jokalariak) {
        super(context, resourceId, jokalariIzenaID, jokalariak);
        this.jokalariak = jokalariak;
        this.context = context;
        this.resourceId = resourceId;
        this.jokalariIzenaID = jokalariIzenaID;
        tempItems = new ArrayList<>(jokalariak);
        suggestions = new ArrayList<>();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View v = convertView;
        try {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            v = inflater.inflate(resourceId, parent, false);

            final Jokalaria jok = jokalariak.get(position);

            //TextView izena=(TextView)inflater.inflate(jokalariIzenaID, parent, false);
            //try {izena.setText(jok.izenOsoa());}catch (Exception e){}
            try {((TextView) v.findViewById(R.id.izena)).setText(jok.izenOsoa());}catch (Exception e){}
            try {((TextView) v.findViewById(R.id.posizioa)).setText(" "+jok.getPosizioa());}catch (Exception e){}
            try {((TextView) v.findViewById(R.id.textView2)).setText(context.getString(R.string.posizioa).substring(0,3)+": ");}catch (Exception e){}
            try {((TextView) v.findViewById(R.id.dorsala)).setText("#"+jok.getDortsala());}catch (Exception e){}

            try {
                Picasso.with(context).load(jok.getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(context).load(jok.getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception ex){
            ex.printStackTrace();
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