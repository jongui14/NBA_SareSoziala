package com.jgr14.nbasaresoziala.gui.logeatua.estadistikak;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
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
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.adapter.AdapterJokalariBakarrarenPuntuak;
import com.jgr14.nbasaresoziala.adapter.AdapterTaldea;
import com.jgr14.nbasaresoziala.adapter.ArrayAdapterJokalariarenInformazioGuztia;
import com.jgr14.nbasaresoziala.adapter.ArrayAdapterJokalariarenInformazioa;
import com.jgr14.nbasaresoziala.businessLogic.Estadistikak;
import com.jgr14.nbasaresoziala.businessLogic.Jokalariak;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.businessLogic.Taldeak;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.domain.PuntuazioaJokalariaJardunaldia;
import com.jgr14.nbasaresoziala.domain.Taldea;
import com.jgr14.nbasaresoziala.gui.logeatua.Menu;

import java.util.ArrayList;
import java.util.Date;

import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.MenuaKonprobatu;
import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.SesioaKonprobatu;

public class JokalariBakarrenPuntuakActivity extends AppCompatActivity
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

        activity=JokalariBakarrenPuntuakActivity.this;
        Partiduak.EgunKopuruaHasieratu();


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
            final View rootView = inflater.inflate(R.layout.estadistikak_jokalari_bakarra, container, false);
            final ListView listView=(ListView)rootView.findViewById(R.id.listview);listView.setVisibility(View.GONE);
            final Spinner spinner=(Spinner)rootView.findViewById(R.id.spinner);spinner.setVisibility(View.GONE);
            new Thread(new Runnable() {
                public void run() {
                    final ArrayList<Jokalaria> jokalariak = Jokalariak.ErabiltzailearenJokalariakLortu();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (rootView.findViewById(R.id.loading)).setVisibility(View.GONE);
                            spinner.setVisibility(View.VISIBLE);
                            final ArrayAdapterJokalariarenInformazioa adapter = new ArrayAdapterJokalariarenInformazioa(getContext(), R.layout.item_jokalaria_alineazioa,R.id.izena, jokalariak);
                            spinner.setAdapter(adapter);
                            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                    int index = arg0.getSelectedItemPosition();
                                    final Jokalaria jok=jokalariak.get(index);
                                    Toast.makeText(getContext(),jok.izenOsoa(),Toast.LENGTH_SHORT).show();
                                    (rootView.findViewById(R.id.loading)).setVisibility(View.VISIBLE);
                                    listView.setVisibility(View.GONE);
                                    new Thread(new Runnable() {
                                        public void run() {
                                            final ArrayList<PuntuazioaJokalariaJardunaldia> jokalariak = Estadistikak.JokalariarenPuntuaketak(jok);
                                            final AdapterJokalariBakarrarenPuntuak adapter = new AdapterJokalariBakarrarenPuntuak(getActivity(), jokalariak);
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    listView.setVisibility(View.VISIBLE);
                                                    (rootView.findViewById(R.id.loading)).setVisibility(View.GONE);
                                                    listView.setAdapter(adapter);
                                                }
                                            });
                                        }
                                    }).start();

                                }
                                @Override
                                public void onNothingSelected(AdapterView<?> arg0) {
                                }
                            });
                        }
                    });
                }
            }).start();
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


}
