package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Diary extends AppCompatActivity {

    String date; //ημερομηνία

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);

        //μπάρα πλοήγησης στο κάτω μέρος της οθόνης
        BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home: // ο χρήστης επιλέγει την αρχική οθόνη.
                        Intent i = new Intent(Diary.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_send: // ο χρήστης επιλέγει την αποστολή.
                        Intent s = new Intent(Diary.this, Send.class);
                        startActivity(s);
                        break;
                    case R.id.navigation_settings: // ο χρήστης επιλέγει τις ρυθμίσεις.
                        Intent se = new Intent(Diary.this,Settings.class);
                        startActivity(se);
                        break;
                }
                return true;
            }
        });


    }

    //μέθοδος με την οποία μεταβαίνουμε στο Activity Symptoms
    public void viewSymptoms(View view){
        //θέτουμε ως ημερομηνία την ημέρα που διάλεξε ο χρήστης
        DatePicker calendar=findViewById(R.id.datePicker2);
        // μετατροπή της ημερομηνίας σε μορφή dd/MM/yyyy.
        String day;
        String month;
        if(calendar.getMonth()+1<10)
        {
            month="0"+(calendar.getMonth()+1);
        }
        else
        {
            month=""+(calendar.getMonth()+1);
        }

        if(calendar.getDayOfMonth()<10)
        {
            day="0"+calendar.getDayOfMonth();
        }
        else
        {
            day=""+calendar.getDayOfMonth();
        }
        date=day+"/"+month+"/"+calendar.getYear();

        Intent i = new Intent(this,Symptoms.class);
        i.putExtra("date",date);  //περνάμε την ημέρα που επιλέχθηκε
        startActivity(i);
    }

}
