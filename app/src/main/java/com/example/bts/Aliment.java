package com.example.bts;

import android.os.Parcel;
import android.os.Parcelable;

public class Aliment implements Parcelable {
    private int idAliment;
    private String nom;
    private String description;
    private int calories;
    private int solid;

    public Aliment() {
    }

    public Aliment(int idAliment, String nom, String description, int calories, int solid) {
        this.idAliment = idAliment;
        this.nom = nom;
        this.description = description;
        this.calories = calories;
        this.solid = solid;
    }

    protected Aliment(Parcel in) {
        idAliment = in.readInt();
        nom = in.readString();
        description = in.readString();
        calories = in.readInt();
        solid = in.readInt();
    }

    public static final Creator<Aliment> CREATOR = new Creator<Aliment>() {
        @Override
        public Aliment createFromParcel(Parcel in) {
            return new Aliment(in);
        }

        @Override
        public Aliment[] newArray(int size) {
            return new Aliment[size];
        }
    };

    public int getIdAliment() {
        return idAliment;
    }

    public void setIdAliment(int idAliment) {
        this.idAliment = idAliment;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public int getSolid() {
        return solid;
    }

    public void setSolid(int solid) {
        this.solid = solid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(idAliment);
        parcel.writeString(nom);
        parcel.writeString(description);
        parcel.writeInt(calories);
        parcel.writeInt(solid);
    }
}
