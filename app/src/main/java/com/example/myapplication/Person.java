package com.example.myapplication;

// κλάση που αναπαριστά τον χρήστη
public class Person {

    private static int id; //κλειδί
    private static String name; //όνομα
    private static String surname; //επώνυμο
    private static String email; //email


    //κενός κατασκευαστής
    public Person(){

    }

    //κατασκευαστής με ορίσματα όνομα, επίθετό και email
    public Person(String name, String surname, String email){
        this.name = name;
        this.surname = surname;
        this.email = email;
    }

    //Getters για τα πεδία
    public int getID()          { return id;         }
    public String getName()     { return name;       }
    public String getSurname()  { return surname;    }
    public String getEmail()    { return email;      }


    //Setters για τα πεδία
    public void setID(int parseInt)         {  id = parseInt;   }
    public void setName(String string)      { name = string;    }
    public void setSurname(String string)   { surname = string; }
    public void setEmail(String string)     { email = string;   }

}
