package com.jgr14.nbasaresoziala._propietateak;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.jgr14.nbasaresoziala.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class Egutegia {

    public static String EgunekoDataLortu(Activity activity, int diaL, int mesL, int añoL){

        String[] strDays = new String[]{
                "",
                activity.getResources().getString(R.string.igandea),
                activity.getResources().getString(R.string.astelehena),
                activity.getResources().getString(R.string.asteartea),
                activity.getResources().getString(R.string.asteazkena),
                activity.getResources().getString(R.string.osteguna),
                activity.getResources().getString(R.string.ostirala),
                activity.getResources().getString(R.string.larunbata)
        };


        String[] meses = new String[]{
                activity.getResources().getString(R.string.urtarrila),
                activity.getResources().getString(R.string.otsaila),
                activity.getResources().getString(R.string.martxoa),
                activity.getResources().getString(R.string.apirila),
                activity.getResources().getString(R.string.maiatza),
                activity.getResources().getString(R.string.ekaina),
                activity.getResources().getString(R.string.uztaila),
                activity.getResources().getString(R.string.abuztua),
                activity.getResources().getString(R.string.iraila),
                activity.getResources().getString(R.string.urria),
                activity.getResources().getString(R.string.azaroa),
                activity.getResources().getString(R.string.abendua)
        };


        Calendar now =  Calendar.getInstance();

        @SuppressWarnings("deprecation")
        Date date=new Date(añoL-1900,mesL-1,diaL);

        now.setTime(date);
        int diaS=now.get(Calendar.DAY_OF_WEEK);


        return strDays[diaS]+",  "+diaL+" "+meses[mesL-1];


    }

    static DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    public static Date StringToDate(String fecha) {
        try {
            Date date=format.parse(fecha);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }
    public static Time StringToTime(String fecha) {
        try {
            int ordua=Integer.parseInt(fecha.split(":")[0]);
            if(ordua!=12 && ordua!=0)ordua=ordua+12;
            Time time = new Time(ordua,Integer.parseInt(fecha.split(":")[1]),Integer.parseInt(fecha.split(":")[2]));
            return time;
        } catch (Exception e) {
            e.printStackTrace();
            return new Time(0,0,0);
        }
    }

    public static String TimeToString(Time time){
        String minutuak=time.getMinutes()+"";if(minutuak.length()<2)minutuak="0"+minutuak;
        return conseguirHora(time.getHours())+":"+minutuak;
    }

    public static String TiempoCuartoToString(Time time){
        try{
            String minutos=time.getMinutes()+"";if(minutos.length()<2)minutos="0"+minutos;
            String segundos=time.getSeconds()+"";if(segundos.length()<2)segundos="0"+segundos;
            return minutos+":"+segundos;
        }catch (Exception e){
            return e.getMessage();
        }
    }

    public static String DateToString(Date date) {
        try {
            Calendar cal=Calendar.getInstance();
            cal.setTime(date);
            return cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.MONTH)+"-"+cal.get(Calendar.DAY_OF_MONTH);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }



    /*
    HORARIOS
     */
    public static int diferentzia =0;

    private static String fichero="Horarios_Diferencias_Guardado.txt";
    public static void OrduDiferentziaLortu(Context c){
        try{
            FileInputStream fin = c.openFileInput(fichero);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            diferentzia =Integer.parseInt(bufferedReader.readLine());
            fin.close();
            inputStreamReader.close();
            bufferedReader.close();
        }catch (Exception e){
            try{
                diferentzia = diferenciaHorario();
                establecerDiferenciaHoraria(c);
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }
    public static void establecerDiferenciaHoraria(Context c){
        try{
            FileOutputStream fos = c.openFileOutput(fichero,Context.MODE_PRIVATE);
            fos.write((diferentzia +"").getBytes());
            fos.close();

        }catch (Exception e){
            Log.e("Exception", "escribirCantidadMoneda: " + e.toString());
            //Toast toast = Toast.makeText(c, "Error en la escritura", Toast.LENGTH_SHORT);
            //toast.show();
        }
    }

    public static int conseguirHora(int hora){
        int suma=(hora+ diferentzia);
        if(suma<0){
            return (suma+24);
        }else if(suma<24){
            return suma;
        }else{
            return (suma-24);
        }
    }

    public static int diferenciaHorario() {
        Date dateUSA = new Date();
        SimpleDateFormat simpleDateFormatUSA = new SimpleDateFormat();
        simpleDateFormatUSA.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        String line = simpleDateFormatUSA.format(dateUSA),lag="";
        int i=0;
        while(line.charAt(i)!=' ') i++;i++;
        while(line.charAt(i)!=':') {
            lag = lag + line.charAt(i);
            i++;
        }
        Date dateLocal = new Date();
        return (dateLocal.getHours()-Integer.parseInt(lag));
    }
}
