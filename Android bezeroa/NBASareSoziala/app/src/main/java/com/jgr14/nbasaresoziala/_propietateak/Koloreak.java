package com.jgr14.nbasaresoziala._propietateak;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;

import com.jgr14.nbasaresoziala.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by JONGUI on 02/05/2019.
 */

public class Koloreak {

    private static String fichero="Koloreak.txt";
    public static int tema=0;

    public static int fondo1= Color.parseColor("#141d26");
    public static int fondo2=Color.parseColor("#1b2836");
    public static int fondo3=Color.parseColor("#243447");
    public static int fondo4=Color.parseColor("#293644");
    public static int fondo5=Color.parseColor("#343447");

    public static int borde1=Color.parseColor("#000000");
    public static int borde2=Color.parseColor("#ffffff");

    public static int letras1=Color.parseColor("#ffffff");
    public static int letras2=Color.parseColor("#8fa1ad");
    public static int letras3=Color.parseColor("#1da1f2");


    public static void InicializarColores(Context context){
        try{
            FileInputStream fin = context.openFileInput(fichero);
            InputStreamReader inputStreamReader = new InputStreamReader(fin);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            tema=Integer.parseInt(bufferedReader.readLine());
            fin.close();
            inputStreamReader.close();
            bufferedReader.close();
        }catch (Exception e){
            e.printStackTrace();
            tema=0;
            Koloreak.EscribirTema(context);
        }

        switch (tema){
            case 0:
                fondo1=context.getResources().getColor(R.color.tema0fondo1);
                fondo2=context.getResources().getColor(R.color.tema0fondo2);
                fondo3=context.getResources().getColor(R.color.tema0fondo3);
                fondo4=context.getResources().getColor(R.color.tema0fondo4);
                fondo5=context.getResources().getColor(R.color.tema0fondo5);

                borde1=context.getResources().getColor(R.color.tema0borde1);
                borde2=context.getResources().getColor(R.color.tema0borde2);

                letras1=context.getResources().getColor(R.color.tema0letras1);
                letras2=context.getResources().getColor(R.color.tema0letras2);
                letras3=context.getResources().getColor(R.color.tema0letras3);
                break;


            case 1:
                fondo1=context.getResources().getColor(R.color.tema1fondo1);
                fondo2=context.getResources().getColor(R.color.tema1fondo2);
                fondo3=context.getResources().getColor(R.color.tema1fondo3);
                fondo4=context.getResources().getColor(R.color.tema1fondo4);
                fondo5=context.getResources().getColor(R.color.tema1fondo5);

                borde1=context.getResources().getColor(R.color.tema1borde1);
                borde2=context.getResources().getColor(R.color.tema1borde2);

                letras1=context.getResources().getColor(R.color.tema1letras1);
                letras2=context.getResources().getColor(R.color.tema1letras2);
                letras3=context.getResources().getColor(R.color.tema1letras3);
                break;


            case 2:
                fondo1=context.getResources().getColor(R.color.tema2fondo1);
                fondo2=context.getResources().getColor(R.color.tema2fondo2);
                fondo3=context.getResources().getColor(R.color.tema2fondo3);
                fondo4=context.getResources().getColor(R.color.tema2fondo4);
                fondo5=context.getResources().getColor(R.color.tema2fondo5);

                borde1=context.getResources().getColor(R.color.tema2borde1);
                borde2=context.getResources().getColor(R.color.tema2borde2);

                letras1=context.getResources().getColor(R.color.tema2letras1);
                letras2=context.getResources().getColor(R.color.tema2letras2);
                letras3=context.getResources().getColor(R.color.tema2letras3);
                break;

        }
    }


    public static void EscribirTema(Context c){
        try{
            FileOutputStream fos = c.openFileOutput(fichero,Context.MODE_PRIVATE);
            fos.write((tema+"").getBytes());
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public static void EstablecerTema(Activity activity){
        if(tema==0){
            activity.setTheme(R.style.AppThemePrincipal);
        }else if(tema==1){
            activity.setTheme(R.style.AppThemeAzulOscuro);
        }else{
            activity.setTheme(R.style.AppThemeClaro);
        }
    }

}
