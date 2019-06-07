package com.jgr14.nbasaresoziala.gui.logeatu_gabea.generalak;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Egutegia;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.adapter.AdapterPartidua;
import com.jgr14.nbasaresoziala.businessLogic.Partiduak;
import com.jgr14.nbasaresoziala.dataAccess.DataAccess;
import com.jgr14.nbasaresoziala.domain.Partidua;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.Menu;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PartiduenEmaitzakActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static SectionsPagerAdapter mSectionsPagerAdapter;
    public static ViewPager mViewPager;

    public static Date date=new Date();
    public static Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeatu_gabea_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Koloreak.EstablecerTema(this);

        activity=PartiduenEmaitzakActivity.this;
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
            final int index=getArguments().getInt(ARG_SECTION_NUMBER);
            final Date fechaDate= Partiduak.IndizetikDataLortu(index);

            final View rootView = inflater.inflate(R.layout.fragment_partiduak, container, false);
            final ListView lv = (ListView) rootView.findViewById(R.id.idListView);

            Calendar cal = Calendar.getInstance();
            cal.setTime(fechaDate);
            final int year = cal.get(Calendar.YEAR);
            final int month = cal.get(Calendar.MONTH)+1;
            final int day = cal.get(Calendar.DAY_OF_MONTH);
            final TextView textView=(TextView)rootView.findViewById(R.id.eguna);
            textView.setText(Egutegia.EgunekoDataLortu(getActivity(),day,month,year));

            final ArrayList<Partidua> eguneko_partiduak=new ArrayList<>();

            new Thread(new Runnable() {
                public void run() {
                    eguneko_partiduak.clear();
                    eguneko_partiduak.addAll(Partiduak.Eguneko_Partiduak(fechaDate));

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            (rootView.findViewById(R.id.loading)).setVisibility(View.GONE);
                            AdapterPartidua adapter = new AdapterPartidua(getActivity(), eguneko_partiduak);
                            lv.setAdapter(adapter);
                            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                                }
                            });
                        }
                    });
                }
            }).start();

            ImageView button= (ImageView) rootView.findViewById(R.id.calendarImage);
            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    DatePickerDialog datePicker = new DatePickerDialog(getContext(),R.style.AppThemeAzulOscuro, datePickerListener,
                            year,
                            month-1,
                            day);

                    datePicker.setTitle("");
                    datePicker.show();
                }
            });

            final SwipeRefreshLayout mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.activity_main_swipe_refresh_layout);
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    new Thread() {
                        public void run() {
                            eguneko_partiduak.clear();
                            eguneko_partiduak.addAll(Partiduak.Eguneko_Partiduak(fechaDate));
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    AdapterPartidua adapter = new AdapterPartidua(getActivity(), eguneko_partiduak);
                                    lv.setAdapter(adapter);
                                    mSwipeRefreshLayout.setRefreshing(false);
                                }
                            });
                        }
                    }.start();

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
            return Partiduak.EgunKopurua;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "";
        }

    }

    private static DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            date=Egutegia.StringToDate(year+"-"+(month+1)+"-"+dayOfMonth);
            activity.startActivity(new Intent(activity, PartiduenEmaitzakActivity.class));
            activity.finish();
        }
    };



}
