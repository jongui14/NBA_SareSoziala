package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.businessLogic.Merkatua;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by JONGUI on 11/04/2019.
 */

public class AdapterJokalariaMerkatuan extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<MerkatukoJokalaria> jokalariak;
    private LayoutInflater inflater;

    public AdapterJokalariaMerkatuan(Activity activity, ArrayList<MerkatukoJokalaria> jokalariak) {
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
            v = inf.inflate(R.layout.item_jokalaria_merkatuan, null);
        }
        try {
            final MerkatukoJokalaria jok = jokalariak.get(position);

            ((TextView) v.findViewById(R.id.izena)).setText(jok.getJokalaria().izenOsoa());
            ((TextView) v.findViewById(R.id.posizioa)).setText(" "+jok.getJokalaria().getPosizioa());
            ((TextView) v.findViewById(R.id.soldata)).setText(jok.hasierakoPrezioaFormatuarekin());
            try {((TextView) v.findViewById(R.id.textView2)).setText(activity.getString(R.string.posizioa).substring(0,3)+": ");}catch(Exception e){e.printStackTrace();}
            try {
                Picasso.with(activity.getApplicationContext()).load(jok.getJokalaria().getArgazkia()).into((ImageView) v.findViewById(R.id.argazkia));
                Picasso.with(activity.getApplicationContext()).load(jok.getJokalaria().getTaldeaByIdTaldea().getArgazkia()).into((ImageView) v.findViewById(R.id.taldea));
            }catch(Exception e){
                e.printStackTrace();
            }

            final EditText merkatuko_prezioaEditText=(EditText)v.findViewById(R.id.merkatuko_prezioa);
            final Button onartu=(Button) v.findViewById(R.id.onartu);
            onartu.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(activity);
                    progressDialog.setMessage(activity.getString(R.string.kargatzen));
                    progressDialog.setTitle(activity.getString(R.string.oferta_egin));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    new Thread(new Runnable() {
                        public void run() {
                            final int oferta=Integer.parseInt(merkatuko_prezioaEditText.getText().toString());
                            if(oferta> Datuak.erabiltzailea.getDirua()){
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        Toast.makeText(activity,activity.getString(R.string.ezin_duzu_horrelako_oferta_egin),Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }else{
                                final boolean zuzena= Merkatua.OfertaEgin(jok,oferta);
                                activity.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        progressDialog.dismiss();
                                        if(zuzena){
                                            Toast.makeText(activity,activity.getString(R.string.oferta_ondo_egin_da),Toast.LENGTH_SHORT).show();
                                        }else{
                                            Toast.makeText(activity,activity.getString(R.string.errorea_oferta_egitean),Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                            }

                        }
                    }).start();
                }
            });

            try{
                Erabiltzailea erabiltzailea= Komunitateak.erabiltzaileaLortu(jok.getErabiltzaileaByIdErabiltzaileaJabea());
                ((TextView) v.findViewById(R.id.nick_user)).setText(erabiltzailea.getNick());
            }catch (Exception e){}


            try{
                if(jok.getErabiltzaileaByIdErabiltzaileaJabea().getIdErabiltzailea()== Datuak.erabiltzailea.getIdErabiltzailea()){
                    merkatuko_prezioaEditText.setEnabled(false);onartu.setEnabled(false);
                }
            }catch (Exception e){
            }

        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }
}