package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Jardunaldia;
import com.jgr14.nbasaresoziala.domain.PuntuazioaErabiltzaileaJardunaldia;

import java.util.ArrayList;

/**
 * Created by JONGUI on 06/05/2019.
 */

public class Klasifikazioak {

    public static ArrayList<PuntuazioaErabiltzaileaJardunaldia> KlasifikazioOrokorra(){
        ArrayList<PuntuazioaErabiltzaileaJardunaldia> res = DataAccess.KlasifikazioOrokorra(Datuak.komunitatea);
        res.add(0,new PuntuazioaErabiltzaileaJardunaldia());
        return res;
    }

    public static ArrayList<Jardunaldia> OrainArtekoJardunaldiak(){
        return DataAccess.OrainArtekoJardunaldiak();
    }

    public static ArrayList<PuntuazioaErabiltzaileaJardunaldia> JornadakoKlasifikazioa(Jardunaldia jardunaldia){
        ArrayList<PuntuazioaErabiltzaileaJardunaldia> res = DataAccess.JornadakoKlasifikazioa(Datuak.komunitatea,jardunaldia);
        res.add(0,new PuntuazioaErabiltzaileaJardunaldia());
        return res;
    }
}
