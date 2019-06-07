package com.jgr14.nbasaresoziala.dataAccess;

import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.domain.Alineazioa;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Eskaintza;
import com.jgr14.nbasaresoziala.domain.Jardunaldia;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.Komunitatea;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;
import com.jgr14.nbasaresoziala.domain.Mezua;
import com.jgr14.nbasaresoziala.domain.Puntuazioa;
import com.jgr14.nbasaresoziala.domain.PuntuazioaErabiltzaileaJardunaldia;
import com.jgr14.nbasaresoziala.domain.PuntuazioaJokalariaJardunaldia;
import com.jgr14.nbasaresoziala.domain.Taldea;
import com.jgr14.nbasaresoziala.domain.Transakzioa;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;

/**
 * Created by JONGUI on 08/05/2019.
 */

public class KastingFuntzioak {

    public static Erabiltzailea erabiltzailea(SoapObject object){
        Erabiltzailea res=new Erabiltzailea();
        try{res.setIdErabiltzailea(Integer.parseInt(object.getProperty("idErabiltzailea").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setNick(object.getProperty("nick").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setEmail(object.getProperty("email").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setIzenOsoa(object.getProperty("izenOsoa").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setDirua(Integer.parseInt(object.getProperty("dirua").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setAdministratzailea(Boolean.parseBoolean(object.getProperty("administratzailea").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setHizkuntza(object.getProperty("hizkuntza").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setKoloreak(Integer.parseInt(object.getProperty("koloreak").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setOrduDiferentzia(Integer.parseInt(object.getProperty("orduDiferentzia").toString()));}catch (Exception e){e.printStackTrace();}
        return res;
    }

    public static Komunitatea komunitatea(SoapObject object){
        Komunitatea res=new Komunitatea();
        try{res.setIdKomunitatea(Integer.parseInt(object.getProperty("idKomunitatea").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setNick(object.getProperty("nick").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setIzenOsoa(object.getProperty("izenOsoa").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setPasahitza(object.getProperty("pasahitza").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setOngietorriMezua(object.getProperty("ongietorriMezua").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setSaria1(Integer.parseInt(object.getProperty("saria1").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setSaria2(Integer.parseInt(object.getProperty("saria2").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setSaria3(Integer.parseInt(object.getProperty("saria3").toString()));}catch (Exception e){e.printStackTrace();}
        try{}catch (Exception e){}
        return res;
    }

    public static Jokalaria jokalaria(SoapObject object){
        Jokalaria res=new Jokalaria();
        try{res.setIdJokalaria(Integer.parseInt(object.getProperty("idJokalaria").toString()));}catch (Exception e){}
        try{res.setTaldeaByIdTaldea(new Taldea(Integer.parseInt(object.getProperty("taldeaByIdTaldea").toString())));}catch (Exception e){}
        try{res.setTaldeaByDraftIdTaldea(new Taldea(Integer.parseInt(object.getProperty("taldeaByDraftIdTaldea").toString())));}catch (Exception e){}
        try{res.setIzena(object.getProperty("izena").toString());}catch (Exception e){}
        try{res.setAbizena(object.getProperty("abizena").toString());}catch (Exception e){}
        try{res.setDortsala(Integer.parseInt(object.getProperty("dortsala").toString()));}catch (Exception e){}
        try{res.setPosizioa(object.getProperty("posizioa").toString());}catch (Exception e){}
        try{res.setAltuera(Float.parseFloat(object.getProperty("altuera").toString()));}catch (Exception e){}
        try{res.setPisua(Float.parseFloat(object.getProperty("pisua").toString()));}catch (Exception e){}
        try{res.setJaiotzeData(Egutegia.StringToDate(object.getProperty("jaiotzeData").toString()));}catch (Exception e){}
        try{res.setNazionalitatea(object.getProperty("nazionalitatea").toString());}catch (Exception e){}
        try{res.setDraftUrtea(Integer.parseInt(object.getProperty("draftUrtea").toString()));}catch (Exception e){}
        try{res.setDraftPostua(Integer.parseInt(object.getProperty("draftPostua").toString()));}catch (Exception e){}
        try{res.setUnibertsitatea(object.getProperty("unibertsitatea").toString());}catch (Exception e){}
        try{res.setNbaDebutUrtea(Integer.parseInt(object.getProperty("nbaDebutUrtea").toString()));}catch (Exception e){}
        try{res.setNbaUrteak(Integer.parseInt(object.getProperty("nbaUrteak").toString()));}catch (Exception e){}
        try{res.setSoldata(Integer.parseInt(object.getProperty("soldata").toString()));}catch (Exception e){}
        try{}catch (Exception e){}
        return res;
    }

    public static MerkatukoJokalaria merkatukoJokalaria(SoapObject object){
        MerkatukoJokalaria res=new MerkatukoJokalaria();
        try{res.setIdMerkatukoJokalaria(Integer.parseInt(object.getProperty("idMerkatukoJokalaria").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setErabiltzaileaByIdErabiltzaileaIrabazlea(new Erabiltzailea(Integer.parseInt(object.getProperty("erabiltzaileaByIdErabiltzaileaIrabazlea").toString())));}catch (Exception e){}
        try{res.setErabiltzaileaByIdErabiltzaileaJabea(new Erabiltzailea(Integer.parseInt(object.getProperty("erabiltzaileaByIdErabiltzaileaJabea").toString())));}catch (Exception e){}
        try{res.setJokalaria(jokalaria((SoapObject)object.getProperty("jokalaria")));}catch (Exception e){}
        try{res.setKomunitatea(new Komunitatea(Integer.parseInt(object.getProperty("komunitatea").toString())));}catch (Exception e){}
        try{res.setHasierakoPrezioa(Integer.parseInt(object.getProperty("hasierakoPrezioa").toString()));}catch (Exception e){}
        try{res.setEskaintzaIrabazlea(Integer.parseInt(object.getProperty("eskaintzaIrabazlea").toString()));}catch (Exception e){}
        try{res.setOnartua(Boolean.parseBoolean(object.getProperty("onartua").toString()));}catch (Exception e){}
        try{res.setTramitatua(Boolean.parseBoolean(object.getProperty("tramitatua").toString()));}catch (Exception e){}
        try{}catch (Exception e){} //EGUNA
        return res;
    }

    public static Eskaintza eskaintza(SoapObject object){
        Eskaintza res=new Eskaintza();
        try{res.setIdEskaintza(Integer.parseInt(object.getProperty("idEskaintza").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setMerkatukoJokalaria(merkatukoJokalaria((SoapObject)object.getProperty("merkatukoJokalaria")));}catch (Exception e){e.printStackTrace();}
        try{res.setEskaintza(Integer.parseInt(object.getProperty("eskaintza").toString()));}catch (Exception e){e.printStackTrace();}
        try{res.setErabiltzailea(new Erabiltzailea(Integer.parseInt(object.getProperty("erabiltzailea").toString())));}catch (Exception e){e.printStackTrace();}
        return res;
    }

    public static Alineazioa alineazioa(SoapObject soapObject){
        Alineazioa res=new Alineazioa();
        try{res.setErabiltzailea(new Erabiltzailea(Integer.parseInt(soapObject.getProperty("erabiltzailea").toString())));}catch (Exception e){}
        try{res.setJokalariaByIdJokalaria1(new Jokalaria(Integer.parseInt(soapObject.getProperty("jokalariaByIdJokalaria1").toString())));}catch (Exception e){}
        try{res.setJokalariaByIdJokalaria2(new Jokalaria(Integer.parseInt(soapObject.getProperty("jokalariaByIdJokalaria2").toString())));}catch (Exception e){}
        try{res.setJokalariaByIdJokalaria3(new Jokalaria(Integer.parseInt(soapObject.getProperty("jokalariaByIdJokalaria3").toString())));}catch (Exception e){}
        try{res.setJokalariaByIdJokalaria4(new Jokalaria(Integer.parseInt(soapObject.getProperty("jokalariaByIdJokalaria4").toString())));}catch (Exception e){}
        try{res.setJokalariaByIdJokalaria5(new Jokalaria(Integer.parseInt(soapObject.getProperty("jokalariaByIdJokalaria5").toString())));}catch (Exception e){}
        return res;
    }


    public static Mezua mezua(SoapObject soapObject){
        Mezua res=new Mezua();
        try{res.setIdMezua(Integer.parseInt(soapObject.getProperty("idMezua").toString()));}catch (Exception e){}
        try{res.setErabiltzailea(new Erabiltzailea(Integer.parseInt(soapObject.getProperty("erabiltzailea").toString())));}catch (Exception e){}
        try{res.setKomunitatea(new Komunitatea(Integer.parseInt(soapObject.getProperty("komunitatea").toString())));}catch (Exception e){}
        try{res.setEguna(Egutegia.StringToDate(soapObject.getProperty("eguna").toString()));}catch (Exception e){}
        try{res.setEzabagarria(Boolean.parseBoolean(soapObject.getProperty("ezabagarria").toString()));}catch (Exception e){}
        try{res.setMezua(soapObject.getProperty("mezua").toString());}catch (Exception e){}
        return res;
    }


    public static PuntuazioaErabiltzaileaJardunaldia puntuazioaErabiltzaileaJardunaldia(SoapObject soapObject){
        PuntuazioaErabiltzaileaJardunaldia res=new PuntuazioaErabiltzaileaJardunaldia();
        try{res.setErabiltzailea(erabiltzailea((SoapObject)soapObject.getProperty("erabiltzailea")));}catch (Exception e){}
        try{res.setPuntuak(Integer.parseInt(soapObject.getProperty("puntuak").toString()));}catch (Exception e){}
        return res;
    }

    public static PuntuazioaJokalariaJardunaldia puntuazioaJokalariaJardunaldia(SoapObject soapObject){
        PuntuazioaJokalariaJardunaldia res=new PuntuazioaJokalariaJardunaldia();
        try{res.setJokalaria(jokalaria((SoapObject)soapObject.getProperty("jokalaria")));}catch (Exception e){}
        try{res.setPuntuak(Integer.parseInt(soapObject.getProperty("puntuak").toString()));}catch (Exception e){}
        return res;
    }

    public static Transakzioa transakzioa(SoapObject soapObject){
        Transakzioa res=new Transakzioa();
        try{res.setErabiltzailea(new Erabiltzailea(Integer.parseInt(soapObject.getProperty("erabiltzailea").toString())));}catch (Exception e){}
        try{res.setKantitatea(Integer.parseInt(soapObject.getProperty("kantitatea").toString()));}catch (Exception e){}
        try{res.setMezua(soapObject.getProperty("mezua").toString());}catch (Exception e){e.printStackTrace();}
        try{res.setEguna(Egutegia.StringToDate(soapObject.getProperty("eguna").toString()));}catch (Exception e){}
        return res;
    }

    public static Jardunaldia jardunaldia(SoapObject soapObject){
        Jardunaldia res=new Jardunaldia();
        try{res.setIdJardunaldia(Integer.parseInt(soapObject.getProperty("idJardunaldia").toString()));}catch (Exception e){}
        try{res.setHasierakoEguna(Egutegia.StringToDate(soapObject.getProperty("hasierakoEguna").toString()));}catch (Exception e){}
        return res;
    }

}
