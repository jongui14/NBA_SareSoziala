package com.jgr14.nbasaresoziala.dataAccess;

import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.businessLogic.Jokalariak;
import com.jgr14.nbasaresoziala.businessLogic.Kontua;
import com.jgr14.nbasaresoziala.domain.Alineazioa;
import com.jgr14.nbasaresoziala.domain.Erabiltzailea;
import com.jgr14.nbasaresoziala.domain.Eskaintza;
import com.jgr14.nbasaresoziala.domain.Jardunaldia;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.Komunitatea;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;
import com.jgr14.nbasaresoziala.domain.Mezua;
import com.jgr14.nbasaresoziala.domain.Partidua;
import com.jgr14.nbasaresoziala.domain.PuntuazioaErabiltzaileaJardunaldia;
import com.jgr14.nbasaresoziala.domain.PuntuazioaJokalariaJardunaldia;
import com.jgr14.nbasaresoziala.domain.Taldea;
import com.jgr14.nbasaresoziala.domain.Transakzioa;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.KvmSerializable;
import org.ksoap2.serialization.MarshalDate;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.Date;

import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.alineazioa;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.erabiltzailea;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.eskaintza;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.jardunaldia;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.jokalaria;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.komunitatea;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.merkatukoJokalaria;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.mezua;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.puntuazioaErabiltzaileaJardunaldia;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.puntuazioaJokalariaJardunaldia;
import static com.jgr14.nbasaresoziala.dataAccess.KastingFuntzioak.transakzioa;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class DataAccess {

    public static String URL="http://192.168.0.8";
    //public static String URL="http://192.168.116.1";
    public static final String PUERTO="9999";
    private static String WSDL_UserNotLogged= URL+":"+PUERTO+"/"+"usernotlogged?wsdl";
    private static String WSDL_UserLogged=    URL+":"+PUERTO+"/"+"userlogged?wsdl";

    private static final String NAMESPACE = "http://businessLogic/";


    public static String emaString="";
    /*
    LOGGED
     */
    public static ArrayList<Jokalaria> ErabiltzailearenJokalariakLortu(){
        ArrayList<Jokalaria> ema=new ArrayList<>();
        try{

            String funtzio_izena="ErabiltzailearenJokalariakLortu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(new Erabiltzailea(Datuak.erabiltzailea.getIdErabiltzailea()));
            propertyInfo.setType(Erabiltzailea.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(jokalaria(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static Alineazioa AlineazioaLortu(){
        try {
            String funtzio_izena="AlineazioaLortu";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");propertyInfo.setValue(Datuak.erabiltzailea);propertyInfo.setType(Erabiltzailea.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapObject  response = (SoapObject) envelope.getResponse();
            return alineazioa(response);

        } catch (Exception e) {
            e.printStackTrace();
            return new Alineazioa();
        }
    }
    public static boolean AlineazioaGorde(Erabiltzailea erabiltzailea,Jokalaria jokalaria1,Jokalaria jokalaria2,Jokalaria jokalaria3,Jokalaria jokalaria4,Jokalaria jokalaria5,boolean publikoa){
        try {
            String funtzio_izena="AlineazioaGorde";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();propertyInfo.setName("arg0");propertyInfo.setValue(erabiltzailea);propertyInfo.setType(Erabiltzailea.class);soapObject.addProperty(propertyInfo);
            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(jokalaria1);propInfo1.setType(Jokalaria.class);soapObject.addProperty(propInfo1);
            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(jokalaria2);propInfo2.setType(Jokalaria.class);soapObject.addProperty(propInfo2);
            PropertyInfo propInfo3 = new PropertyInfo();propInfo3.setName("arg3");propInfo3.setValue(jokalaria3);propInfo3.setType(Jokalaria.class);soapObject.addProperty(propInfo3);
            PropertyInfo propInfo4 = new PropertyInfo();propInfo4.setName("arg4");propInfo4.setValue(jokalaria4);propInfo4.setType(Jokalaria.class);soapObject.addProperty(propInfo4);
            PropertyInfo propInfo5 = new PropertyInfo();propInfo5.setName("arg5");propInfo5.setValue(jokalaria5);propInfo5.setType(Jokalaria.class);soapObject.addProperty(propInfo5);
            PropertyInfo propInfo6 = new PropertyInfo();propInfo6.setName("arg6");propInfo6.setValue(publikoa);propInfo6.setType(Boolean.class);soapObject.addProperty(propInfo6);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<Alineazioa> AlineazioPublikoakLortu(){
        ArrayList<Alineazioa> res = new ArrayList<>();
        try{
            String funtzio_izena="AlineazioPublikoak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                res.add(alineazioa(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean MerkatuanJarri(Komunitatea komunitatea, Erabiltzailea erabiltzailea,Jokalaria jokalaria,int hasierakoPrezioa){
        try {
            final String funtzio_izena="MerkatuanJarri";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo = new PropertyInfo();propInfo.setName("arg0");propInfo.setValue(new Komunitatea(komunitatea.getIdKomunitatea()));
            propInfo.setType(Erabiltzailea.class);soapObject.addProperty(propInfo);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg1");propInfo0.setValue(erabiltzailea);
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);

            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg2");propInfo1.setValue(jokalaria);
            propInfo1.setType(Jokalaria.class);soapObject.addProperty(propInfo1);

            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg3");propInfo2.setValue(hasierakoPrezioa);
            propInfo2.setType(Integer.class);soapObject.addProperty(propInfo2);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<MerkatukoJokalaria> MerkatuaKontsultatu(Komunitatea komunitatea){
        ArrayList<MerkatukoJokalaria> res = new ArrayList<>();
        try{
            String funtzio_izena="MerkatuaKontsultatu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(komunitatea);
            propInfo0.setType(Komunitatea.class);soapObject.addProperty(propInfo0);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();emaString="";
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);//emaString=emaString+resultObject.toString();
                res.add(merkatukoJokalaria(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean OfertaEgin(MerkatukoJokalaria merkatukoJokalaria,Erabiltzailea erabiltzailea,int oferta){
        try {
            final String funtzio_izena="OfertaEgin";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new MerkatukoJokalaria(merkatukoJokalaria.getIdMerkatukoJokalaria()));
            propInfo0.setType(MerkatukoJokalaria.class);soapObject.addProperty(propInfo0);

            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(new Erabiltzailea(erabiltzailea.getIdErabiltzailea()));
            propInfo1.setType(Erabiltzailea.class);soapObject.addProperty(propInfo1);

            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(oferta);
            propInfo2.setType(Integer.class);soapObject.addProperty(propInfo2);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static ArrayList<Jardunaldia> OrainArtekoJardunaldiak(){
        ArrayList<Jardunaldia> ema=new ArrayList<>();
        try{
            String funtzio_izena="OrainArtekoJardunaldiak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(jardunaldia(resultObject));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static ArrayList<PuntuazioaErabiltzaileaJardunaldia> JornadakoKlasifikazioa(Komunitatea komunitatea, Jardunaldia jardunaldia){
        ArrayList<PuntuazioaErabiltzaileaJardunaldia> ema=new ArrayList<>();
        try{
            String funtzio_izena="JornadakoKlasifikazioa";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInf0 = new PropertyInfo();propInf0.setName("arg0");propInf0.setValue(komunitatea);
            propInf0.setType(Komunitatea.class);soapObject.addProperty(propInf0);

            PropertyInfo propInf1 = new PropertyInfo();propInf1.setName("arg1");propInf1.setValue(jardunaldia);
            propInf1.setType(Jardunaldia.class);soapObject.addProperty(propInf1);


            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(puntuazioaErabiltzaileaJardunaldia(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static ArrayList<PuntuazioaErabiltzaileaJardunaldia> KlasifikazioOrokorra(Komunitatea komunitatea){
        ArrayList<PuntuazioaErabiltzaileaJardunaldia> ema=new ArrayList<>();
        try{
            String funtzio_izena="KlasifikazioOrokorra";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();propertyInfo.setName("arg0");propertyInfo.setValue(komunitatea);
            propertyInfo.setType(Komunitatea.class);soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(puntuazioaErabiltzaileaJardunaldia(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static ArrayList<PuntuazioaJokalariaJardunaldia> JokalariarenPuntuaketak(Jokalaria jokalaria){
        ArrayList<PuntuazioaJokalariaJardunaldia> ema=new ArrayList<>();
        try{
            String funtzio_izena="JokalariarenPuntuaketak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();propertyInfo.setName("arg0");
            propertyInfo.setValue(new Jokalaria(jokalaria.getIdJokalaria()));propertyInfo.setType(Jokalaria.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            int jardunaldia=count;
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(puntuazioaJokalariaJardunaldia(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static ArrayList<PuntuazioaJokalariaJardunaldia> JokalarienPuntuaketak(){
        ArrayList<PuntuazioaJokalariaJardunaldia> ema=new ArrayList<>();
        try{
            String funtzio_izena="JokalarienPuntuaketak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(puntuazioaJokalariaJardunaldia(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static ArrayList<Eskaintza> EgindakoEskaintzak(Erabiltzailea erabiltzailea){
        ArrayList<Eskaintza> res = new ArrayList<>();
        try{
            String funtzio_izena="EgindakoEskaintzak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(erabiltzailea);
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;emaString=result.toString();

            int count = result.getPropertyCount();emaString="";
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                res.add(eskaintza(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static ArrayList<Eskaintza> JasotakoEskaintzak(Erabiltzailea erabiltzailea){
        ArrayList<Eskaintza> res = new ArrayList<>();
        try{
            String funtzio_izena="JasotakoEskaintzak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(erabiltzailea);
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                res.add(eskaintza(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean EskaintzaErantzun(MerkatukoJokalaria merkatukoJokalaria,Eskaintza eskaintza,boolean onartu) {
        try {
            final String funtzio_izena="EskaintzaErantzun";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new MerkatukoJokalaria(merkatukoJokalaria.getIdMerkatukoJokalaria()));
            propInfo0.setType(MerkatukoJokalaria.class);soapObject.addProperty(propInfo0);

            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(new Eskaintza(eskaintza.getIdEskaintza()));
            propInfo1.setType(Eskaintza.class);soapObject.addProperty(propInfo1);

            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(onartu);
            propInfo2.setType(Boolean.class);soapObject.addProperty(propInfo2);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<Mezua> KomunitatekoMezuak(Komunitatea komunitatea){
        ArrayList<Mezua> res = new ArrayList<>();
        try{
            String funtzio_izena="MezuakLortu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new Komunitatea(komunitatea.getIdKomunitatea()));
            propInfo0.setType(Komunitatea.class);soapObject.addProperty(propInfo0);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                res.add(mezua(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean MezuaBidali(String textua,Komunitatea komunitatea,Erabiltzailea erabiltzailea) {
        try {
            final String funtzio_izena="MezuaIdatzi";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(textua);
            propInfo0.setType(String.class);soapObject.addProperty(propInfo0);

            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(new Komunitatea(komunitatea.getIdKomunitatea()));
            propInfo1.setType(Komunitatea.class);soapObject.addProperty(propInfo1);

            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(new Erabiltzailea(erabiltzailea.getIdErabiltzailea()));
            propInfo2.setType(Erabiltzailea.class);soapObject.addProperty(propInfo2);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ArrayList<Transakzioa> ErabiltzailearenTransakzioak(Erabiltzailea erabiltzailea){
        ArrayList<Transakzioa> res = new ArrayList<>();
        try{
            String funtzio_izena="ErabiltzailearenTransakzioak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new Erabiltzailea(erabiltzailea.getIdErabiltzailea()));
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                res.add(transakzioa(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean TransakzioaBidali(Erabiltzailea erabiltzailea,int kantitatea,String mezua) {
        try {
            final String funtzio_izena="TransakzioaGorde";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new Erabiltzailea(erabiltzailea.getIdErabiltzailea()));
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);

            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(kantitatea);
            propInfo1.setType(Integer.class);soapObject.addProperty(propInfo1);

            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(mezua);
            propInfo2.setType(String.class);soapObject.addProperty(propInfo2);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Komunitatea KomunitateaLortu(int id){
        try {
            String funtzio_izena="KomunitateaLortu";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");propertyInfo.setValue(new Komunitatea(id));propertyInfo.setType(Komunitatea.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapObject  response = (SoapObject) envelope.getResponse();
            return komunitatea(response);
        } catch (Exception e) {
            e.printStackTrace();
            return new Komunitatea();
        }
    }
    public static ArrayList<Erabiltzailea> ErabiltzaileakLortu(Komunitatea komunitatea){
        ArrayList<Erabiltzailea> res = new ArrayList<>();
        try{
            String funtzio_izena="ErabiltzaileakLortu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new Komunitatea(komunitatea.getIdKomunitatea()));
            propInfo0.setType(Komunitatea.class);soapObject.addProperty(propInfo0);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                res.add(erabiltzailea(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static boolean ErabiltzaileaEzabatu(Erabiltzailea erabiltzailea) {
        try {
            final String funtzio_izena="ErabiltzaileaEzabatu";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");propInfo0.setValue(new Erabiltzailea(erabiltzailea.getIdErabiltzailea()));
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean MezuaEzabatu(Mezua mezua) {
        try {
            final String funtzio_izena="MezuaEzabatu";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");
            propInfo0.setValue(mezua);
            propInfo0.setType(Mezua.class);soapObject.addProperty(propInfo0);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean KomunitateaGorde(Komunitatea komunitatea) {
        try {
            final String funtzio_izena="KomunitateaGorde";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");
            propInfo0.setValue(komunitatea);
            propInfo0.setType(Komunitatea.class);soapObject.addProperty(propInfo0);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean ErabiltzaileaaGorde(Erabiltzailea erabiltzailea) {
        try {
            final String funtzio_izena="ErabiltzaileaGorde";
            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propInfo0 = new PropertyInfo();propInfo0.setName("arg0");
            propInfo0.setValue(erabiltzailea);
            propInfo0.setType(Erabiltzailea.class);soapObject.addProperty(propInfo0);


            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    /*
    GENERALES
     */
    public static ArrayList<Partidua> PartiduakLortu(Date date){
        ArrayList<Partidua> ema=new ArrayList<>();
        try{
            String funtzio_izena="JornadakoPartiduak";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(date);
            propertyInfo.setType(Date.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;
            //SoapPrimitive soapresults = (SoapPrimitive)result.getProperty(0);

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);

                SoapObject etxekoTaldeaObject=(SoapObject)resultObject.getProperty("etxeko_taldea");
                SoapObject kanpokoTaldeaObject=(SoapObject)resultObject.getProperty("kanpoko_taldea");


                Taldea etxekoTaldea=new Taldea();
                etxekoTaldea.setIdTaldea(Integer.parseInt(etxekoTaldeaObject.getProperty("idTaldea").toString()));
                etxekoTaldea.setUrlIzena(etxekoTaldeaObject.getProperty("urlIzena").toString());

                Taldea kanpokoTaldea=new Taldea();
                kanpokoTaldea.setIdTaldea(Integer.parseInt(kanpokoTaldeaObject.getProperty("idTaldea").toString()));
                kanpokoTaldea.setUrlIzena(kanpokoTaldeaObject.getProperty("urlIzena").toString());

                Partidua partidua=new Partidua();
                partidua.setEtxeko_taldea(etxekoTaldea);
                partidua.setKanpoko_taldea(kanpokoTaldea);
                partidua.setEtxeko_puntuak(Integer.parseInt(resultObject.getProperty("etxeko_puntuak").toString()));
                partidua.setKanpoko_puntuak(Integer.parseInt(resultObject.getProperty("kanpoko_puntuak").toString()));

                try{partidua.setIdPartidua(Integer.parseInt(resultObject.getProperty("idPartidua").toString()));}catch (Exception ex){}
                try{partidua.setPartiduaren_egoera(Integer.parseInt(resultObject.getProperty("partiduaren_egoera").toString()));}catch (Exception ex){}
                try{partidua.setData(Egutegia.StringToDate(soapObject.getProperty("data").toString()));}catch (Exception e){e.printStackTrace();}
                try{partidua.setOrdua(Egutegia.StringToTime(soapObject.getProperty("orduaString").toString()));}catch (Exception e){e.printStackTrace();}
                try{partidua.setErlojua(Egutegia.StringToTime(soapObject.getProperty("erlojua").toString()));}catch (Exception e){e.printStackTrace();}
                try{partidua.setZatia(Integer.parseInt(resultObject.getProperty("zatia").toString()));}catch (Exception ex){}

                ema.add(partidua);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }


    /*
    NOT LOGGED
     */
    public static Erabiltzailea ErabiltzaileaLogeatu(String nick,String pasahitza){
        Erabiltzailea erabiltzailea=new Erabiltzailea();
        try {
            String funtzio_izena="ErabiltzaileaLogeatu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(nick);
            soapObject.addProperty(propertyInfo);

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.setName("arg1");
            propertyInfo1.setValue(pasahitza);
            soapObject.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapObject  response = (SoapObject) envelope.getResponse();

            erabiltzailea=erabiltzailea(response);
            /*erabiltzailea.setIdErabiltzailea(Integer.parseInt(response.getProperty("idErabiltzailea").toString()));
            erabiltzailea.setNick(response.getProperty("nick").toString());
            erabiltzailea.setEmail(response.getProperty("email").toString());
            erabiltzailea.setIzenOsoa(response.getProperty("izenOsoa").toString());
            erabiltzailea.setDirua(Integer.parseInt(response.getProperty("dirua").toString()));*/
            //erabiltzailea.setNick(response.getProperty("administratzailea").toString());
            //erabiltzailea.setNick(response.getProperty("hizkuntza").toString());
            //erabiltzailea.setNick(response.getProperty("koloreak").toString());
            //erabiltzailea.setNick(response.getProperty("orduDiferentzia").toString());

            Komunitatea komunitatea=new Komunitatea();
            komunitatea.setIdKomunitatea(Integer.parseInt(response.getProperty("komunitatea").toString()));
            Kontua.komunitatea=komunitatea;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return erabiltzailea;
    }
    public static boolean ErabiltzaileaErregistratu(Komunitatea komunitatea,String nick,String pass,String izen_osoa,String email,boolean admin){
        try {
            String funtzio_izena="ErabiltzaileaErregistratu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();propertyInfo.setName("arg0");
            propertyInfo.setValue(komunitatea);propertyInfo.setType(Komunitatea.class);soapObject.addProperty(propertyInfo);
            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(nick);soapObject.addProperty(propInfo1);
            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(email);soapObject.addProperty(propInfo2);
            PropertyInfo propInfo3 = new PropertyInfo();propInfo3.setName("arg3");propInfo3.setValue(izen_osoa);soapObject.addProperty(propInfo3);
            PropertyInfo propInfo4 = new PropertyInfo();propInfo4.setName("arg4");propInfo4.setValue(pass);soapObject.addProperty(propInfo4);
            PropertyInfo propInfo5 = new PropertyInfo();propInfo5.setName("arg5");propInfo5.setValue(admin);propInfo5.setType(Boolean.class);soapObject.addProperty(propInfo5);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static Komunitatea KomunitateaAukeratu(String nick,String pass){
        Komunitatea res=new Komunitatea();
        try {
            String funtzio_izena="KomunitateaAukeratu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(nick);
            soapObject.addProperty(propertyInfo);

            PropertyInfo propertyInfo1 = new PropertyInfo();
            propertyInfo1.setName("arg1");
            propertyInfo1.setValue(pass);
            soapObject.addProperty(propertyInfo1);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapObject  response = (SoapObject) envelope.getResponse();
            res.setIdKomunitatea(Integer.parseInt(response.getProperty("idKomunitatea").toString()));
            res.setNick(response.getProperty("nick").toString());


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static Komunitatea KomunitateaErregistratu(String nick,String pass,String izen_osoa,String mezua, int saria1,int saria2,int saria3){
        Komunitatea res=new Komunitatea();
        try {
            String funtzio_izena="KomunitateaErregistratu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            PropertyInfo propertyInfo = new PropertyInfo();propertyInfo.setName("arg0");propertyInfo.setValue(nick);soapObject.addProperty(propertyInfo);
            PropertyInfo propInfo1 = new PropertyInfo();propInfo1.setName("arg1");propInfo1.setValue(pass);soapObject.addProperty(propInfo1);
            PropertyInfo propInfo2 = new PropertyInfo();propInfo2.setName("arg2");propInfo2.setValue(izen_osoa);soapObject.addProperty(propInfo2);
            PropertyInfo propInfo3 = new PropertyInfo();propInfo3.setName("arg3");propInfo3.setValue(mezua);soapObject.addProperty(propInfo3);
            PropertyInfo propInfo4 = new PropertyInfo();propInfo4.setName("arg4");propInfo4.setValue(saria1);propInfo4.setType(Integer.class);soapObject.addProperty(propInfo4);
            PropertyInfo propInfo5 = new PropertyInfo();propInfo5.setName("arg5");propInfo5.setValue(saria2);propInfo5.setType(Integer.class);soapObject.addProperty(propInfo5);
            PropertyInfo propInfo6 = new PropertyInfo();propInfo6.setName("arg6");propInfo6.setValue(saria3);propInfo6.setType(Integer.class);soapObject.addProperty(propInfo6);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapObject  response = (SoapObject) envelope.getResponse();
            res.setIdKomunitatea(Integer.parseInt(response.getProperty("idKomunitatea").toString()));
            res.setNick(nick);
            res.setIzenOsoa(izen_osoa);
            res.setOngietorriMezua(mezua);
            res.setSaria1(saria1);
            res.setSaria2(saria2);
            res.setSaria3(saria3);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
    public static ArrayList<Jokalaria> JokalariGuztiakLortu(){
        ArrayList<Jokalaria> ema=new ArrayList<>();
        try{
            String funtzio_izena="JokalariakLortu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                ema.add(jokalaria(resultObject));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }
    public static ArrayList<Taldea> TaldeakLortu(){
        ArrayList<Taldea> ema=new ArrayList<>();
        try{
            String funtzio_izena="TaldeakLortu";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);

            SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
            new MarshalDate().register(envelope);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);
            httpTransportSE.debug = true;

            SoapObject result = (SoapObject) envelope.bodyIn;

            int count = result.getPropertyCount();
            for (int i = 0; i < count; i++){
                SoapObject resultObject = (SoapObject)result.getProperty(i);
                Taldea taldea=new Taldea();
                taldea.setIdTaldea(Integer.parseInt(resultObject.getProperty("idTaldea").toString()));
                taldea.setIzenOsoa(resultObject.getProperty("izenOsoa").toString());
                ema.add(taldea);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ema;
    }


    public static boolean ErabiltzaileNickLibre(String nick){
        try {
            String funtzio_izena="ErabiltzaileNickLibre";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(nick);
            propertyInfo.setType(String.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    public static boolean ErabiltzaileEmailLibre(String email){
        try {
            String funtzio_izena="ErabiltzaileNickLibre";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(email);
            propertyInfo.setType(String.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }


    public static boolean KomunitateaLibre(String nick){
        try {
            String funtzio_izena="KomunitateaLibre";

            SoapObject soapObject = new SoapObject(NAMESPACE, funtzio_izena);
            PropertyInfo propertyInfo = new PropertyInfo();
            propertyInfo.setName("arg0");
            propertyInfo.setValue(nick);
            propertyInfo.setType(String.class);
            soapObject.addProperty(propertyInfo);

            SoapSerializationEnvelope envelope =  new SoapSerializationEnvelope(SoapEnvelope.VER11);
            envelope.dotNet=false;
            envelope.setOutputSoapObject(soapObject);

            HttpTransportSE httpTransportSE = new HttpTransportSE(WSDL_UserNotLogged);
            httpTransportSE.call(NAMESPACE+funtzio_izena, envelope);

            SoapPrimitive  response = (SoapPrimitive) envelope.getResponse();
            return Boolean.parseBoolean(response.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

}
