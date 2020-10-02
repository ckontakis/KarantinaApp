package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String localeString=Locale.getDefault().getLanguage();
        System.out.println(localeString);
        Locale locale = new Locale(localeString);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MobileAds.initialize(this);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);



        //μπάρα πλοήγησης στο κάτω μέρος της οθόνης
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_calendar: // ο χρήστης επιλέγει το Ημερολόγιο.
                        Intent i = new Intent(MainActivity.this, Diary.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_send: // ο χρήστης επιλέγει την Αποστολή.
                        Intent s = new Intent(MainActivity.this, Send.class);
                        startActivity(s);
                        break;
                    case R.id.navigation_settings: // ο χρήστης επιλέγει τις Ρυθμίσεις.
                        Intent j = new Intent(MainActivity.this,Settings.class);
                        startActivity(j);
                        break;
                }
                return true;
            }
        });

    }

}
