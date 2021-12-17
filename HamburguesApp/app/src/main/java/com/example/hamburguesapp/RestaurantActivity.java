package com.example.hamburguesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class RestaurantActivity extends AppCompatActivity {


    //Variables
    ActionBar actionBar;


    /**
     * Permite cargar el fragmento principal a la hora de iniciar la app
     * Cambia algunos parámetros de la ActionBar
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo_foreground);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f6a33e")));

    }

    /**
     * Permite usar el menú
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Personalizar el funcionamiento del botón de compartir en el toolbar
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "La Mejor Comida a Tu Disposición");
        intent.putExtra(Intent.EXTRA_TEXT, "Pronto lo Podrás Descargar en Google Play ♥");
        startActivity(Intent.createChooser(intent, "Compartir"));
        return super.onOptionsItemSelected(item);
    }

    public void Map (View view){
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }
}

