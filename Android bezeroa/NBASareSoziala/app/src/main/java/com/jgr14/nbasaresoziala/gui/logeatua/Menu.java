package com.jgr14.nbasaresoziala.gui.logeatua;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.businessLogic.Kontua;
import com.jgr14.nbasaresoziala.gui.KargaActivityActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.estadistikak.JokalariBakarrenPuntuakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.estadistikak.JokalariGuztienPuntuakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.finantzak.FinantzakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.jokalariak.AlineazioaActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.jokalariak.JokalariakLogActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.jokalariak.NireJokalariakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.jokalariak.TaldeakLogActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.klasifikazioa.AzkenKlasifikazioaActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.klasifikazioa.KlasifikazioGeneralaActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.komunitatea.ErabiltzaileakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.komunitatea.KomunitateaActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.komunitatea.SariakAldatuActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.merkatua.EgindakoEskaintzakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.merkatua.JasotakoEskaintzakActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.merkatua.JokalariaMerkatuanJarriActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.merkatua.MerkatuaKontsultatuActivity;

import java.util.ArrayList;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class Menu {

    public static ArrayList<Activity> activities= new ArrayList<>();
    public static boolean eliminar=true;

    public static boolean onNavigationItemSelected(MenuItem item, Activity activity) {
        int id = item.getItemId();eliminar=true;

        if(activity!= PartiduenEmaitzakLogActivity.activity){
            activities.add(activity);
        }

        if (id == R.id.nav_partiduen_emaitzak) {


        } else if (id == R.id.nav_alineazio_publikoak) {
            activity.startActivity(new Intent(activity, AlineazioPublikoakLogActivity.class));
        }

        //Jokalariak
        else if (id == R.id.nav_alineazioa) {
            activity.startActivity(new Intent(activity, AlineazioaActivity.class));
        } else if (id == R.id.nav_nire_jokalariak) {
            activity.startActivity(new Intent(activity, NireJokalariakActivity.class));
        } else if (id == R.id.nav_jokalariak) {
            activity.startActivity(new Intent(activity, JokalariakLogActivity.class));
        } else if (id == R.id.nav_taldeak) {
            activity.startActivity(new Intent(activity, TaldeakLogActivity.class));
        }

        //Merkatua
        else if (id == R.id.nav_merkatua_kontsultatu) {
            activity.startActivity(new Intent(activity, MerkatuaKontsultatuActivity.class));
        } else if (id == R.id.nav_jokalariak_merkatuan_jarri) {
            activity.startActivity(new Intent(activity, JokalariaMerkatuanJarriActivity.class));
        }else if (id == R.id.nav_egindako_eskaintzak) {
            activity.startActivity(new Intent(activity, EgindakoEskaintzakActivity.class));
        } else if (id == R.id.nav_jasotako_eskaintzak) {
            activity.startActivity(new Intent(activity, JasotakoEskaintzakActivity.class));
        }

        //Finantzak
        else if (id == R.id.nav_finantzak) {
            activity.startActivity(new Intent(activity, FinantzakActivity.class));
        }else if (id == R.id.nav_sariak_aldatu) {
            activity.startActivity(new Intent(activity, SariakAldatuActivity.class));
        }

        //Klasifikazioa
        else if (id == R.id.nav_azken_klasifikazioa) {
            activity.startActivity(new Intent(activity, AzkenKlasifikazioaActivity.class));
        } else if (id == R.id.nav_klasifikazio_generala) {
            activity.startActivity(new Intent(activity, KlasifikazioGeneralaActivity.class));
        }

        //Estadistikak
        else if (id == R.id.nav_jokalari_guztien_puntuak) {
            activity.startActivity(new Intent(activity, JokalariGuztienPuntuakActivity.class));
        } else if (id == R.id.nav_jokalari_bakarraren_eboluzioa) {
            activity.startActivity(new Intent(activity, JokalariBakarrenPuntuakActivity.class));
        }

        //Komunitatea
        else if (id == R.id.nav_komunitatea) {
            activity.startActivity(new Intent(activity, KomunitateaActivity.class));
        }else if (id == R.id.nav_erabiltzaileak_ezabatu) {
            activity.startActivity(new Intent(activity, ErabiltzaileakActivity.class));
        } else if (id == R.id.nav_sesioa_itxi) {
            Datuak.SaioaItxi(activity);
            activity.startActivity(new Intent(activity, com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.PartiduenEmaitzakActivity.class));
        }else if (id == R.id.nav_aukerak) {
            activity.startActivity(new Intent(activity, AukerakLogActivity.class));
        }




        if(eliminar)cerrarTodasLasActividades();

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void cerrarTodasLasActividades(){
        for(Activity activity:activities)activity.finish();
    }


    public static void MenuaKonprobatu(NavigationView navigationView){
        android.view.Menu menu=navigationView.getMenu();
        if(!Datuak.erabiltzailea.isAdministratzailea()){
            menu.findItem(R.id.nav_erabiltzaileak_ezabatu).setVisible(false);
            menu.findItem(R.id.nav_sariak_aldatu).setVisible(false);
        }

    }

    private static int kop=0;
    public static void SesioaKonprobatu(Activity activity){
        try{
            if(Datuak.komunitatea.getErabiltzaileas().size()==0){
                //Toast.makeText(activity,"kop: "+kop,Toast.LENGTH_SHORT).show();
                if(kop==0){kop++;
                    activity.startActivity(new Intent(activity, KargaActivityActivity.class));
                    activity.finish();
                }else{kop=0;
                    Datuak.SaioaItxi(activity);
                    activity.startActivity(new Intent(activity, com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.PartiduenEmaitzakActivity.class));
                    activity.finish();
                }

            }
        }catch (Exception e){

        }
    }
}
