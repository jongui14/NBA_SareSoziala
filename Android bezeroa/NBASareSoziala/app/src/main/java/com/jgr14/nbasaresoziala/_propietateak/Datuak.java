package com.jgr14.nbasaresoziala._propietateak;

import android.content.Context;

import com.jgr14.nbasaresoziala.domain.Komunitatea;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by JONGUI on 03/05/2019.
 */

public class Datuak {

    public static boolean aurretik_logeatua=false;
    public static Erabiltzailea erabiltzailea=new Erabiltzailea();
    public static Komunitatea komunitatea=new Komunitatea();

    private final static String fitxategia="Erabiltzailea.txt";
    public static void ErabiltzaileaGorde(Context c){
        try{
            FileOutputStream fos = c.openFileOutput(fitxategia,Context.MODE_PRIVATE);
            String txt=     erabiltzailea.getIdErabiltzailea()+"\n"+
                            erabiltzailea.getNick()+"\n"+
                            komunitatea.getIdKomunitatea()+"\n";
            fos.write((txt).getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static void ErabiltzaileaIrakurri(Context c){
        try{
            FileInputStream fin = c.openFileInput(fitxategia);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader br = new BufferedReader(inputStreamReader);
            erabiltzailea=new Erabiltzailea(Integer.parseInt(br.readLine()),br.readLine());
            komunitatea=new Komunitatea(Integer.parseInt(br.readLine()));
            fin.close();
            inputStreamReader.close();
            br.close();
            if(erabiltzailea.getIdErabiltzailea()!=0)aurretik_logeatua=true; else aurretik_logeatua=false;
        }catch (Exception e){
            e.printStackTrace();
            aurretik_logeatua=false;
        }
    }
    public static void SaioaItxi(Context c){
        erabiltzailea=new Erabiltzailea();
        komunitatea=new Komunitatea();
        ErabiltzaileaGorde(c);
    }


}
