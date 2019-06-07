package com.jgr14.nbasaresoziala.gui.logeatua.komunitatea;

import android.app.Activity;
import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Datuak;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.adapter.AdapterMezua;
import com.jgr14.nbasaresoziala.businessLogic.Komunitateak;
import com.jgr14.nbasaresoziala.businessLogic.Kontua;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.domain.Mezua;
import com.jgr14.nbasaresoziala.gui.logeatua.Menu;
import com.jgr14.nbasaresoziala.gui.logeatua.PartiduenEmaitzakLogActivity;

import java.util.ArrayList;
import java.util.Date;

import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.MenuaKonprobatu;
import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.SesioaKonprobatu;

public class SariakAldatuActivity extends AppCompatActivity
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

        activity=SariakAldatuActivity.this;
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
            final View rootView = inflater.inflate(R.layout.komunitatea_sariak_aldatu, container, false);
            ((EditText)rootView.findViewById(R.id.input_saria1)).setText(Datuak.komunitatea.getSaria1()+"");
            ((EditText)rootView.findViewById(R.id.input_saria2)).setText(Datuak.komunitatea.getSaria2()+"");
            ((EditText)rootView.findViewById(R.id.input_saria3)).setText(Datuak.komunitatea.getSaria3()+"");

            (rootView.findViewById(R.id.onartu)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    progressDialog.setMessage(getString(R.string.kargatzen));
                    progressDialog.setTitle(getString(R.string.sariak_aldatu));
                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                    progressDialog.show();
                    progressDialog.setCancelable(false);

                    final int saria1=Integer.parseInt(((EditText)rootView.findViewById(R.id.input_saria1)).getText().toString());
                    final int saria2=Integer.parseInt(((EditText)rootView.findViewById(R.id.input_saria2)).getText().toString());
                    final int saria3=Integer.parseInt(((EditText)rootView.findViewById(R.id.input_saria3)).getText().toString());
                    new Thread(new Runnable() {
                        public void run() {
                            final boolean zuzena= Komunitateak.SariakAldatu(saria1,saria2,saria3);
                            progressDialog.dismiss();
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(zuzena){
                                        Toast.makeText(getContext(),getString(R.string.sariak_ondo_aldatu_dira),Toast.LENGTH_LONG).show();
                                    }else{
                                        Toast.makeText(getContext(),getString(R.string.ezin_izan_dira_sariak_aldatu),Toast.LENGTH_LONG).show();
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




}
