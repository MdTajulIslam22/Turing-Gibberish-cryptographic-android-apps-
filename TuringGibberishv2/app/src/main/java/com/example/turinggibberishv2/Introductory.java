package com.example.turinggibberishv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class Introductory extends AppCompatActivity {
    ImageView logoImage;
    LottieAnimationView lotty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introductory);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        logoImage = (ImageView) findViewById(R.id.logo_image);
        lotty = (LottieAnimationView) findViewById(R.id.lotty);

        logoImage.animate().translationX(200).setDuration(2500).setStartDelay(0);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
            }
        }, 5000);
    }
}