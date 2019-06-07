package com.jgr14.nbasaresoziala.gui.logeatu_gabea.kontua;

import android.app.ProgressDialog;
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
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Hizkuntza;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.businessLogic.Kontua;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.Menu;
import com.jgr14.nbasaresoziala.gui.logeatua.PartiduenEmaitzakLogActivity;

import java.util.Locale;

public class KonturaSartuActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeatu_gabea_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

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


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }


    public static SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;
    public static class PlaceholderFragment extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";
        public PlaceholderFragment() {
        }
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.kontua_login, container, false);
            (rootView.findViewById(R.id.btn_login)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage(getString(R.string.kargatzen));
                    progressDialog.setTitle(getString(R.string.kontura_sartu));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    final String nick=((EditText)rootView.findViewById(R.id.input_nick)).getText().toString();
                    final String pass=((EditText)rootView.findViewById(R.id.input_password)).getText().toString();
                    new Thread(new Runnable() {
                        public void run() {
                            final boolean zuzena= Kontua.ErabitzaileaLogeatu(nick,pass);
                            progressDialog.dismiss();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(zuzena){
                                        Datuak.ErabiltzaileaGorde(getContext());

                                        Egutegia.diferentzia=Datuak.erabiltzailea.getOrduDiferentzia();Egutegia.establecerDiferenciaHoraria(getActivity());
                                        Koloreak.tema=Datuak.erabiltzailea.getKoloreak();Koloreak.EscribirTema(getContext());
                                        String hizkuntza=Hizkuntza.hizkuntza;
                                        Hizkuntza.hizkuntza=Datuak.erabiltzailea.getHizkuntza();Hizkuntza.EscribirLenguaje(getActivity());
                                        if(!hizkuntza.equals(Hizkuntza.hizkuntza)){
                                            Locale myLocale = new Locale(Hizkuntza.hizkuntza);
                                            if(Hizkuntza.hizkuntza.equals("eu")){
                                                myLocale=new Locale("eu","ES");
                                            }
                                            Resources res = getResources();
                                            DisplayMetrics dm = res.getDisplayMetrics();
                                            Configuration conf = res.getConfiguration();
                                            conf.locale = myLocale;
                                            res.updateConfiguration(conf, dm);
                                        }

                                        startActivity(new Intent(getActivity(), PartiduenEmaitzakLogActivity.class));
                                        Menu.cerrarTodasLasActividades();
                                        getActivity().finish();
                                    }else{
                                        Toast.makeText(getContext(),getString(R.string.errorea_kontura_sartzean),Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }
                    }).start();
                }
            });
            return rootView;
        }
    }
    public static class SectionsPagerAdapter extends FragmentPagerAdapter {
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
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


}
