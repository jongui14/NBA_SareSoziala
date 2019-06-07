package com.jgr14.nbasaresoziala.gui.logeatua.jokalariak;

import android.app.Activity;
import android.app.DatePickerDialog;
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
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.adapter.AdapterJokalariaAlineazioa;
import com.jgr14.nbasaresoziala.businessLogic.Jokalariak;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.domain.Alineazioa;
import com.jgr14.nbasaresoziala.domain.Jokalaria;
import com.jgr14.nbasaresoziala.gui.logeatua.Menu;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.MenuaKonprobatu;
import static com.jgr14.nbasaresoziala.gui.logeatua.Menu.SesioaKonprobatu;

public class AlineazioaActivity extends AppCompatActivity
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

        activity=AlineazioaActivity.this;
        Partiduak.EgunKopuruaHasieratu();
        aukeratua1=false;aukeratua2=false;aukeratua3=false;aukeratua4=false;aukeratua5=false;

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

    public static boolean aukeratua1=false,aukeratua2=false,aukeratua3=false,aukeratua4=false,aukeratua5=false;
    public static Alineazioa alineazioa=new Alineazioa();
    public static ArrayList<Jokalaria> jokalariak=new ArrayList<>();public static ArrayList<Jokalaria> jokalari_guztiak=new ArrayList<>();
    public static ImageView jokalaria1IV,jokalaria2IV,jokalaria3IV,jokalaria4IV,jokalaria5IV;
    public static ListView listView;

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
            final View rootView = inflater.inflate(R.layout.fragment_alineazioa, container, false);
            listView=(ListView)rootView.findViewById(R.id.listview);
            (rootView.findViewById(R.id.principal)).setVisibility(View.GONE);

            jokalaria1IV=(ImageView)rootView.findViewById(R.id.jokalaria1);
            jokalaria2IV=(ImageView)rootView.findViewById(R.id.jokalaria2);
            jokalaria3IV=(ImageView)rootView.findViewById(R.id.jokalaria3);
            jokalaria4IV=(ImageView)rootView.findViewById(R.id.jokalaria4);
            jokalaria5IV=(ImageView)rootView.findViewById(R.id.jokalaria5);

            new Thread(new Runnable() {
                public void run() {
                    jokalari_guztiak = Jokalariak.ErabiltzailearenJokalariakLortu();
                    alineazioa=Jokalariak.AlineazioaLortu();
                    final AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(getActivity(), jokalari_guztiak);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (rootView.findViewById(R.id.loading)).setVisibility(View.GONE);(rootView.findViewById(R.id.principal)).setVisibility(View.VISIBLE);
                            listView.setAdapter(adapter);
                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    if (aukeratua1 || aukeratua2 || aukeratua3 || aukeratua4 || aukeratua5) {
                                        if(aukeratua1){
                                            alineazioa.setJokalariaByIdJokalaria1(jokalariak.get(position));
                                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria1().getArgazkia()).into(jokalaria1IV);
                                        }else if(aukeratua2){
                                            alineazioa.setJokalariaByIdJokalaria2(jokalariak.get(position));
                                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria2().getArgazkia()).into(jokalaria2IV);
                                        }else if(aukeratua3){
                                            alineazioa.setJokalariaByIdJokalaria3(jokalariak.get(position));
                                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria3().getArgazkia()).into(jokalaria3IV);
                                        }else if(aukeratua4){
                                            alineazioa.setJokalariaByIdJokalaria4(jokalariak.get(position));
                                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria4().getArgazkia()).into(jokalaria4IV);
                                        }else if(aukeratua5){
                                            alineazioa.setJokalariaByIdJokalaria5(jokalariak.get(position));
                                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria5().getArgazkia()).into(jokalaria5IV);
                                        }
                                        AlineazioaHasieratu(getActivity());
                                    }
                                }
                            });


                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria1().getArgazkia()).into(jokalaria1IV);
                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria2().getArgazkia()).into(jokalaria2IV);
                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria3().getArgazkia()).into(jokalaria3IV);
                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria4().getArgazkia()).into(jokalaria4IV);
                            Picasso.with(getContext()).load(alineazioa.getJokalariaByIdJokalaria5().getArgazkia()).into(jokalaria5IV);


                            jokalaria1IV.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(aukeratua1){
                                        aukeratua1=false;
                                        AlineazioaHasieratu(getActivity());
                                    }else{
                                        aukeratua1=true;
                                        jokalaria2IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria3IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria4IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria5IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalariak=Jokalariak.JokalariakFiltratu(jokalari_guztiak,"G",alineazioa);
                                        AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(getActivity(),jokalariak);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            });

                            jokalaria2IV.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(aukeratua2){
                                        aukeratua2=false;
                                        AlineazioaHasieratu(getActivity());
                                    }else{
                                        aukeratua2=true;
                                        jokalaria1IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria3IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria4IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria5IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalariak=Jokalariak.JokalariakFiltratu(jokalari_guztiak,"G",alineazioa);
                                        AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(getActivity(),jokalariak);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            });

                            jokalaria3IV.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(aukeratua3){
                                        aukeratua3=false;
                                        AlineazioaHasieratu(getActivity());
                                    }else{
                                        aukeratua3=true;
                                        jokalaria1IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria2IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria4IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria5IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalariak=Jokalariak.JokalariakFiltratu(jokalari_guztiak,"F",alineazioa);
                                        AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(getActivity(),jokalariak);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            });

                            jokalaria4IV.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(aukeratua1){
                                        aukeratua4=false;
                                        AlineazioaHasieratu(getActivity());
                                    }else{
                                        aukeratua4=true;
                                        jokalaria1IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria2IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria3IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria5IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalariak=Jokalariak.JokalariakFiltratu(jokalari_guztiak,"F",alineazioa);
                                        AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(getActivity(),jokalariak);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            });

                            jokalaria5IV.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    if(aukeratua5){
                                        aukeratua5=false;
                                        AlineazioaHasieratu(getActivity());
                                    }else{
                                        aukeratua5=true;
                                        jokalaria1IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria2IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria3IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalaria4IV.setBackgroundColor(Koloreak.fondo1);
                                        jokalariak=Jokalariak.JokalariakFiltratu(jokalari_guztiak,"C",alineazioa);
                                        AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(getActivity(),jokalariak);
                                        listView.setAdapter(adapter);
                                    }
                                }
                            });



                            (rootView.findViewById(R.id.gorde)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    final ProgressDialog progressDialog = new ProgressDialog(activity);
                                    progressDialog.setMessage(activity.getString(R.string.kargatzen));
                                    progressDialog.setTitle(activity.getString(R.string.alineazioa));
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.show();
                                    progressDialog.setCancelable(false);

                                    new Thread(new Runnable() {
                                        public void run() {
                                            final boolean zuzena=Jokalariak.AlineazioaGorde(alineazioa);
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressDialog.dismiss();
                                                    if(zuzena){
                                                        Toast.makeText(getContext(),getString(R.string.alineazioa_egoki_gorde_da),Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(getContext(),getString(R.string.errorea_alineazioa_gordetzean),Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }).start();
                                }
                            });

                            (rootView.findViewById(R.id.publikatu)).setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                    final ProgressDialog progressDialog = new ProgressDialog(activity);
                                    progressDialog.setMessage(activity.getString(R.string.kargatzen));
                                    progressDialog.setTitle(activity.getString(R.string.alineazioa));
                                    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                                    progressDialog.show();
                                    progressDialog.setCancelable(false);

                                    new Thread(new Runnable() {
                                        public void run() {
                                            final boolean zuzena=Jokalariak.AlineazioaPublikatu(alineazioa);
                                            getActivity().runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    progressDialog.dismiss();
                                                    if(zuzena){
                                                        Toast.makeText(getContext(),getString(R.string.alineazioa_egoki_publikatu_da),Toast.LENGTH_SHORT).show();
                                                    }else{
                                                        Toast.makeText(getContext(),getString(R.string.errorea_alineazioa_publikatzean),Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            });
                                        }
                                    }).start();
                                }
                            });
                        }
                    });
                }
            }).start();
            return rootView;
        }
    }

    public static void AlineazioaHasieratu(Activity activity){
        aukeratua1=false;aukeratua2=false;aukeratua3=false;aukeratua4=false;aukeratua5=false;
        jokalaria1IV.setBackgroundColor(Koloreak.fondo4);
        jokalaria2IV.setBackgroundColor(Koloreak.fondo4);
        jokalaria3IV.setBackgroundColor(Koloreak.fondo4);
        jokalaria4IV.setBackgroundColor(Koloreak.fondo4);
        jokalaria5IV.setBackgroundColor(Koloreak.fondo4);
        AdapterJokalariaAlineazioa adapter = new AdapterJokalariaAlineazioa(activity,jokalari_guztiak);
        listView.setAdapter(adapter);
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

    private static DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date=Egutegia.StringToDate(year+"-"+(month+1)+"-"+dayOfMonth);
            activity.startActivity(new Intent(activity, AlineazioaActivity.class));
            activity.finish();
        }
    };



}
