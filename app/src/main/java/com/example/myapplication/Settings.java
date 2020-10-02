package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class Settings extends Activity {
    EditText nameEditText; // όνομα
    EditText surnameEditText; // επίθετο
    EditText emailEditText; // email
    Person george; // αντικείμενο για την αναπαράσταση του χρήστη
    MyDataHandler myDataHandler = new MyDataHandler(this,null,null,1); // δημιουργία αντικειμένου ΜyDataHandler
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);


        mAdView = findViewById(R.id.adViewSettings);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // μπάρα στο κάτω μέρος της οθόνης
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home: // ο χρήστης επιλέγει την αρχική
                        Intent i = new Intent(Settings.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_send: // ο χρήστης επιλέγει την αποστολή
                        Intent s = new Intent(Settings.this, Send.class);
                        startActivity(s);
                        break;
                    case R.id.navigation_calendar: // ο χρήστης επιλέγει το ημερολόγιο
                        Intent c = new Intent(Settings.this, Diary.class);
                        startActivity(c);
                        break;
                }
                return true;
            }


        });

        nameEditText = findViewById(R.id.editTextEditName);
        surnameEditText = findViewById(R.id.editTextEditSurname);
        emailEditText = findViewById(R.id.editTextEditEmail);

        george=myDataHandler.showPerson(); // αν ο χρήστης έχει συμπληρώσει τα στοιχεία του να εμφανίζουμε
        if(george!=null)
        {
            nameEditText.setText(george.getName());
            surnameEditText.setText(george.getSurname());
            emailEditText.setText(george.getEmail());
        }

    }

    // μέθοδος με την οποία αποθηκεύονται τα στοιχεία που συμπληρώνει ο χρήστης
    public void save(View view){
        Person person=myDataHandler.showPerson();
        String name=nameEditText.getText().toString();
        String surname=surnameEditText.getText().toString();
        String email=emailEditText.getText().toString();

        // αν δεν υπάρχουν συμπληρωμένα στοιχεία τα εισάγουμε στη βάση
        if(person==null)
        {
            person=new Person(name,surname,email);
            myDataHandler.addPerson(person);
            Toast.makeText(this,getString(R.string.info_saved),Toast.LENGTH_LONG).show();
        }
        // αν υπάρχουν διαγράφουμε τα παλιά και εισάγουμε τα καινούργια
        else
        {
            String oldname=person.getName();
            person.setName(name);
            person.setSurname(surname);
            person.setEmail(email);
            if(myDataHandler.deletePerson(oldname)){
                System.out.println("yes");
            }
            myDataHandler.addPerson(person);
            Toast.makeText(this,getString(R.string.info_saved),Toast.LENGTH_LONG).show();
        }

    }

}
