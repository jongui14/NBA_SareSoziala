package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Partidua;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class Partiduak {

    public static Date partiduenHasiera=new Date();public static final String partiduenHasieraString="2018-09-01"; //"2018-09-01";
    public static Date partiduenBukaera=new Date();public static final String partiduenBukaeraString="2019-07-01";

    public static int EgunKopurua=0;
    public static void EgunKopuruaHasieratu(){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            partiduenHasiera=dateFormat.parse(partiduenHasieraString);
            partiduenBukaera=dateFormat.parse(partiduenBukaeraString);
            EgunKopurua=(int) ((partiduenBukaera.getTime()-partiduenHasiera.getTime())/86400000);
        }catch (Exception e){
        }
    }
    public static Date IndizetikDataLortu(int numero){
        Calendar cal = Calendar.getInstance();
        cal.setTime(partiduenHasiera);
        cal.add(Calendar.DATE, numero);
        return cal.getTime();
    }
    public static int DatarenIndizeaLortu(Date date){
        try{
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            partiduenHasiera=dateFormat.parse(partiduenHasieraString);
            return (int) ((date.getTime()-partiduenHasiera.getTime())/86400000);
        }catch (Exception e){
            return 10;
        }
    }


    public static ArrayList<Partidua> Eguneko_Partiduak(Date date){
        ArrayList<Partidua> ema = DataAccess.PartiduakLortu(date);
        Collections.sort(ema, new Comparator<Partidua>() {
            public int compare(Partidua p1, Partidua p2) {
                return p1.getOrdua().compareTo(p2.getOrdua());
            }
        });
        return ema;
    }


}
