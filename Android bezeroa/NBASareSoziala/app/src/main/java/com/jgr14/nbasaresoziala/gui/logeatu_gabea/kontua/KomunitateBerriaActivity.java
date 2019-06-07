package com.jgr14.nbasaresoziala.gui.logeatu_gabea.kontua;

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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jgr14.nbasaresoziala.R;
import com.jgr14.nbasaresoziala._propietateak.Koloreak;
import com.jgr14.nbasaresoziala.businessLogic.Kontua;
import com.jgr14.nbasaresoziala.gui.logeatu_gabea.Menu;

public class KomunitateBerriaActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static String nick="",izen_osoa="",pass="",mezua="";
    public static int saria1=0,saria2=0,saria3=0;

    public static boolean nick_zuzena,pass_zuzena,pass_zuzena2;
    public static Button btn_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeatu_gabea_main);
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
            final View rootView = inflater.inflate(R.layout.kontua_komunitatea_sortu, container, false);
            nick_zuzena=false;pass_zuzena=false;pass_zuzena2=false;

            final EditText input_nick=(EditText)rootView.findViewById(R.id.input_nick);
            input_nick.addTextChangedListener(new TextWatcher() {
                @Override public void afterTextChanged(Editable s) {}
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    final String komunitate_nick=s.toString();
                    new Thread(new Runnable() {
                        public void run() {
                            nick_zuzena= Kontua.KomunitateaLibre(komunitate_nick);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(!nick_zuzena){input_nick.setError("");}
                                    ParametroakKonprobatu();
                                }
                            });
                        }
                    }).start();
                }
            });

            final EditText input_password=(EditText)rootView.findViewById(R.id.input_password);
            final EditText input_password2=(EditText)rootView.findViewById(R.id.input_password2);
            input_password.addTextChangedListener(new TextWatcher() {
                @Override public void afterTextChanged(Editable s) {}
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    final String pass=s.toString();
                    new Thread(new Runnable() {
                        public void run() {
                            pass_zuzena= Kontua.PasahitzaEgokia(pass);
                            pass_zuzena2= pass.equals(input_password2.getText().toString());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(!pass_zuzena){input_password.setError("");}
                                    if(!pass_zuzena2){input_password2.setError("");}else{input_password2.setError(null);}
                                    ParametroakKonprobatu();
                                }
                            });
                        }
                    }).start();
                }
            });
            input_password2.addTextChangedListener(new TextWatcher() {
                @Override public void afterTextChanged(Editable s) {}
                @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
                @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                    final String pass=s.toString();
                    new Thread(new Runnable() {
                        public void run() {
                            pass_zuzena2= pass.equals(input_password.getText().toString());
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if(!pass_zuzena2){input_password2.setError("");}
                                    ParametroakKonprobatu();
                                }
                            });
                        }
                    }).start();
                }
            });



            btn_signup=(Button)rootView.findViewById(R.id.btn_signup);
            btn_signup.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    final ProgressDialog progressDialog = new ProgressDialog(getContext());
                    try{
                        progressDialog.setMessage(getString(R.string.kargatzen));
                        progressDialog.setTitle(getString(R.string.komunitate_berria));
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.show();
                        progressDialog.setCancelable(false);

                        nick=input_nick.getText().toString();
                        izen_osoa=((EditText)rootView.findViewById(R.id.input_name)).getText().toString();
                        pass=input_password.getText().toString();
                        mezua=((EditText)rootView.findViewById(R.id.input_mezua)).getText().toString();
                        try{saria1=Integer.parseInt(((EditText)rootView.findViewById(R.id.input_saria1)).getText().toString());}catch (Exception e){saria1=0;}
                        try{saria2=Integer.parseInt(((EditText)rootView.findViewById(R.id.input_saria2)).getText().toString());}catch (Exception e){saria2=0;}
                        try{saria3=Integer.parseInt(((EditText)rootView.findViewById(R.id.input_saria3)).getText().toString());}catch (Exception e){saria3=0;}

                        new Thread(new Runnable() {
                            public void run() {
                                final boolean zuzena= true;
                                progressDialog.dismiss();
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if(zuzena){
                                            ErabiltzaileBerriaActivity.admin=true;
                                            startActivity(new Intent(getActivity(), ErabiltzaileBerriaActivity.class));
                                        }else{
                                            Toast.makeText(getContext(),getString(R.string.errorea_komunitate_berria),Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        }).start();
                    }catch (Exception e){
                        e.printStackTrace();
                        try{progressDialog.dismiss();}catch (Exception ex){ex.printStackTrace();}
                        Toast.makeText(getContext(),getString(R.string.errorea_komunitate_berria),Toast.LENGTH_LONG).show();
                    }

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

    private static void ParametroakKonprobatu(){
        if(nick_zuzena && pass_zuzena && pass_zuzena2){
            btn_signup.setEnabled(true);
        }else{
            btn_signup.setEnabled(false);
        }
    }

}
