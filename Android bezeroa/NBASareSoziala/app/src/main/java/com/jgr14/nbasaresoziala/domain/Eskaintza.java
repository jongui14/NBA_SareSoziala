package com.jgr14.nbasaresoziala.domain;

import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.PropertyInfo;

import java.io.Serializable;
import java.util.Date;
import java.util.Hashtable;

/**
 * Created by JONGUI on 07/05/2019.
 */

public class Eskaintza implements Serializable,KvmSerializable {

    private Integer idEskaintza=0;

    private MerkatukoJokalaria merkatukoJokalaria=new MerkatukoJokalaria();
    private Erabiltzailea erabiltzailea=new Erabiltzailea();
    private int eskaintza=0;


    public Eskaintza(){
    }

    public Eskaintza(int idEskaintza){
        this.idEskaintza=idEskaintza;
    }

    public String eskaintzaFormatuarekin(){
        try{
            String salarioLag=""+this.eskaintza;
            String salario="";
            while(salarioLag.length()>3) {
                salario=salario+","+salarioLag.substring(salarioLag.length()-3,salarioLag.length());
                salarioLag=salarioLag.substring(0,salarioLag.length()-3);
            }
            salario="$"+salarioLag+salario;
            return salario;

        }catch (Exception e){
            e.printStackTrace();
            return "";
        }
    }

    @Override
    public Object getProperty(int i) {
        switch (i) {
            case 0: return idEskaintza;
            case 1: return merkatukoJokalaria;
            case 2: return erabiltzailea;
            case 3: return eskaintza;
        }
        return null;
    }

    @Override
    public int getPropertyCount() {
        return 4;
    }

    @Override
    public void setProperty(int i, Object o) {
        switch (i) {
            case 0: idEskaintza=Integer.parseInt(o.toString());
            case 1: merkatukoJokalaria=(MerkatukoJokalaria) o;
            case 2: erabiltzailea=(Erabiltzailea) o;
            case 3: eskaintza=Integer.parseInt(o.toString());
        }
    }

    @Override
    public void getPropertyInfo(int i, Hashtable properties, PropertyInfo info) {
        switch (i) {
            case 0:info.name="idEskaintza";info.type = PropertyInfo.INTEGER_CLASS;break;
            case 1:info.name="merkatukoJokalaria";info.setType(MerkatukoJokalaria.class);break;
            case 2:info.name="erabiltzailea";info.setType(Erabiltzailea.class);break;
            case 3:info.name="eskaintza";info.setType(Integer.class);break;
        }
    }


    public Integer getIdEskaintza() {
        return idEskaintza;
    }

    public void setIdEskaintza(Integer idEskaintza) {
        this.idEskaintza = idEskaintza;
    }

    public MerkatukoJokalaria getMerkatukoJokalaria() {
        return merkatukoJokalaria;
    }

    public void setMerkatukoJokalaria(MerkatukoJokalaria merkatukoJokalaria) {
        this.merkatukoJokalaria = merkatukoJokalaria;
    }

    public Erabiltzailea getErabiltzailea() {
        return erabiltzailea;
    }

    public void setErabiltzailea(Erabiltzailea erabiltzailea) {
        this.erabiltzailea = erabiltzailea;
    }

    public int getEskaintza() {
        return eskaintza;
    }

    public void setEskaintza(int eskaintza) {
        this.eskaintza = eskaintza;
    }


}
