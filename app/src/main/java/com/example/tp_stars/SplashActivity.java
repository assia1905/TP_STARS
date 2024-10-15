package com.example.tp_stars;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import tp.ensa.ma.stargallery.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);

        // Animations
        logo.animate().rotation(360f).setDuration(2000);
        logo.animate().scaleX(0.5f).scaleY(0.5f).setDuration(3000);
        logo.animate().translationYBy(1000f).setDuration(2000);
        logo.animate().alpha(0f).setDuration(6000);

        // Redirection après 5 secondes
        new Thread(() -> {
            try {
                Thread.sleep(5000);
                startActivity(new Intent(SplashActivity.this, ListActivity.class));
                finish();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}

