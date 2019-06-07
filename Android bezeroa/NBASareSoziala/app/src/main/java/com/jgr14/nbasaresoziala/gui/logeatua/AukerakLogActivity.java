package com.jgr14.nbasaresoziala.gui.logeatua;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Hizkuntza;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.adapter.AdapterPartidua;
import com.jgr14.nbasaresoziala.businessLogic.Kontua;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.domain.Partidua;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak.AukerakActivity;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.MenuaKonprobatu;
import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.SesioaKonprobatu;

public class AukerakLogActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;

    public static Date date=new Date();
    public static Activity activity;

    @Override
    protected void onResume(){
        super.onResume();
        SesioaKonprobatu(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeatua_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Koloreak.EstablecerTema(this);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        MenuaKonprobatu(navigationView);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.setCurrentItem(Partiduak.DatarenIndizeaLortu(date));

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return Menu.onNavigationItemSelected(item,this);
    }



    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        public static AukerakActivity.PlaceholderFragment newInstance(int sectionNumber) {
            AukerakActivity.PlaceholderFragment fragment = new AukerakActivity.PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_aukerak, container, false);

            //hizkuntza
            try{
                switch (Hizkuntza.hizkuntza){
                    case "eu":
                        ((RadioButton)rootView.findViewById(R.id.euskera)).setChecked(true);
                        break;
                    case "es":
                        ((RadioButton)rootView.findViewById(R.id.gaztelera)).setChecked(true);
                        break;
                    case "en":
                        ((RadioButton)rootView.findViewById(R.id.ingelesa)).setChecked(true);
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            //horario
            try{
                switch (Egutegia.diferentzia){
                    case -1:((RadioButton)rootView.findViewById(R.id.horario_mexico)).setChecked(true);break;
                    case 0:((RadioButton)rootView.findViewById(R.id.horario_usa)).setChecked(true);break;
                    case 1:((RadioButton)rootView.findViewById(R.id.horario_argentina)).setChecked(true);break;
                    case 6:((RadioButton)rootView.findViewById(R.id.horario_españa)).setChecked(true);break;
                    default:((RadioButton)rootView.findViewById(R.id.horario_detectar_automaticamente)).setChecked(true);break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }


            //tema
            try{
                Switch Switch;
                switch (Koloreak.tema){
                    case 0:
                        Switch=(Switch)rootView.findViewById(R.id.switch1);
                        Switch.setChecked(false);
                        Switch.setText(R.string.modo_dia);
                        break;
                    case 1:
                        Switch=(Switch)rootView.findViewById(R.id.switch1);
                        Switch.setChecked(true);
                        Switch.setText(R.string.modo_noche);
                        break;
                }
            }catch (Exception e){
                e.printStackTrace();
            }

            return rootView;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return AukerakActivity.PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 1;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }
    }


    public void seleccionarIdioma(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.euskera:
                if (checked) Hizkuntza.hizkuntza="eu"; break;

            case R.id.gaztelera:
                if (checked) Hizkuntza.hizkuntza="es"; break;

            case R.id.ingelesa:
                if (checked) Hizkuntza.hizkuntza="en"; break;
        }

        Datuak.erabiltzailea.setHizkuntza(Hizkuntza.hizkuntza);
        ErabiltzailearenDatuakGorde();

        Hizkuntza.EscribirLenguaje(getApplicationContext());
        Locale myLocale = new Locale(Hizkuntza.hizkuntza);
        if(Hizkuntza.hizkuntza.equals("eu")){
            myLocale=new Locale("eu","ES");
        }
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = myLocale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, AukerakLogActivity.class);
        startActivity(refresh);
        finish();
    }

    public void seleccionarTema(View view){
        Switch Switch=(Switch)findViewById(R.id.switch1);
        if( Switch.isChecked() ){
            Koloreak.tema=1;Switch.setText(R.string.modo_noche);
        }else{
            Koloreak.tema=0;Switch.setText(R.string.modo_dia);
        }
        Koloreak.EscribirTema(view.getContext());
        Koloreak.InicializarColores(view.getContext());

        Datuak.erabiltzailea.setKoloreak(Koloreak.tema);
        ErabiltzailearenDatuakGorde();

        startActivity(new Intent(AukerakLogActivity.this,AukerakLogActivity.class));
        finish();
    }

    public void seleccionarHorario(View view){
        boolean checked = ((RadioButton) view).isChecked();
        switch(view.getId()) {
            case R.id.horario_usa:
                if (checked) Egutegia.diferentzia=0;break;

            case R.id.horario_españa:
                if (checked) Egutegia.diferentzia=6;break;

            case R.id.horario_argentina:
                if (checked) Egutegia.diferentzia=1;break;

            case R.id.horario_mexico:
                if (checked) Egutegia.diferentzia=-1;break;

            case R.id.horario_detectar_automaticamente:
                if (checked) Egutegia.diferentzia=Egutegia.diferenciaHorario();break;
        }
        Egutegia.establecerDiferenciaHoraria(view.getContext());

        Datuak.erabiltzailea.setOrduDiferentzia(Egutegia.diferentzia);
        ErabiltzailearenDatuakGorde();
    }


    public void ErabiltzailearenDatuakGorde(){
        new Thread(new Runnable() {
            public void run() {
                Kontua.ErabiltzaileaGorde();
            }
        }).start();
    }

}
