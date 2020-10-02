package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.w3c.dom.Text;

public class Contacts extends AppCompatActivity {

    String date; //ημερομηνία
    Friends contacts; //στενές επαφές
    TextView person1, person2, person3, person4, person5; // textView για την εμφάνιση των στενών επαφών.
    EditText name; //όνομα επαφής

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        //μπάρα πλοήγησης στο κάτω μέρος της οθόνης
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home: // ο χρήστης επιλέγει την αρχική οθόνη.
                        Intent i = new Intent(Contacts.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_send: // ο χρήστης επιλέγει την αποστολή.
                        Intent s = new Intent(Contacts.this, Send.class);
                        startActivity(s);
                        break;
                    case R.id.navigation_settings: // ο χρήστης επιλέγει τις ρυθμίσεις.
                        Intent se = new Intent(Contacts.this,Settings.class);
                        startActivity(se);
                        break;
                    case R.id.navigation_calendar: // ο χρήστης επιλέγει το ημερολόγιο.
                        Intent c = new Intent(Contacts.this, Diary.class);
                        startActivity(c);
                }
                return true;
            }
        });


        //παίρνουμε την ημερομηνία που έχει επιλεγεί και εμφανίζουμε κατάλληλο μήνυμα
        Intent i=getIntent();
        date=i.getStringExtra("date");
        String text=getString(R.string.fill_contact)+ " " + date;
        TextView textView=(TextView)findViewById(R.id.textView_contacts);
        textView.setText(text);


        person1 = findViewById(R.id.textView_personA);
        person2 = findViewById(R.id.textView_personB);
        person3 = findViewById(R.id.textView_personC);
        person4 = findViewById(R.id.textView_personD);
        person5 = findViewById(R.id.textView_personE);


        // κλήση του data handler.
        MyDataHandler dbHandler =new MyDataHandler(this,null,null,1);
        contacts=dbHandler.showContacts(date);

        //αν υπάρχει στην βάση εγγραφή με την ίδια ημερομηνία προσυμπληρώνουμε τα στοιχεία.
        if(contacts!=null) {
           person1.setText(contacts.getPersonA());
           person2.setText(contacts.getPersonB());
           person3.setText(contacts.getPersonC());
           person4.setText(contacts.getPersonD());
           person5.setText(contacts.getPersonE());
        }
        // αν δεν υπάρχουν δημιουργούμε μία καινούργια εγγραφή.
        else {

            contacts=new Friends();
            contacts.setDate(date);
            dbHandler.addContacts(contacts);
        }

    }



    //μέθοδος με την οποία μεταβαίνουμε στο Activity Symptoms
    public void viewSymptoms(View view){
        Intent i = new Intent(this,Symptoms.class);
        i.putExtra("date",date); //περνάμε την ημερομηνία
        startActivity(i);
    }

    // μέθοδος που προσθέτουμε στενές επαφές
    public void add_contacts(View view)
    {
        MyDataHandler dbhandler= new MyDataHandler(this,null,null,1);

        name=findViewById(R.id.editText_contact); // editText για την προσθήκη επαφής.

        if(!name.getText().toString().isEmpty()){ // αν ο χρήστης έχει γράψει κάποιο όνομα.
            if(contacts.getPersonA().isEmpty()) // αν η πρώτη επαφή δεν έχει όνομα.
            {
                contacts.setPerA(name.getText().toString());
                person1.setText(name.getText().toString()); // εμφάνιση επαφής.
                dbhandler.replaceContacts(contacts,date); // προσθήκη επαφής στην βάση.
                name.setText("");
            }
             else if(contacts.getPersonB().isEmpty()) // αν η δεύτερη επαφή δεν έχει όνομα.
            {
                contacts.setPerB(name.getText().toString());
                person2.setText(name.getText().toString()); // εμφάνιση επαφής.
                dbhandler.replaceContacts(contacts,date); // προσθήκη επαφής στην βάση.
                name.setText("");
            }
            else if(contacts.getPersonC().isEmpty()) // αν η τρίτη επαφή δεν έχει όνομα.
            {
                contacts.setPerC(name.getText().toString());
                person3.setText(name.getText().toString()); // εμφάνιση επαφής.
                dbhandler.replaceContacts(contacts,date); // προσθήκη επαφής στην βάση.
                name.setText("");
            }
            else if(contacts.getPersonD().isEmpty()) // αν η τέταρτη επαφή δεν έχει όνομα.
            {
                contacts.setPerD(name.getText().toString());
                person4.setText(name.getText().toString()); // εμφάνιση επαφής.
                dbhandler.replaceContacts(contacts,date); // προσθήκη επαφής στην βάση.
                name.setText("");
            }
            else if (contacts.getPersonE().isEmpty()) // αν η πέμπτη επαφή δεν έχει όνομα.
            {
                contacts.setPerE(name.getText().toString());
                person5.setText(name.getText().toString()); // εμφάνιση επαφής.
                dbhandler.replaceContacts(contacts,date); // προσθήκη επαφής στην βάση.
                name.setText("");
            }
            else // αν έχουν συπληρωθεί όλες οι επαφές.
            {
                Toast.makeText(this,"ΜΕΓΙΣΤΟΣ ΑΡΙΘΜΟΣ ΕΠΑΦΩΝ: 5! ΜΕΝΟΥΜΕ ΣΠΙΤΙ!!!",Toast.LENGTH_LONG).show();
                name.setText("");
            }
        }

    }

    // μέθοδος για την διαγραφή της τελευταίς επαφής.
    public void delete_contact(View view)
    {
        MyDataHandler dbHandler=new MyDataHandler(this,null,null,1);

        if(!person5.getText().toString().isEmpty()) // αν η πέμπτη επαφή είναι η τελευταία που προσθέσαμε.
        {
            contacts.setPerE("");
            person5.setText("");
            dbHandler.replaceContacts(contacts,date); // διαγραφή της επαφής από την βάση.
        }
        else if(!person4.getText().toString().isEmpty()) // αν η τέταρτη επαφή είναι η τελευταία που προσθέσαμε.
        {
            contacts.setPerD("");
            person4.setText("");
            dbHandler.replaceContacts(contacts,date); // διαγραφή της επαφής από την βάση.
        }
        else if(!person3.getText().toString().isEmpty()) // αν η τρίτη επαφή είναι η τελευταία που προσθέσαμε.
        {
            contacts.setPerC("");
            person3.setText("");
            dbHandler.replaceContacts(contacts,date); // διαγραφή της επαφής από την βάση.
        }
        else if(!person2.getText().toString().isEmpty()) // αν η δεύτερη επαφή είναι η τελευταία που προσθέσαμε.
        {
            contacts.setPerB("");
            person2.setText("");
            dbHandler.replaceContacts(contacts,date); // διαγραφή της επαφής από την βάση.
        }
        else if(!person1.getText().toString().isEmpty()) // αν η πρώτη επαφή είναι η τελευταία που προσθέσαμε.
        {
            contacts.setPerA("");
            person1.setText("");
            dbHandler.replaceContacts(contacts,date); // διαγραφή της επαφής από την βάση.
        }
        else // αν δεν έχουν προστεθεί επαφές.
        {
            Toast.makeText(this,getString(R.string.no_close_contacts),Toast.LENGTH_LONG).show();
        }
    }
}
