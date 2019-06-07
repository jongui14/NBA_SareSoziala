package com.jgr14.nbasaresoziala.gui.logeatua.merkatua;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
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
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.adapter.AdapterAlineazioak;
import com.jgr14.nbasaresoziala.adapter.AdapterJokalariaEskaintzakEginak;
import com.jgr14.nbasaresoziala.adapter.AdapterJokalariaEskaintzakJasotakoak;
import com.jgr14.nbasaresoziala.businessLogic.Jokalariak;
import com.jgr14.nbasaresoziala.businessLogic.Merkatua;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.domain.Alineazioa;
import com.jgr14.nbasaresoziala.domain.Eskaintza;
import com.jgr14.nbasaresoziala.domain.MerkatukoJokalaria;
import com.jgr14.nbasaresoziala.gui.logeatua.Menu;

import java.util.ArrayList;
import java.util.Date;

import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.MenuaKonprobatu;
import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.SesioaKonprobatu;

public class JasotakoEskaintzakActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;

    public static ArrayList<MerkatukoJokalaria> eskaintza_guztiak=new ArrayList<>();private static boolean kargatua=false;

    @Override
    protected void onResume(){
        super.onResume();
        SesioaKonprobatu(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeatua_tabs);
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

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        kargatua=false;
        //new CallWebService().execute("");
        new Thread(new Runnable() {
            public void run() {
                eskaintza_guztiak= Merkatua.JasotakoEskaintzak();kargatua=true;
            }
        }).start();

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
            final int index=getArguments().getInt(ARG_SECTION_NUMBER);
            final View rootView = inflater.inflate(R.layout.fragment_listview_dirua, container, false);
            ((TextView)rootView.findViewById(R.id.dirua)).setText(Datuak.erabiltzailea.diruaFormatuarekin());
            final ListView lv = (ListView) rootView.findViewById(R.id.listview);

            new Thread(new Runnable() {
                public void run() {
                    while(!kargatua){try {Thread.sleep(300); } catch (InterruptedException e) {e.printStackTrace();}}
                    final ArrayList<MerkatukoJokalaria> eskaintza_tramitatuak=Merkatua.JasotakoEskaintzakTramitatuak(eskaintza_guztiak);
                    final ArrayList<MerkatukoJokalaria> eskaintza_tramitatu_gabeak=Merkatua.JasotakoEskaintzakTramitatuGabeak(eskaintza_guztiak);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (rootView.findViewById(R.id.loading)).setVisibility(View.GONE);
                            if(index==0){
                                AdapterJokalariaEskaintzakJasotakoak adapter = new AdapterJokalariaEskaintzakJasotakoak(getActivity(), eskaintza_tramitatu_gabeak);
                                lv.setAdapter(adapter);
                            }else{
                                AdapterJokalariaEskaintzakJasotakoak adapter = new AdapterJokalariaEskaintzakJasotakoak(getActivity(), eskaintza_tramitatuak);
                                lv.setAdapter(adapter);
                            }
                        }
                    });
                }
            }).start();
            return rootView;
        }
    }
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:
                    return getString(R.string.tramitatu_gabeak);
                case 1:
                    return getString(R.string.tramitatuak);
            }
            return "";
        }

    }

    class CallWebService extends AsyncTask<String, Void, String> {
        @Override
        protected void onPostExecute(String s) {
            kargatua=true;
        }

        @Override
        protected String doInBackground(String... params) {
            eskaintza_guztiak= Merkatua.JasotakoEskaintzak();
            return "";
        }
    }

}
