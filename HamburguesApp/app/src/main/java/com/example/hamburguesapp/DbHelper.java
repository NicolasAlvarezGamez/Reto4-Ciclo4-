package com.example.hamburguesapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;


    public DbHelper(Context context) {
        super(context, "HamburApp.db", null, 1);
        sqLiteDatabase = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE COMBOS(" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME VARCHAR," +
                "IMAGE BLOB)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS COMBOS");
    }

    //Funciones personalizadas

    public void insertCombo(String name, byte[] image){
        String sql = "INSERT INTO COMBOS VALUES(null, ?, ?)";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeInsert();
    }

    public Cursor getCombo() {
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COMBOS", null);
        return cursor;
    }

    public Cursor getComboById(String id){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM COMBOS WHERE ID ="+id, null);
        return cursor;
    }

    public void deleteCombo(String id) {
        String[] args = new String[]{id};
        sqLiteDatabase.delete("COMBOS", "ID=?", args);
    }

    public void updateCombo(String id, String name, byte[] image){
        String sql = "UPDATE PERSONAJES " +
                "SET NAME = ?," +
                "IMAGE = ?";
        SQLiteStatement statement = sqLiteDatabase.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, name);
        statement.bindBlob(2, image);

        statement.executeUpdateDelete();
    }


}
