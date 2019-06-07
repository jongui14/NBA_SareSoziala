package com.jgr14.nbasaresoziala.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.domain.Partidua;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by JONGUI on 11/04/2019.
 */


public class AdapterPartidua extends BaseAdapter {

    protected Activity activity;
    protected ArrayList<Partidua> partiduak;

    public AdapterPartidua(Activity activity, ArrayList<Partidua> partiduak) {
        this.activity = activity;
        this.partiduak = partiduak;
    }

    @Override
    public int getCount() {
        return partiduak.size();
    }

    public void clear() {
        partiduak.clear();
    }

    public void addAll(ArrayList<Partidua> category) {
        for (int i = 0; i < category.size(); i++) {
            partiduak.add(category.get(i));
        }
    }

    @Override
    public Object getItem(int arg0) {
        return partiduak.get(arg0);
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
            v = inf.inflate(R.layout.item_partidua, null);
        }
        try {
            ((ImageView)v.findViewById(R.id.botonJugando)).setVisibility(View.GONE);
            Partidua partidua = partiduak.get(position);

            TextView ordua = (TextView) v.findViewById(R.id.ordua);ordua.setTextColor(Koloreak.letras3);
            TextView puntosVisitante = (TextView) v.findViewById(R.id.puntosVisitante);
            TextView puntosLocal = (TextView) v.findViewById(R.id.puntosLocal);
            TextView nombreLocal = (TextView) v.findViewById(R.id.nombreLocal);
            TextView nombreVisitante = (TextView) v.findViewById(R.id.nombreVisitante);

            if(partidua.getPartiduaren_egoera()==3){//bukatuta
                ordua.setText(partidua.PartiduaBukatuaZatia());

                int colorLocal=Koloreak.letras1,colorVisitante=Koloreak.letras1;
                if(partidua.getEtxeko_puntuak()>partidua.getKanpoko_puntuak()){
                    colorVisitante=Koloreak.letras2;
                }else{
                    colorLocal= Koloreak.letras2;
                }

                puntosLocal.setText(partidua.getEtxeko_puntuak()+"");
                puntosLocal.setTextColor(colorLocal);
                nombreLocal.setText(partidua.getEtxeko_taldea().getUrlIzena());
                nombreLocal.setTextColor(colorLocal);

                puntosVisitante.setText(partidua.getKanpoko_puntuak()+"");
                puntosVisitante.setTextColor(colorVisitante);
                nombreVisitante.setText(partidua.getKanpoko_taldea().getUrlIzena());
                nombreVisitante.setTextColor(colorVisitante);


            }else if(partidua.getPartiduaren_egoera()==2){//jolasten
                BotonRojo(activity,(ImageView)v.findViewById(R.id.botonJugando));
                ordua.setText(Egutegia.TiempoCuartoToString(partidua.getErlojua()));
                ordua.setTextColor(Koloreak.letras3);
                ((TextView) v.findViewById(R.id.zatia)).setText(partidua.PartidukoZatia());

                puntosVisitante.setText(partidua.getKanpoko_puntuak()+"");
                puntosVisitante.setTextColor(Koloreak.letras2);
                puntosLocal.setText(partidua.getEtxeko_puntuak()+"");
                puntosLocal.setTextColor(Koloreak.letras2);

                nombreLocal.setText(partidua.getEtxeko_taldea().getUrlIzena());
                nombreLocal.setTextColor(Koloreak.letras1);
                nombreVisitante.setText(partidua.getKanpoko_taldea().getUrlIzena());
                nombreVisitante.setTextColor(Koloreak.letras1);


            }else if(partidua.getPartiduaren_egoera()==1){//jokatu gabe
                ordua.setText(Egutegia.TimeToString(partidua.getOrdua()));
                ordua.setTextColor(Koloreak.letras3);

                puntosVisitante.setText("");//record
                puntosVisitante.setTextColor(Koloreak.letras2);
                puntosVisitante.setTextSize(12);
                puntosLocal.setText("");//record
                puntosLocal.setTextColor(Koloreak.letras2);
                puntosLocal.setTextSize(12);

                nombreLocal.setText(partidua.getKanpoko_taldea().getUrlIzena());
                nombreLocal.setTextColor(Koloreak.letras1);
                nombreVisitante.setText(partidua.getEtxeko_taldea().getUrlIzena());
                nombreVisitante.setTextColor(Koloreak.letras1);
            }




            try {
                Picasso.with(activity.getApplicationContext()).load(partidua.getKanpoko_taldea().getArgazkia()).into((ImageView) v.findViewById(R.id.fotoVisitante));
            }catch(Exception e){
                e.printStackTrace();
            }
            try {
                Picasso.with(activity.getApplicationContext()).load(partidua.getEtxeko_taldea().getArgazkia()).into((ImageView) v.findViewById(R.id.fotoLocal));
            }catch(Exception e){
                e.printStackTrace();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }
        return v;
    }


    public static void BotonRojo(final Activity activity,final ImageView imageView){
        new Thread(new Runnable() {
            public void run() {
                while(true){
                    try {Thread.sleep(600);} catch (InterruptedException e) {e.printStackTrace();}

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setVisibility(View.GONE);
                        }
                    });

                    try {Thread.sleep(600);} catch (InterruptedException e) {e.printStackTrace();}

                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            imageView.setVisibility(View.VISIBLE);
                        }
                    });
                }
            }
        }).start();
    }

}

