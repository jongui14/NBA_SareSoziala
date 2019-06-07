package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.*;

import java.util.ArrayList;

/**
 * Created by JONGUI on 03/05/2019.
 */

public class Jokalariak {


    public static ArrayList<Jokalaria> ErabiltzailearenJokalariakLortu(){
        return DataAccess.ErabiltzailearenJokalariakLortu();
    }
    public static ArrayList<Jokalaria> JokalariGuztiakLortu(){
        return DataAccess.JokalariGuztiakLortu();
    }

    public static Alineazioa AlineazioaLortu(){
        return DataAccess.AlineazioaLortu();
    }
    public static ArrayList<Alineazioa> AlineazioPublikoakLortu(){
        return DataAccess.AlineazioPublikoakLortu();
    }
    public static ArrayList<Alineazioa> KomunitatekoAlineazioPublikoakLortu(ArrayList<Alineazioa> alineazioak){
        ArrayList<Integer> erabiltzaile_ID=new ArrayList<>();
        for(Erabiltzailea erabiltzailea: Komunitateak.KomunitatekoErabiltzaileak()){
            erabiltzaile_ID.add(erabiltzailea.getIdErabiltzailea());
        }
        ArrayList<Alineazioa> res = new ArrayList<>();
        for(Alineazioa alineazioa:alineazioak){
            if(erabiltzaile_ID.contains(alineazioa.getErabiltzailea().getIdErabiltzailea()) ){
                res.add(alineazioa);
            }
        }
        return res;
    }

    public static ArrayList<Jokalaria> JokalariakFiltratu(ArrayList<Jokalaria> jokalariak,String filtro,Alineazioa alineazioa){
        ArrayList<Integer> alineazioarenID=new ArrayList<>();
        alineazioarenID.add(alineazioa.getJokalariaByIdJokalaria1().getIdJokalaria());
        alineazioarenID.add(alineazioa.getJokalariaByIdJokalaria2().getIdJokalaria());
        alineazioarenID.add(alineazioa.getJokalariaByIdJokalaria3().getIdJokalaria());
        alineazioarenID.add(alineazioa.getJokalariaByIdJokalaria4().getIdJokalaria());
        alineazioarenID.add(alineazioa.getJokalariaByIdJokalaria5().getIdJokalaria());

        ArrayList<Jokalaria> res = new ArrayList<>();
        for(Jokalaria jokalaria:jokalariak){
            if(jokalaria.getPosizioa().contains(filtro) && !alineazioarenID.contains(jokalaria.getIdJokalaria()) )
                res.add(jokalaria);
        }
        return res;
    }

    public static Boolean AlineazioaGorde(Alineazioa alineazioa){
        return DataAccess.AlineazioaGorde(Datuak.erabiltzailea,
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria1().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria2().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria3().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria4().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria5().getIdJokalaria()),
                false);
    }

    public static Boolean AlineazioaPublikatu(Alineazioa alineazioa){
        return DataAccess.AlineazioaGorde(Datuak.erabiltzailea,
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria1().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria2().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria3().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria4().getIdJokalaria()),
                new Jokalaria(alineazioa.getJokalariaByIdJokalaria5().getIdJokalaria()),
                true);
    }

}
