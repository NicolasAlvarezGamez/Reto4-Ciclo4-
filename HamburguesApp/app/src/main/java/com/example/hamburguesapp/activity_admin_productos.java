package com.example.hamburguesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.wrappers.PackageManagerWrapper;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class activity_admin_productos extends AppCompatActivity {

    private static final int READ_CODE_GALLERY = 999;
    private Button btnEliminar, btnMostrarLista, btnInsertar, btnConsultar, btnEscoger;
    private EditText edtname, edtId;
    private ImageView imgSelected;
    private DbHelper dbHelper;
    private CasoUsoCombos casoUsoCombos;

    ActionBar actionBar;


    public byte[] imageViewToByte(ImageView imageView){

        Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray =  stream.toByteArray();
        return byteArray;
    }

    public void clean(){
        edtname.setText("");
        imgSelected.setImageResource(R.mipmap.logo_foreground);
    }

    public void showById(Cursor cursor){
        if(cursor.getCount() == 0){
            Toast.makeText(getApplicationContext(), "Combo No Encontrado", Toast.LENGTH_SHORT).show();
        }else{
            StringBuffer buffer = new StringBuffer();
            while(cursor.moveToNext()){
                edtname.setText(cursor.getString(1));
                byte[] image = cursor.getBlob(2);
                Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                imgSelected.setImageBitmap(bitmap);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_productos);

        actionBar = getSupportActionBar();
        actionBar.setIcon(R.mipmap.logo_foreground);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f6a33e")));


        btnInsertar = (Button) findViewById(R.id.btnInsertar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);
        btnConsultar = (Button) findViewById(R.id.btnConsultar);
        btnEscoger = (Button) findViewById(R.id.btnEscoger);
        btnMostrarLista = (Button) findViewById(R.id.btnMostrarLista);

        edtname = (EditText) findViewById(R.id.edtName);
        edtId = (EditText) findViewById(R.id.edtId);

        imgSelected = (ImageView) findViewById(R.id.imgSelected);

        dbHelper = new DbHelper(getApplicationContext());

        casoUsoCombos = new CasoUsoCombos();

        btnEscoger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityCompat.requestPermissions(
                        activity_admin_productos.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        READ_CODE_GALLERY
                );

            }
        });


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Toast.makeText(getApplicationContext(), edtname.getText().toString().trim(), Toast.LENGTH_SHORT).show();
                    dbHelper.insertCombo(
                            edtname.getText().toString().trim(),
                            imageViewToByte(imgSelected)
                    );
                    clean();
                    Toast.makeText(getApplicationContext(), "Se Guardó con Éxito", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                if (id.equals("")) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                } else {
                    dbHelper.deleteCombo(id);
                    Toast.makeText(getApplicationContext(), "Se Borró con Éxito", Toast.LENGTH_SHORT).show();
                    clean();
                }
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtId.getText().toString().trim();
                if (id.equals("")) {
                    Cursor cursor = dbHelper.getCombo();
                    String result = casoUsoCombos.cursorToString(cursor);
                    Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
                } else {
                    Cursor cursor = dbHelper.getComboById(id);
                    showById(cursor);
                }
            }
        });

        btnMostrarLista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_admin_productos.this, ProductosUsuarioActvity.class);
                Toast.makeText(getApplicationContext(), "Se Revisarán los Combos para Añadirlos en un Futuro ☺", Toast.LENGTH_LONG).show();
                startActivity(intent);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == READ_CODE_GALLERY){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, READ_CODE_GALLERY);
            }else{
                Toast.makeText(getApplicationContext(), "No Diste Permisos", Toast.LENGTH_LONG);

            }return;
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == READ_CODE_GALLERY && resultCode == RESULT_OK && data != null ){
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imgSelected.setImageBitmap(bitmap);
            }catch (FileNotFoundException e){
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
