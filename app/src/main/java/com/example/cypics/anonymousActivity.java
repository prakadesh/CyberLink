package com.example.cypics;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

public class anonymousActivity extends AppCompatActivity {
Spinner spinner;
ArrayList<String> arrType = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anonymous);

        getSupportActionBar().setTitle("Anonymous Complaint");

        spinner = findViewById(R.id.spinner);

        arrType.add("1");
        arrType.add("2");
        arrType.add("other");

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrType);
        spinner.setAdapter(spinnerAdapter);

    }
}