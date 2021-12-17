package com.example.hamburguesapp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

public class ProductosUsuarioActvity extends AppCompatActivity {

    private DbHelper dbHelper;
    private ArrayList<Combo> listaCombos;
    private GridView gridView;
    ActionBar actionBar;

    public ArrayList<Combo> llenarLista(Cursor cursor){
        ArrayList<Combo> list = new ArrayList<>();
        if(cursor.getCount() == 0){
            return list;
        }else{
            StringBuffer buffer = new StringBuffer();
            while(cursor.moveToNext()){
                byte[] image = cursor.getBlob(2);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                Combo combo = new Combo(
                        cursor.getString(1),
                        cursor.getBlob(2)
                );
                list.add(combo);
            }
            return list;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos_usuario_actvity);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.logo_foreground);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f6a33e")));

        dbHelper = new DbHelper(getApplicationContext());
        gridView = (GridView) findViewById(R.id.grid_view);

        Cursor cursor = dbHelper.getCombo();
        listaCombos = llenarLista(cursor);
        ComboAdapter comboAdapter = new ComboAdapter(getApplicationContext(), listaCombos);
        gridView.setAdapter(comboAdapter);

    }
}