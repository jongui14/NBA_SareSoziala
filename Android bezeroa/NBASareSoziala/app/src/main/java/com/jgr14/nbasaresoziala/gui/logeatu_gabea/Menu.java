package com.jgr14.nbasaresoziala.gui.logeatu_gabea;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.gui.Ajustes;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.AlineazioPublikoakActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.AukerakActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.JokalariakActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.PartiduenEmaitzakActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.TaldeakActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.kontua.KomunitateBerriaActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.kontua.KomunitateaAukeratuActivity;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.kontua.KonturaSartuActivity;
import com.jgr14.nbasaresoziala.gui.logeatua.PartiduenEmaitzakLogActivity;

import java.util.ArrayList;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class Menu {

    public static ArrayList<Activity> activities= new ArrayList<>();
    public static boolean eliminar=true;

    public static boolean onNavigationItemSelected(MenuItem item, Activity activity) {
        int id = item.getItemId();eliminar=true;

        if(activity!= PartiduenEmaitzakActivity.activity){
            activities.add(activity);
        }


        if (id == R.id.nav_partiduen_emaitzak) {
            //activity.startActivity(new Intent(activity, PartiduenEmaitzakLogActivity.class));

        } else if (id == R.id.nav_jokalariak) {
            activity.startActivity(new Intent(activity, JokalariakActivity.class));

        } else if (id == R.id.nav_taldeak) {
            activity.startActivity(new Intent(activity, TaldeakActivity.class));
        }
        else if (id == R.id.nav_alineazio_publikoak) {
            activity.startActivity(new Intent(activity, AlineazioPublikoakActivity.class));

        }else if (id == R.id.nav_komunitate_berria) {
            activity.startActivity(new Intent(activity, KomunitateBerriaActivity.class));

        }else if (id == R.id.nav_erabiltzaile_berria) {
            activity.startActivity(new Intent(activity, KomunitateaAukeratuActivity.class));

        }else if (id == R.id.nav_kontura_sartu) {
            activity.startActivity(new Intent(activity, KonturaSartuActivity.class));

        }else if (id == R.id.nav_aukerak) {
            activity.startActivity(new Intent(activity, AukerakActivity.class));

        }else if (id == R.id.nav_ip) {
            activity.startActivity(new Intent(activity, Ajustes.class));
            eliminar=false;
        }

        if(eliminar)cerrarTodasLasActividades();

        DrawerLayout drawer = (DrawerLayout) activity.findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public static void cerrarTodasLasActividades(){
        for(Activity activity:activities)activity.finish();
    }
}
