package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.businessLogic.Merkatua;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Eskaintza;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariaEskaintzakJasotakoak extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<MerkatukoJokalaria> jokalariak;
    private LayoutInflater inflater;

    public static int aukeratutako_balioa=0;
    public static Eskaintza aukeratutako_eskaintza=new Eskaintza();

    public AdapterJokalariaEskaintzakJasotakoak(Activity activity, ArrayList<MerkatukoJokalaria> jokalariak) {
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
            v = inf.inflate(R.layout.item_jokalaria_eskaintza_jaso, null);
        }
        try {
            final MerkatukoJokalaria jok = jokalariak.get(position);
            final TextView eskaintzaTextView=(TextView) v.findViewById(R.id.eskaintza);

            try {((TextView) v.findViewById(R.id.izena)).setText(jok.getJokalaria().izenOsoa());}catch(Exception e){e.printStackTrace();}
            try {((TextView) v.findViewById(R.id.posizioa)).setText(jok.getJokalaria().getPosizioa());}catch(Exception e){e.printStackTrace();}
            try {((TextView) v.findViewById(R.id.soldata)).setText(jok.hasierakoPrezioaFormatuarekin());}catch(Exception e){e.printStackTrace();}
            try {((TextView) v.findViewById(R.id.textView2)).setText(activity.getString(R.string.posizioa).substring(0,3)+": ");}catch(Exception e){e.printStackTrace();}
            try {
                if(jok.getOnartua())
                    eskaintzaTextView.setText(jok.eskaintzaIrabazleaFormatuarekin());
            }catch(Exception e){e.printStackTrace();}

            try {
                Picasso.with(activity.getApplicationContext()).load(jok.getJokalaria().getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(activity.getApplicationContext()).load(jok.getJokalaria().getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }

            try{
                final RadioGroup rgp = (RadioGroup) v.findViewById(R.id.radiogroup);
                for(final Eskaintza eskaintza:jok.getEskaintzak()){
                    try{
                        Erabiltzailea erabiltzailea= Komunitateak.erabiltzaileaLortu(eskaintza.getErabiltzailea());

                        RadioButton rbn = new RadioButton(activity);
                        rbn.setId(eskaintza.getIdEskaintza());
                        rbn.setText(eskaintza.eskaintzaFormatuarekin()+"   "+erabiltzailea.getNick());
                        rgp.addView(rbn);
                        if(jok.getOnartua() && jok.getEskaintzaIrabazlea()==eskaintza.getEskaintza() && jok.getErabiltzaileaByIdErabiltzaileaIrabazlea().getIdErabiltzailea()==eskaintza.getErabiltzailea().getIdErabiltzailea() ){
                            aukeratutako_balioa=eskaintza.getEskaintza();
                            rgp.setSelected(true);
                        }
                        rbn.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                aukeratutako_balioa=eskaintza.getEskaintza();
                                aukeratutako_eskaintza=eskaintza;
                            }
                        });

                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }catch(Exception e){
                e.printStackTrace();
            }

            (v.findViewById(R.id.onartu)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage(activity.getString(R.string.kargatzen));
                    progressDialog.setTitle(activity.getString(R.string.jasotako_eskaintzak));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        public void run() {
                            if(aukeratutako_balioa!=0){
                                final boolean zuzena= Merkatua.EskaintzaErantzun(jok,aukeratutako_eskaintza,true);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        if(zuzena){
                                            eskaintzaTextView.setText(aukeratutako_eskaintza.eskaintzaFormatuarekin());
                                            Toast.makeText(activity,activity.getString(R.string.oferta_ondo_onartu_da),Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(activity,activity.getString(R.string.errorea_oferta_onartzean),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }
                        }
                    }).start();
                }
            });
            (v.findViewById(R.id.ezeztatu)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage(activity.getString(R.string.kargatzen));
                    progressDialog.setTitle(activity.getString(R.string.jasotako_eskaintzak));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        public void run() {
                            final boolean zuzena= Merkatua.EskaintzaErantzun(jok,new Eskaintza(),false);
                            activity.runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    progressDialog.dismiss();
                                    if(zuzena){
                                        Toast.makeText(activity,activity.getString(R.string.oferta_ondo_ezeztatu_da),Toast.LENGTH_SHORT).show();
                                    }else{
                                        Toast.makeText(activity,activity.getString(R.string.errorea_oferta_ezeztatzean),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }
                    }).start();
                }
            });

            if(jok.getTramitatua()){
                (v.findViewById(R.id.onartu)).setEnabled(false);
                (v.findViewById(R.id.ezeztatu)).setEnabled(false);
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}