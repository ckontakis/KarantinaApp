package com.example.myapplication;

// κλάση που αναπαριστά όλα τα συμπτώματα μίας συγκεκριμένης ημέρας.
public class Illness {

    private String idate; // ημέρα.
    // συμπτώματα.
    private int bla;
    private int fat;
    private int cou;
    private int cat;
    private int sth;
    private int brd;
    private int tou;
    private int odo;
    private int dia;
    private int hda;
    private double fiv;
    private String oth;

    // κενός κατασκευαστής.
    public Illness() {

    }

    // κατασκευαστής που δέχεται ημέρα και συμπτώματα
    public Illness(String idate, int bla, int fat, int cou, int cat, int sth, int brd, int tou, int odo, int dia, int hda, double fiv, String oth) {
        this.idate = idate;
        this.bla = bla;
        this.fat = fat;
        this.cou = cou;
        this.cat = cat;
        this.sth = sth;
        this.brd = brd;
        this.tou = tou;
        this.odo = odo;
        this.dia = dia;
        this.hda = hda;
        this.fiv = fiv;
        this.oth = oth;
    }

    // Getters για τα πεδία.
    public String getIdate() { return idate; }
    public int getBla() { return bla; }
    public int getFat() { return fat; }
    public int getCou() { return cou; }
    public int getCat() { return cat; }
    public int getSth() { return sth; }
    public int getBrd() { return brd; }
    public int getTou() { return tou; }
    public int getOdo() { return odo; }
    public int getDia() { return dia; }
    public int getHda() { return hda; }
    public double getFiv() { return fiv; }
    public String getOth() { return oth; }

    // Setters για τα πεδία.
    public void setId(String idate)   { this.idate = idate; }
    public void setBla(int bla) { this.bla = bla; }
    public void setFat(int fat) { this.fat = fat; }
    public void setCou(int cou) { this.cou = cou; }
    public void setCat(int cat) { this.cat = cat; }
    public void setSth(int sth) { this.sth = sth; }
    public void setBrd(int brd) { this.brd = brd; }
    public void setTou(int tou) { this.tou = tou; }
    public void setOdo(int odo) { this.odo = odo; }
    public void setDia(int dia) { this.dia = dia; }
    public void setHda(int hda) { this.hda = hda; }
    public void setFiv(double fiv) { this.fiv = fiv; }
    public void setOth(String oth) { this.oth = oth; }
}


