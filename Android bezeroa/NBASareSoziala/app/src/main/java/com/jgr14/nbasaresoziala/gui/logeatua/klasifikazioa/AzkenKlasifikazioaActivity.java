package com.jgr14.nbasaresoziala.gui.logeatua.klasifikazioa;

import android.app.Activity;
import android.content.pm.ActivityInfo;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala.adapter.AdapterErabiltzaileenPuntuak;
import com.jgr14.nbasaresoziala.businessLogic.Klasifikazioak;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Jardunaldia;
import com.jgr14.nbasaresoziala.domain.PuntuazioaErabiltzaileaJardunaldia;
import com.jgr14.nbasaresoziala.gui.logeatua.Menu;

import java.util.ArrayList;
import java.util.Date;

import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.MenuaKonprobatu;
import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.SesioaKonprobatu;

public class AzkenKlasifikazioaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static SectionsPagerAdapter mSectionsPagerAdapter;
    public static SectionsPagerAdapterKarga mSectionsPagerAdapterKarga;
    public static ViewPager mViewPager;

    public static Date date=new Date();
    public static Activity activity;


    public static ArrayList<Jardunaldia> jardunaldiak=new ArrayList<>();

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

        activity=AzkenKlasifikazioaActivity.this;
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


        mSectionsPagerAdapterKarga = new SectionsPagerAdapterKarga(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapterKarga);

        new Thread(new Runnable() {
            public void run() {
                jardunaldiak.clear();
                jardunaldiak= DataAccess.OrainArtekoJardunaldiak();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
                        mViewPager.setAdapter(mSectionsPagerAdapter);

                        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
                        tabLayout.setupWithViewPager(mViewPager);
                        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
                    }
                });
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
            final View rootView = inflater.inflate(R.layout.fragment_listview, container, false);
            final ListView listView=(ListView)rootView.findViewById(R.id.listview);
            new Thread(new Runnable() {
                public void run() {
                    Jardunaldia jardunaldia=jardunaldiak.get(index);
                    final ArrayList<PuntuazioaErabiltzaileaJardunaldia> erabilzaileak = Klasifikazioak.JornadakoKlasifikazioa(jardunaldia);
                    final AdapterErabiltzaileenPuntuak adapter = new AdapterErabiltzaileenPuntuak(getActivity(), erabilzaileak);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (rootView.findViewById(R.id.loading)).setVisibility(View.GONE);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

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
            return jardunaldiak.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return Egutegia.DateToString(jardunaldiak.get(position).getHasierakoEguna());
        }
    }

    public static class PlaceholderFragmentKarga extends Fragment {
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragmentKarga() {
        }


        public static PlaceholderFragmentKarga newInstance(int sectionNumber) {
            PlaceholderFragmentKarga fragment = new PlaceholderFragmentKarga();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            final View rootView = inflater.inflate(R.layout.fragment_listview, container, false);
            return rootView;
        }
    }
    public static class SectionsPagerAdapterKarga extends FragmentPagerAdapter {

        public SectionsPagerAdapterKarga(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PlaceholderFragmentKarga.newInstance(position);
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
