package com.example.hamburguesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView splash_logo;
    TextView splash_hamburapp;
    ProgressBar pb;
    int counter = 0;

    private final int Time = 5000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        //Hooks
        splash_logo = findViewById(R.id.splash_logo);
        splash_hamburapp = findViewById(R.id.splash_hamburapp);

        splash_logo.setAnimation(topAnim);
        splash_hamburapp.setAnimation(bottomAnim);

        progress();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this, MainActivity.class);
                startActivity(intent);
            }
        },Time );
    }

    public void progress(){
        pb = (ProgressBar)findViewById(R.id.pb);

        Timer timer= new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                counter++;
                pb.setProgress(counter);

                if(counter == 100)
                    timer.cancel();
            }
        };

        timer.schedule(timerTask, 0, 50);

    }

}