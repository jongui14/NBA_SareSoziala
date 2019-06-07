package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Transakzioa;

import java.util.ArrayList;

/**
 * Created by JONGUI on 09/05/2019.
 */

public class Finantzak {

    public static ArrayList<Transakzioa> ErabiltzailearenTransakzioak(){
        return DataAccess.ErabiltzailearenTransakzioak(Datuak.erabiltzailea);
    }

}
