package com.jgr14.nbasaresoziala._propietateak;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.Locale;

/**
 * Created by JONGUI on 04/06/2019.
 */

public class Hizkuntza {

    public static String hizkuntza ="eu";
    private static String fichero="Hizkuntza.txt";

    public static void HizkuntzaHasieratu(Context context){
        try{
            FileInputStream fin = context.openFileInput(fichero);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            hizkuntza =bufferedReader.readLine();
            fin.close();
            inputStreamReader.close();
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
            hizkuntza ="eu";
            EscribirLenguaje(context);
        }
        //Toast.makeText(context,"hizkuntza: "+hizkuntza,Toast.LENGTH_SHORT).show();
        Locale myLocale = new Locale(Hizkuntza.hizkuntza);
        Resources res = context.getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
    }
    public static void EscribirLenguaje(Context c){
        try{
            FileOutputStream fos = c.openFileOutput(fichero,Context.MODE_PRIVATE);
            fos.write(hizkuntza.getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
