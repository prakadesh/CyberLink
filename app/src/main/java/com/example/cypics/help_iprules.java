package com.example.cypics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help_iprules extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_iprules);
        getSupportActionBar().setTitle("Help/IP Rules");
        button = findViewById(R.id.button4);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Hacking.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button6);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Phishing.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button7);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Cyberstalking.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button8);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Identity_theft.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button9);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Cyberbullying.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button10);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Online_defamation.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Child_pornography.class);
                startActivity(intent);
            }
        });
        button = findViewById(R.id.button12);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(help_iprules.this,Cyber_terrorism.class);
                startActivity(intent);
            }
        });
    }
}