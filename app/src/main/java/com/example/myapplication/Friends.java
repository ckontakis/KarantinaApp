package com.example.myapplication;

// κλάση που αναπαριστά τις στενές επαφές για μία συγκεκριμένη μέρα.
class Friends {

    private String date; // ημέρα.
    // στενές επαφές.
    private String parA;
    private String parB;
    private String parC;
    private String parD;
    private String parE;

    // κενός κατασκευαστής
    public Friends(){
        parA="";
        parB="";
        parC="";
        parD="";
        parE="";
    }

    // κατασκευαστής που δέχεται ως ορίσματα ημερομηνία και επαφές.
    public Friends(String date, String parA, String parB, String parC, String parD, String parE){
        this.date = date;
        this.parA = parA;
        this.parB = parB;
        this.parC = parC;
        this.parD = parD;
        this.parE = parE;
    }

    // Setters για τα πεδία.
    public void setDate(String string) { date = string; }
    public void setPerA(String string) { parA = string; }
    public void setPerB(String string) { parB = string; }
    public void setPerC(String string) { parC = string; }
    public void setPerD(String string) { parD = string; }
    public void setPerE(String string) { parE = string; }

    // Getters για τα πεδία.
    public String getDate()    {   return date;    }
    public String getPersonA() {   return parA;    }
    public String getPersonB() {   return parB;    }
    public String getPersonC() {   return parC;    }
    public String getPersonD() {   return parD;    }
    public String getPersonE() {   return parE;    }

}


