package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Symptoms extends AppCompatActivity {

    String date; //ημερομηνία που επιλέχθηκε
    //Checkboxes για τα συμπτώματα
    CheckBox bellyache;
    CheckBox breath;
    CheckBox cough;
    CheckBox notaste;
    CheckBox tiredness;
    CheckBox nosmell;
    CheckBox diarroea;
    CheckBox headache;
    CheckBox throatache;
    CheckBox runnynose;
    SeekBar fever; //Seekbar για την δήλωση πυρετού
    TextView feverTag; //κείμενο που δείχνει τον πυρετό
    EditText other; //άλλα συμπτώματα
    Illness illness; //αντικείμενο της κλάσηε Illness για την αποθήκευση των δεδομένων

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);

        //μπάρα στο κάτω μέρος της οθόνης
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home: // ο χρήστης επιλέγει την αρχική οθόνη.
                        Intent i = new Intent(Symptoms.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_send: // ο χρήστης επιλέγει την αποστολή.
                        Intent s = new Intent(Symptoms.this, Send.class);
                        startActivity(s);
                        break;
                    case R.id.navigation_settings: // ο χρήστης επιλέγει τις ρυθμίσεις.
                        Intent se = new Intent(Symptoms.this,Settings.class);
                        startActivity(se);
                        break;
                    case R.id.navigation_calendar: // ο χρήστης επιλέγει το ημερολόγιο.
                        Intent c = new Intent(Symptoms.this, Diary.class);
                        startActivity(c);
                        break;
                }
                return true;
            }
        });

        //παίρνουμε την ημερομηνία που επιλέχθηκε και εμφανίζουμε το κατάλληλο μήνυμα
        Intent i=getIntent();
        date=i.getStringExtra("date");
        String text=getString(R.string.fill_symptoms)+date;
        TextView textView=findViewById(R.id.textView_symptoms);
        textView.setText(text);

        bellyache=findViewById(R.id.checkBox_bellyache);
        throatache=findViewById(R.id.checkBox_throatpain);
        headache=findViewById(R.id.checkBox_headache);
        breath=findViewById(R.id.checkBox_breath);
        notaste=findViewById(R.id.checkBox_notaste);
        nosmell=findViewById(R.id.checkBox_nosmell);
        cough=findViewById(R.id.checkBox_cough);
        diarroea=findViewById(R.id.checkBox_diarhoea);
        runnynose=findViewById(R.id.checkBox_runnynose);
        tiredness=findViewById(R.id.checkBox_tiredness);
        other=findViewById(R.id.editText_other);

        //αλλαγή στο feverTag όταν επηρεάζεται το Seekbar
        fever=findViewById(R.id.seekBar);
        feverTag=findViewById(R.id.textView_fevertag);
        feverTag.setText((34.0 + fever.getProgress() / 10) +"\u2103");
        fever.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                feverTag.setText(String.valueOf(34.0+fever.getProgress()/10.0)+"\u2103");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                feverTag.setText(String.valueOf(34.0+fever.getProgress()/10.0)+"\u2103");

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                feverTag.setText(String.valueOf(34.0+fever.getProgress()/10.0)+"\u2103");

            }
        });

        //Δημιουργία αντικειμένου του ΜyDataHandler.
        MyDataHandler dbHandler =new MyDataHandler(this,null,null,1);
        illness=dbHandler.showSymptoms(date);
        //αν υπάρχει στην βάση εγγραφή με την ίδια ημερομηνία προσυμπληρώνουμε τα στοιχεία
        if(illness!=null)
        {
            if(illness.getBla()==1) bellyache.setChecked(true);
            if(illness.getBrd()==1) breath.setChecked(true);
            if(illness.getCat()==1) runnynose.setChecked(true);
            if(illness.getCou()==1) cough.setChecked(true);
            if(illness.getDia()==1) diarroea.setChecked(true);
            if(illness.getFat()==1) tiredness.setChecked(true);
            if(illness.getHda()==1) headache.setChecked(true);
            if(illness.getOdo()==1) nosmell.setChecked(true);
            if(illness.getSth()==1) throatache.setChecked(true);
            if(illness.getTou()==1) notaste.setChecked(true);
            other.setText(illness.getOth());
            fever.setProgress((int)((illness.getFiv()-34)*10));
        }

    }

    //μέθοδος που καλείται όταν μεταβαίνουμε στο Activity Contacts
    public void viewContacts(View view){
        submit_symptoms(view); //αποθήκευση δεδομένων

        Intent i = new Intent(this,Contacts.class);
        i.putExtra("date",date); //περνάμε την ημερομηνία που επιλέχθηκε από το χρήστη
        startActivity(i);
    }

    //μέθοδος που καλείται όταν αποθηκεύουμε τα δεδομένα που συμπλήρωσε ο χρήστης στην βάση
    public void submit_symptoms(View view)
    {
        // Δημιουργία αντικειμένου του ΜyDataHandler.
        MyDataHandler dbHandler=new MyDataHandler(this,null,null,1);
        illness=dbHandler.showSymptoms(date);

        // παίρνουμε τα στοιχεία που έχει συμπληρώσει ο χρήστης.
        int bla=(bellyache.isChecked()) ? 1 : 0;
        int fat=(tiredness.isChecked()) ? 1 : 0;
        int cou=(cough.isChecked()) ? 1 : 0;
        int cat=(runnynose.isChecked()) ? 1:0;
        int sth=(throatache.isChecked()) ? 1:0;
        int brd=(breath.isChecked()) ? 1:0;
        int tou=(notaste.isChecked()) ? 1:0;
        int odo=(nosmell.isChecked()) ? 1:0;
        int dia=(diarroea.isChecked()) ? 1: 0;
        int hda=(headache.isChecked()) ? 1 : 0;
        double fiv=34.0+fever.getProgress()/10.0;
        String oth=other.getText().toString();

        // αν δεν υπάρχει εγγραφή με την ίδια ημερομηνία δημιουργούμε μία καινούργια
        if(illness==null){
            Illness illness=new Illness(date,bla,fat,cou,cat,sth,brd,tou,odo,dia,hda,fiv,oth);
            dbHandler.setSymptoms(illness);
        }
        // αν υπάρχει ενημερώνουμε τα στοιχεία.
        else
        {
            illness.setBla(bla);
            illness.setFat(fat);
            illness.setCou(cou);
            illness.setCat(cat);
            illness.setSth(sth);
            illness.setBrd(brd);
            illness.setTou(tou);
            illness.setOdo(odo);
            illness.setDia(dia);
            illness.setHda(hda);
            illness.setFiv(fiv);
            illness.setOth(oth);
            dbHandler.replaceSymptoms(illness,date);
        }

        Toast.makeText(this,"ΤΑ ΣΥΜΠΤΩΜΑΤΑ ΣΑΣ ΥΠΟΒΛΗΘΗΚΑΝ",Toast.LENGTH_LONG).show();
    }

}
