package com.jgr14.nbasaresoziala.gui;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Hizkuntza;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.PartiduenEmaitzakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.PartiduenEmaitzakLogActivity;

import java.util.Locale;

public class KargaActivityActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        Koloreak.InicializarColores(getApplicationContext());

        Egutegia.OrduDiferentziaLortu(getApplicationContext());

        Hizkuntza.HizkuntzaHasieratu(getApplicationContext());
        Locale myLocale = new Locale(Hizkuntza.hizkuntza);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);

        Datuak.ErabiltzaileaIrakurri(getApplicationContext());
        if(Datuak.aurretik_logeatua){
            new Thread(new Runnable() {
                public void run() {
                    Datuak.komunitatea= Komunitateak.KomunitateaLortu();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            startActivity(new Intent(KargaActivityActivity.this, PartiduenEmaitzakLogActivity.class));
                            finish();
                        }
                    });
                }
            }).start();

        }else{
            startActivity(new Intent(KargaActivityActivity.this, PartiduenEmaitzakActivity.class));
            finish();
        }

    }

}
