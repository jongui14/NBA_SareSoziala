package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Taldea;

import java.util.ArrayList;

/**
 * Created by JONGUI on 04/05/2019.
 */

public class Taldeak {

    public static ArrayList<Taldea> TaldeakLortu(){
        return DataAccess.TaldeakLortu();
    }

}
