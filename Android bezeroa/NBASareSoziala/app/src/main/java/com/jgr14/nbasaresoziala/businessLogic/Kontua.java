package com.jgr14.nbasaresoziala.businessLogic;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Komunitatea;
import com.jgr14.nbasaresoziala.domain.Transakzioa;

import java.util.ArrayList;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class Kontua {

    public static Erabiltzailea erabiltzailea=new Erabiltzailea();
    public static Komunitatea komunitatea=new Komunitatea();


    public static boolean ErabitzaileaLogeatu(String nick,String pass){
        Erabiltzailea erab= DataAccess.ErabiltzaileaLogeatu(nick,pass);
        Datuak.komunitatea=Kontua.komunitatea;
        if(erab.getIdErabiltzailea()==0){
            return false;
        }else{
            Datuak.komunitatea=Komunitateak.KomunitateaLortu();;
            Datuak.erabiltzailea=erab;
            return true;
        }
    }
    public static boolean ErabitzaileaErregistratu(String nick,String pass,String izen_osoa,
                                                   String email,boolean admin){
        return DataAccess.ErabiltzaileaErregistratu(komunitatea,nick,pass,izen_osoa,email,admin);
    }
    public static boolean KomunitateaAukeratu(String nick,String pass){
        Komunitatea kom= DataAccess.KomunitateaAukeratu(nick, pass);
        if(kom.getIdKomunitatea()==0){
            return false;
        }else{
            komunitatea=kom;
            return true;
        }
    }
    public static boolean KomunitateaErregistratu(String nick,String pass,String izen_osoa,String mezua,
                                                  int saria1,int saria2,int saria3){
        Komunitatea kom= DataAccess.KomunitateaErregistratu(nick, pass,izen_osoa,mezua,saria1,saria2,saria3);
        if(kom.getIdKomunitatea()==0){
            return false;
        }else{
            komunitatea=kom;
            return true;
        }
    }


    public static boolean ErabiltzaileNickLibre(String nick){
        return DataAccess.ErabiltzaileNickLibre(nick);
    }
    public static boolean ErabiltzaileEmailLibre(String email){
        return DataAccess.ErabiltzaileEmailLibre(email);
    }

    public static boolean KomunitateaLibre(String nick){
        return DataAccess.KomunitateaLibre(nick);
    }

    public static void ErabiltzaileaGorde(){
        DataAccess.ErabiltzaileaaGorde(Datuak.erabiltzailea);
    }

    public static boolean PasahitzaEgokia(String pass){
        return DataAccess.PasahitzaOnargarria(pass);
    }



}
