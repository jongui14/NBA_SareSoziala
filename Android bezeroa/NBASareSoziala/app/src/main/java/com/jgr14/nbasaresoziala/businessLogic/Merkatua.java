package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Eskaintza;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;

import java.util.ArrayList;

/**
 * Created by JONGUI on 04/05/2019.
 */

public class Merkatua {


    public static boolean JokalariaMerkatuanJarri(Jokalaria jokalaria, int hasierakoPrezioa){
        return DataAccess.MerkatuanJarri(Datuak.komunitatea,Datuak.erabiltzailea,jokalaria,hasierakoPrezioa);
    }
    public static boolean OfertaEgin(MerkatukoJokalaria merkatukoJokalaria, int oferta){
        return DataAccess.OfertaEgin(merkatukoJokalaria,Datuak.erabiltzailea,oferta);
    }
    public static boolean EskaintzaErantzun(MerkatukoJokalaria merkatukoJokalaria,Eskaintza eskaintza,boolean onartu){
        return DataAccess.EskaintzaErantzun(merkatukoJokalaria,eskaintza,onartu);
    }


    public static ArrayList<MerkatukoJokalaria> MerkatuaKontsultatu(){
        return DataAccess.MerkatuaKontsultatu(Datuak.komunitatea);
    }


    public static ArrayList<Eskaintza> EgindakoEskaintzak(){
        return DataAccess.EgindakoEskaintzak(Datuak.erabiltzailea);
    }
    public static ArrayList<Eskaintza> EgindakoEskaintzakTramitatuak(ArrayList<Eskaintza> eskaintzak){
        ArrayList<Eskaintza> res = new ArrayList<>();
        for(Eskaintza esk:eskaintzak){
            if(esk.getMerkatukoJokalaria().getTramitatua())res.add(esk);
        }
        return res;
    }
    public static ArrayList<Eskaintza> EgindakoEskaintzakTramitatuGabeak(ArrayList<Eskaintza> eskaintzak){
        ArrayList<Eskaintza> res = new ArrayList<>();
        for(Eskaintza esk:eskaintzak){
            if(!esk.getMerkatukoJokalaria().getTramitatua())res.add(esk);
        }
        return res;
    }

    public static ArrayList<MerkatukoJokalaria> JasotakoEskaintzak(){
        ArrayList<Integer> merkatukoJokalariakID=new ArrayList<>();
        ArrayList<Eskaintza> eskaintzak=DataAccess.JasotakoEskaintzak(Datuak.erabiltzailea);
        ArrayList<MerkatukoJokalaria> res=new ArrayList<>();
        for(Eskaintza eskaintza:eskaintzak){
            if(merkatukoJokalariakID.contains(eskaintza.getMerkatukoJokalaria().getIdMerkatukoJokalaria())){
                for(MerkatukoJokalaria merkatukoJokalaria:res){
                    if(merkatukoJokalaria.getIdMerkatukoJokalaria()==eskaintza.getMerkatukoJokalaria().getIdMerkatukoJokalaria()){
                        merkatukoJokalaria.getEskaintzak().add(eskaintza);
                        break;
                    }
                }
            }else{
                merkatukoJokalariakID.add(eskaintza.getMerkatukoJokalaria().getIdMerkatukoJokalaria());
                MerkatukoJokalaria merkatukoJokalaria=eskaintza.getMerkatukoJokalaria();
                merkatukoJokalaria.getEskaintzak().add(eskaintza);
                res.add(merkatukoJokalaria);
            }
        }
        return res;
    }
    public static ArrayList<MerkatukoJokalaria> JasotakoEskaintzakTramitatuak(ArrayList<MerkatukoJokalaria> jokalariak){
        ArrayList<MerkatukoJokalaria> res = new ArrayList<>();
        for(MerkatukoJokalaria jok:jokalariak){
            if(jok.getTramitatua())res.add(jok);
        }
        return res;
    }
    public static ArrayList<MerkatukoJokalaria> JasotakoEskaintzakTramitatuGabeak(ArrayList<MerkatukoJokalaria> jokalariak){
        ArrayList<MerkatukoJokalaria> res = new ArrayList<>();
        for(MerkatukoJokalaria jok:jokalariak){
            if(!jok.getTramitatua())res.add(jok);
        }
        return res;
    }

}
