package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.Komunitatea;
import com.jgr14.nbasaresoziala.domain.Mezua;

import java.util.ArrayList;

/**
 * Created by JONGUI on 03/05/2019.
 */

public class Komunitateak {

    public static Komunitatea KomunitateaLortu(){
        Komunitatea res=DataAccess.KomunitateaLortu(Datuak.komunitatea.getIdKomunitatea());
        res.getErabiltzaileas().addAll(DataAccess.ErabiltzaileakLortu(new Komunitatea(res.getIdKomunitatea())));
        for(Erabiltzailea erabiltzailea:res.getErabiltzaileas()){
            if(erabiltzailea.getIdErabiltzailea()==Datuak.erabiltzailea.getIdErabiltzailea()){
                Datuak.erabiltzailea=erabiltzailea;
            }
        }
        return res;
    }


    public static Erabiltzailea erabiltzaileaLortu(Erabiltzailea erabiltzailea){
        for(Erabiltzailea res:Datuak.komunitatea.getErabiltzaileas()){
            if(res.getIdErabiltzailea()==erabiltzailea.getIdErabiltzailea()){
                return res;
            }
        }
        return erabiltzailea;
    }



    public static ArrayList<Erabiltzailea> KomunitatekoErabiltzaileak(){
        return Datuak.komunitatea.getErabiltzaileas();
    }


    public static ArrayList<Mezua> KomunitatekoMezuak(){
        return DataAccess.KomunitatekoMezuak(Datuak.komunitatea);
    }

    public static boolean MezuaBidali(String mezua){
        return DataAccess.MezuaBidali(mezua,Datuak.komunitatea,Datuak.erabiltzailea);
    }

    public static boolean ErabiltzaileaEzabatu(Erabiltzailea erabiltzailea){
        return DataAccess.ErabiltzaileaEzabatu(erabiltzailea);
    }
    public static boolean MezuaEzabatu(Mezua mezua){
        return DataAccess.MezuaEzabatu(mezua);
    }

    public static boolean SariakAldatu(int saria1,int saria2,int saria3){
        Datuak.komunitatea.setSaria1(saria1);
        Datuak.komunitatea.setSaria2(saria2);
        Datuak.komunitatea.setSaria3(saria3);
        return DataAccess.KomunitateaGorde(Datuak.komunitatea);
    }
}
