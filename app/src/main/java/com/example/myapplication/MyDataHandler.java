package com.example.myapplication;

import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.Context;
import android.content.ContentValues;

import com.google.android.material.internal.NavigationMenu;



public class MyDataHandler extends SQLiteOpenHelper {

    //Σταθερές για τη ΒΔ (όνομα ΒΔ, έκδοση, πίνακες κλπ)
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "myApp.db";

    public static final String TABLE_PERSON = "person";
    public static final String TABLE_SYMPTOMS = "symptoms";
    public static final String TABLE_CONTACTS = "contacts";

    public static final String COLUMN_ID = "perseid";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_SURNAME = "surname";
    public static final String COLUMN_EMAIL = "email";

    public static final String COLUMN_IDATE = "symdate";
    public static final String COLUMN_BLA = "bellyache";
    public static final String COLUMN_FAT = "fatigue";
    public static final String COLUMN_COU = "cough";
    public static final String COLUMN_CAT = "catarrh";
    public static final String COLUMN_STH = "sore_throat";
    public static final String COLUMN_BRD = "breathing_difficulty";
    public static final String COLUMN_TOU = "touch";
    public static final String COLUMN_ODO = "odorless";
    public static final String COLUMN_DIA = "diarrhea";
    public static final String COLUMN_HDA = "headache";
    public static final String COLUMN_FIV = "fiver";
    public static final String COLUMN_OTH = "other";

    public static final String COLUMN_IDCON = "date";
    public static final String COLUMN_CON1 = "contactA";
    public static final String COLUMN_CON2 = "contactB";
    public static final String COLUMN_CON3 = "contactC";
    public static final String COLUMN_CON4 = "contactD";
    public static final String COLUMN_CON5 = "contactE";


    //Κατασκευαστής
    public MyDataHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    //Δημιουργία του σχήματος της ΒΔ
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_PERSON_TABLE = "CREATE TABLE " + TABLE_PERSON +"(" +
                COLUMN_ID + " INTEGER PRIMARY KEY ," +
                COLUMN_NAME + " VARCHAR(20) ," +
                COLUMN_SURNAME + " VARCHAR(45) ," +
                COLUMN_EMAIL + " VARCHAR(255) " +");";

        String CREATE_TABLE_SYMPTOMS = "CREATE TABLE " + TABLE_SYMPTOMS + "(" +
                COLUMN_IDATE + " VARCHAR (20) PRIMARY KEY NOT NULL," +
                COLUMN_BLA + " INTEGER NOT NULL," +
                COLUMN_FAT + " INTEGER NOT NULL," +
                COLUMN_COU + " INTEGER NOT NULL," +
                COLUMN_CAT + " INTEGER NOT NULL," +
                COLUMN_STH + " INTEGER NOT NULL," +
                COLUMN_BRD + " INTEGER NOT NULL," +
                COLUMN_TOU + " INTEGER NOT NULL," +
                COLUMN_ODO + " INTEGER NOT NULL," +
                COLUMN_DIA + " INTEGER NOT NULL," +
                COLUMN_HDA + " INTEGER NOT NULL," +
                COLUMN_FIV + " DOUBLE NOT NULL," +
                COLUMN_OTH + " MEDIUMTEXT "+");";

        String CREATE_TABLE_CONTACTS = "CREATE TABLE " + TABLE_CONTACTS + "(" +
                COLUMN_IDCON + " VARCHAR (20) ," +
                COLUMN_CON1 + " VARCHAR (45) ," +
                COLUMN_CON2 + " VARCHAR (45) ," +
                COLUMN_CON3 + " VARCHAR (45) ," +
                COLUMN_CON4 + " VARCHAR (45) ," +
                COLUMN_CON5 + " VARCHAR (45) " + ");";




        db.execSQL(CREATE_PERSON_TABLE);
        db.execSQL(CREATE_TABLE_CONTACTS);
        db.execSQL(CREATE_TABLE_SYMPTOMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PERSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SYMPTOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);

