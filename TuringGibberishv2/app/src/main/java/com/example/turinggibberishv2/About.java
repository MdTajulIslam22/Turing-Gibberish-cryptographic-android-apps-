package com.example.turinggibberishv2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class About extends AppCompatActivity {
    Button intro, introduction, mainPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        intro = (Button) findViewById(R.id.intro_animation);
        introduction = (Button) findViewById(R.id.introduction_page);
        mainPage = (Button) findViewById(R.id.main_page);


        mainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, MainPage.class);
                startActivity(i);
            }
        });

        introduction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, MainActivity.class);
                startActivity(i);
            }
        });

        intro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(About.this, Introductory.class);
                startActivity(i);
            }
        });
    }
}