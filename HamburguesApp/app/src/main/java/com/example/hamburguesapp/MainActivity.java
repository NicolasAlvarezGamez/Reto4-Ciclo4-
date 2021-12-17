package com.example.hamburguesapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Configuration;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.io.File;

/**
 * @author Nicolas Alvarez Gamez
 */

public class MainActivity extends AppCompatActivity {

    //Variables
    ActionBar actionBar;
    HomeFragment homeFragment = new HomeFragment();
    MenuFragment menuFragment = new MenuFragment();
    ServiceFragment serviceFragment = new ServiceFragment();


    /**
     * Permite cargar el fragmento principal a la hora de iniciar la app
     * Cambia algunos parámetros de la ActionBar
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottom_navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        loadFragment(homeFragment);

        actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setIcon(R.mipmap.logo_foreground);
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#f6a33e")));

    }

    /**
     * Permite usar el menú
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Personalizar el funcionamiento del botón de compartir en el toolbar
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT,"La Mejor Comida a Tu Disposición");
        intent.putExtra(Intent.EXTRA_TEXT, "Pronto lo Podrás Descargar en Google Play ♥");
        startActivity(Intent.createChooser(intent, "Compartir"));
        return super.onOptionsItemSelected(item);
        }


    /**
     * Hace la lógica para los fragmentos
     *
     * @param fragment
     */
    public void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }

    /**
     * Se usa un Switch para mediante el ID cargar los fragmentos
     */
    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.homeFragment:
                    loadFragment(homeFragment);
                    return true;
                case R.id.menuFragment:
                    loadFragment(menuFragment);
                    return true;
                case R.id.serviceFragment:
                    loadFragment(serviceFragment);
                    return true;
                default:
                    startActivity(new Intent(getApplicationContext(), RestaurantActivity.class));
                    overridePendingTransition(0, 0);
                    return true;
            }

        }
    };
}