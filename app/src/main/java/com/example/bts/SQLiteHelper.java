package com.example.bts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "dbAliment";
    private static final int DATABASE_VERSION = 1;

    public SQLiteHelper(@Nullable Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE aliment (idAliment INTEGER PRIMARY KEY, nom TEXT, description TEXT, calories INTEGER, solid INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS aliment");
    onCreate(db);
    }

    public void addAlim(Aliment u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",u.getNom());
        cv.put("description",u.getDescription());
        cv.put("calories",u.getCalories());
        cv.put("solid",u.getSolid());

        db.insert("aliment", null, cv);
        db.close();
    }

    public void modifAlim(Aliment u){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("nom",u.getNom());
        cv.put("description",u.getDescription());
        cv.put("calories",u.getCalories());
        cv.put("solid",u.getSolid());

        db.update("aliment",cv, "idAliment", new String[]{String.valueOf(u.getIdAliment())});
        db.close();
    }

    public void deleteAlim (int id){
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete("aliment","idAliment", new String[]{String.valueOf(id)});
        db.close();
    }

    public List<Aliment> getAllAliment(){
        List<Aliment> aliments = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String requete = "SELECT * FROM aliment";
        Cursor c = db.rawQuery(requete, null);
        c.moveToFirst();
        while ( !c.isAfterLast()){
            Aliment alim = new Aliment(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3), c.getInt(4));
            aliments.add(alim);
            c.moveToNext();
            String tmp = alim.getNom()+String.valueOf(alim.getCalories());
            Log.i("red",tmp);
        }
        return aliments;
    }
     public Aliment getOneAlim(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor c = db.query("aliment",new String[]{"idAliment","nom","description","calories","solid"},"idAliment=?", new String[]{String.valueOf(id)},null,null,null);
        if (c != null){
            c.moveToFirst();
            Aliment u = new Aliment(c.getInt(0),c.getString(1),c.getString(2),c.getInt(3), c.getInt(4));
            return u;
        } else {
            return null;
        }
    }

}