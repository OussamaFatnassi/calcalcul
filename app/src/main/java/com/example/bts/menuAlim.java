package com.example.bts;

public class menuAlim {
    private String nom;
    private String poid;
    private String cal;

    public menuAlim() {
    }

    public menuAlim(String nom, String poid, String cal) {
        this.nom = nom;
        this.poid = poid;
        this.cal = cal;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPoid() {
        return poid;
    }

    public void setPoid(String poid) {
        this.poid = poid;
    }

    public String getCal() {
        return cal;
    }

    public void setCal(String cal) {
        this.cal = cal;
    }

}
