package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.timessquare.CalendarPickerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;



public class Send extends AppCompatActivity {

    List<Date> dates; // λίστα με τις ημερομηνίες που επέλεξε ο χρήστης.
    CalendarPickerView calendar_view; // ημερολόγιο.
    CheckBox checkBox_contacts; // CheckBox για την προσθήκη των στενών επαφών στο μήνυμα.


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);

        // μπάρα πλοήγησης στο κάτω μέρος της οθόνης.
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_home: // ο χρήστης επιλέγει την αρχική οθόνη.
                        Intent i = new Intent(Send.this, MainActivity.class);
                        startActivity(i);
                        break;
                    case R.id.navigation_calendar: // ο χρήστης επιλέγει το ημερολόγιο.
                        Intent s = new Intent(Send.this, Diary.class);
                        startActivity(s);
                        break;
                    case R.id.navigation_settings: // ο χρήστης επιλέγει τις ρυθμίσεις.
                        Intent se = new Intent(Send.this,Settings.class);
                        startActivity(se);
                        break;

                }
                return true;
            }
        });

        // Δημιουργία CalendarPickerView για την επολιγή ημερομηνιών.
        calendar_view =findViewById(R.id.calendar_view);
        Calendar start = Calendar.getInstance(); // σημείο που αρχίζει το ημερολόγιο.
        start.add(Calendar.MONTH,-2);
        Calendar end = Calendar.getInstance(); // σημείο που τελειώνει το ημερολόγιο.
        end.add(Calendar.DATE,1);
        calendar_view.init(start.getTime(),end.getTime()).inMode(CalendarPickerView.SelectionMode.RANGE);

        checkBox_contacts=findViewById(R.id.checkBox_contacts);


}

    /*
        Μέθοδος η οποία δέχεται ως όρισμα μία λίστα με ημερομηνίες που έχει επιλέξει ο χρήστης και επιστρέφει μία λίστα με
        όλα τα στοιχεία για αυτές τις μέρες.
    */
    public String getMessage(List<Date> dates){
        SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy"); // μορφή ημερομηνίας.
        String message=""; // μήνυμα.
        MyDataHandler myHandler = new MyDataHandler(this,null,null,1);

        // αν έχουν συμπληρωθεί τα στοιχεία του χρήστη τα προσθέτουμε στο μήνυμα.
        Person person = myHandler.showPerson();
        if(person != null){
            message += person.getName() + " " + person.getSurname() + " " + person.getEmail() + '\n';
        }

        Illness symptoms;
        Friends contacts;



        // για κάθε μέρα της λίστας συμπληρώνουμε τα συμπτώματα και τις στενές επαφές
        for(int i=0;i<dates.size();i++)
        {
            message+=sdf.format(dates.get(i))+"\n"; // προσθέτουμε ημερομηνία στο μήνυμα

            symptoms =myHandler.showSymptoms(sdf.format(dates.get(i))); // αν υπάρχουν συμπτώματα τα προσθέτουμε
            if(symptoms!=null)
            {
                message+=getString(R.string.symptoms_name)+"\n";
                if(symptoms.getBla()==1) message+=getString(R.string.headache_name)+"\n";
                if(symptoms.getBrd()==1) message+=getString(R.string.breath_name)+"\n";
                if(symptoms.getCat()==1) message+=getString(R.string.runnynose)+"\n";
                if(symptoms.getCou()==1) message+=getString(R.string.cough)+"\n";
                if(symptoms.getDia()==1) message+=getString(R.string.diarrhoea_name)+"\n";
                if(symptoms.getFat()==1) message+=getString(R.string.tiredness_name)+"\n";
                if(symptoms.getHda()==1) message+=getString(R.string.headache_name)+"\n";
                if(symptoms.getOdo()==1) message+=getString(R.string.no_smell_name)+"\n";
                if(symptoms.getSth()==1) message+=getString(R.string.throat_pain)+"\n";
                if(symptoms.getTou()==1) message+=getString(R.string.no_taste_name)+"\n";
                if(!symptoms.getOth().isEmpty()) message+=symptoms.getOth()+"\n";
                if(symptoms.getFiv()!=34.0) message+=getString(R.string.fever_name)+": "+symptoms.getFiv()+"\n";
            }
            else
            {
                message+=getString(R.string.no_symptoms)+'\n';
            }

            // Εάν έχει επιλεγεί το CheckBox για τις στενές επαφές.
            if(checkBox_contacts.isChecked())
            {
                contacts= myHandler.showContacts(sdf.format(dates.get(i))); // αν υπάρχουν στένες επαφές τις προσθέτουμε στο μήνυμα

                if(contacts != null && !contacts.getPersonA().isEmpty()){
                    message+=getString(R.string.contacts_name)+"\n";
                    if(!contacts.getPersonA().isEmpty()){
                        message += contacts.getPersonA()+'\n';
                    }
                    if(!contacts.getPersonB().isEmpty()){
                        message += contacts.getPersonB()+'\n';
                    }
                    if(!contacts.getPersonC().isEmpty()){
                        message += contacts.getPersonC()+'\n';
                    }
                    if(!contacts.getPersonD().isEmpty()){
                        message += contacts.getPersonD()+'\n';
                    }
                    if(!contacts.getPersonE().isEmpty()){
                        message += contacts.getPersonE()+'\n';
                    }
                }
                else
                {
                    message+=getString(R.string.no_contacts)+"\n";
                }
            }


        }

        return  message;
    }

    // μέθοδος με την οποία μεταβαίνουμε στα μηνύματα
    public void sendMessage(View v) {
        try{
            dates = calendar_view.getSelectedDates(); // λίστα με τις ημερομηνίες που επέλεξε ο χρήστης

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + ""));
            intent.putExtra("sms_body", getMessage(dates)); // περνάμε το μήνυμα
            startActivity(intent);
        }
        catch(Exception e){
            Toast.makeText(Send.this, getString(R.string.fail), Toast.LENGTH_SHORT).show();
        }
    }
}
