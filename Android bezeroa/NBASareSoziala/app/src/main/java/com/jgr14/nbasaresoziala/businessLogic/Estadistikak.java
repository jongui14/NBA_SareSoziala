package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.PuntuazioaJokalariaJardunaldia;

import java.util.ArrayList;

/**
 * Created by JONGUI on 06/05/2019.
 */

public class Estadistikak {


    public static ArrayList<PuntuazioaJokalariaJardunaldia> JokalarienPuntuaketak(){
        return DataAccess.JokalarienPuntuaketak();
    }

    public static ArrayList<PuntuazioaJokalariaJardunaldia> JokalariarenPuntuaketak(Jokalaria jokalaria){
        return DataAccess.JokalariarenPuntuaketak(jokalaria);
    }

}