        onCreate(db);
    }

    // μέθοδος για εισαγωγή ατόμου στην βάση
    public void addPerson(Person george) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID,1);
        values.put(COLUMN_NAME, george.getName());
        values.put(COLUMN_SURNAME, george.getSurname());
        values.put(COLUMN_EMAIL, george.getEmail());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_PERSON, null, values);
        db.close();
    }


    // μέθοδος για την εισαγωγή συμπτωμάτων για μία ημέρα στην βάση
    public void setSymptoms(Illness symptoms) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDATE, symptoms.getIdate());
        values.put(COLUMN_BLA, symptoms.getBla());
        values.put(COLUMN_FAT, symptoms.getFat());
        values.put(COLUMN_COU, symptoms.getCou());
        values.put(COLUMN_CAT, symptoms.getCat());
        values.put(COLUMN_STH, symptoms.getSth());
        values.put(COLUMN_BRD, symptoms.getBrd());
        values.put(COLUMN_TOU, symptoms.getTou());
        values.put(COLUMN_ODO, symptoms.getOdo());
        values.put(COLUMN_DIA, symptoms.getDia());
        values.put(COLUMN_HDA, symptoms.getHda());
        values.put(COLUMN_FIV, symptoms.getFiv());
        values.put(COLUMN_OTH, symptoms.getOth());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_SYMPTOMS, null, values);
        db.close();
    }

    // μέθοδος για την ενημέρωση μίας εγγραφής στον πίνακα symptoms
    public void replaceSymptoms(Illness symptoms,String date)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDATE, symptoms.getIdate());
        values.put(COLUMN_BLA, symptoms.getBla());
        values.put(COLUMN_FAT, symptoms.getFat());
        values.put(COLUMN_COU, symptoms.getCou());
        values.put(COLUMN_CAT, symptoms.getCat());
        values.put(COLUMN_STH, symptoms.getSth());
        values.put(COLUMN_BRD, symptoms.getBrd());
        values.put(COLUMN_TOU, symptoms.getTou());
        values.put(COLUMN_ODO, symptoms.getOdo());
        values.put(COLUMN_DIA, symptoms.getDia());
        values.put(COLUMN_HDA, symptoms.getHda());
        values.put(COLUMN_FIV, symptoms.getFiv());
        values.put(COLUMN_OTH, symptoms.getOth());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_SYMPTOMS,values,COLUMN_IDATE+"="+"'"+date+"'",null);
        db.close();

    }


    // μέθοδος για την εισαγωγή μίας επαφής στον πίνακα contacts
    public void addContacts(Friends people) {
        ContentValues values = new ContentValues();
        values.put(COLUMN_IDCON, people.getDate());
        values.put(COLUMN_CON1, people.getPersonA());
        values.put(COLUMN_CON2, people.getPersonB());
        values.put(COLUMN_CON3, people.getPersonC());
        values.put(COLUMN_CON4, people.getPersonD());
        values.put(COLUMN_CON5, people.getPersonE());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_CONTACTS, null, values);

        db.close();
    }

    // μέθοδος για την ενημέρωση μίας εγγραφής στον πίνακα contacts
    public void replaceContacts(Friends people,String date)
    {
        ContentValues values = new ContentValues();
        values.put(COLUMN_CON1, people.getPersonA());
        values.put(COLUMN_CON2, people.getPersonB());
        values.put(COLUMN_CON3, people.getPersonC());
        values.put(COLUMN_CON4, people.getPersonD());
        values.put(COLUMN_CON5, people.getPersonE());
        SQLiteDatabase db = this.getWritableDatabase();
        db.update(TABLE_CONTACTS,values,COLUMN_IDCON+"="+"'"+date+"'",null);
        db.close();

    }


    // μέθοδος που βρίσκει και επιστρέφει μία εγγραφή του πίνακα symptoms με βάση την ημερομηνία
    public Illness showSymptoms(String sId) {
        String query = "SELECT * FROM " + TABLE_SYMPTOMS + " WHERE "  +COLUMN_IDATE+ " = " + "'" +sId + "'";
        System.out.println(query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Illness theIllness = new Illness();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            theIllness.setId(cursor.getString(0));
            theIllness.setBla(Integer.parseInt(cursor.getString(1)));
            theIllness.setFat(Integer.parseInt(cursor.getString(2)));
            theIllness.setCou(Integer.parseInt(cursor.getString(3)));
            theIllness.setCat(Integer.parseInt(cursor.getString(4)));
            theIllness.setSth(Integer.parseInt(cursor.getString(5)));
            theIllness.setBrd(Integer.parseInt(cursor.getString(6)));
            theIllness.setTou(Integer.parseInt(cursor.getString(7)));
            theIllness.setOdo(Integer.parseInt(cursor.getString(8)));
            theIllness.setDia(Integer.parseInt(cursor.getString(9)));
            theIllness.setHda(Integer.parseInt(cursor.getString(10)));
            theIllness.setFiv(Double.parseDouble(cursor.getString(11)));
            theIllness.setOth(cursor.getString(12));
            cursor.close();
        } else {
            theIllness = null;
        }
        db.close();
        return theIllness;
    }

    // μέθοδος που βρίσκει και επιστρέφει μία εγγραφή του πίνακα contacts με βάση την ημερομηνία
    public Friends showContacts(String str) {
        String query = "SELECT * FROM " + TABLE_CONTACTS + " WHERE " + COLUMN_IDCON + " = "+ "'" + str + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Friends contacts = new Friends();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            contacts.setDate(cursor.getString(0));
            contacts.setPerA(cursor.getString(1));
            contacts.setPerB(cursor.getString(2));
            contacts.setPerC(cursor.getString(3));
            contacts.setPerD(cursor.getString(4));
            contacts.setPerE(cursor.getString(5));
            cursor.close();
        } else {
            contacts = null;
        }
        db.close();
        return contacts;
    }

    // μεθοδος που επιστρέφει τον χρήστη
    public Person showPerson()
    {
        String query = "SELECT * FROM " + TABLE_PERSON +" WHERE "+COLUMN_ID+" =1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Person person = new Person();
        if (cursor.moveToFirst()) {
            cursor.moveToFirst();
            person.setName(cursor.getString(1));
            person.setSurname(cursor.getString(2));
            person.setEmail(cursor.getString(3));
            cursor.close();
        } else {
           person = null;
        }
        db.close();
        return person;
    }


    // μέθοδος για την διαγραφή της εγγραφής του πίνακα person
    public boolean deletePerson(String name) {
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_PERSON + " WHERE " + COLUMN_NAME + " = "+"'" + name + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Person george = new Person();
        if (cursor.moveToFirst()) {
            george.setID(1);
            db.delete(TABLE_PERSON, COLUMN_ID + " = ?",
                    new String[]{String.valueOf(george.getID())});
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }
}
