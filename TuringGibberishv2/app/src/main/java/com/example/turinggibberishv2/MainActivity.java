package com.example.turinggibberishv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button aboutButton;
    Button nextPageButton;
    boolean clicked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        aboutButton = (Button) findViewById(R.id.about_button);
        nextPageButton = (Button)findViewById(R.id.next_button);

//        nextPageButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this, MainPage.class);
//                startActivity(i);
//            }
//        });


        clicked = false;
        //make a file and try again:
        aboutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
                Intent i = new Intent(MainActivity.this, About.class);
                startActivity(i);
            }
        });

        nextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clicked = true;
                Intent i = new Intent(MainActivity.this, MainPage.class);
                startActivity(i);
            }
        });

        if(clicked){

        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent i = new Intent(getApplicationContext(), MainPage.class);
                    startActivity(i);
                }
            }, 10000);
        }

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent i = new Intent(getApplicationContext(), MainPage.class);
//                startActivity(i);
//            }
//        }, 10000);
    }
}