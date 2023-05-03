package com.example.cypics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.text.TextWatcher;
import android.text.Editable;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class register_complaint extends AppCompatActivity {
    EditText dateformat;
    EditText editText;
    Button button;
    int year, month, day;
    TextView predictionTextView;
    TextView prediction1TextView;
    TextView prediction1ResultTextView;
    TextView prediction2TextView;
    TextView prediction2ResultTextView;
    TextView ensemblePredictionTextView;
    TextView ensemblePredictionResultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_complaint);
        getSupportActionBar().hide();

        dateformat = findViewById(R.id.dateformatID);
        editText = findViewById(R.id.editTextpredict);
        button = findViewById(R.id.onpresspredict);

        predictionTextView = findViewById(R.id.predictionTextView);
        prediction1TextView = findViewById(R.id.prediction1TextView);
        prediction1ResultTextView = findViewById(R.id.prediction1ResultTextView);
        prediction2TextView = findViewById(R.id.prediction2TextView);
        prediction2ResultTextView = findViewById(R.id.prediction2ResultTextView);
        ensemblePredictionTextView = findViewById(R.id.ensemblePredictionTextView);
        ensemblePredictionResultTextView = findViewById(R.id.ensemblePredictionResultTextView);

        if (prediction1ResultTextView.getText().toString().equals("N/A")) {
            predictionTextView.setVisibility(View.GONE);
            prediction1TextView.setVisibility(View.GONE);
            prediction1ResultTextView.setVisibility(View.GONE);
            prediction2TextView.setVisibility(View.GONE);
            prediction2ResultTextView.setVisibility(View.GONE);
            ensemblePredictionTextView.setVisibility(View.GONE);
            ensemblePredictionResultTextView.setVisibility(View.GONE);
        } else {
            predictionTextView.setVisibility(View.VISIBLE);
            prediction1TextView.setVisibility(View.VISIBLE);
            prediction1ResultTextView.setVisibility(View.VISIBLE);
            prediction2TextView.setVisibility(View.VISIBLE);
            prediction2ResultTextView.setVisibility(View.VISIBLE);
            ensemblePredictionTextView.setVisibility(View.VISIBLE);
            ensemblePredictionResultTextView.setVisibility(View.VISIBLE);
        }

        // Disable the button initially
        button.setEnabled(false);

        // Add text change listener to the EditText
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Check if EditText is empty or not
                if (editText.getText().toString().trim().isEmpty()) {
                    // Disable the Button if EditText is empty
                    button.setEnabled(false);
                } else {
                    // Enable the Button if EditText is not empty
                    button.setEnabled(true);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        Calendar calendar = Calendar.getInstance();
        dateformat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(register_complaint.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

                        //Showing the picked value in the textView
                        dateformat.setText(String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day));



                    }
                }, 2023, 01, 20);
                datePickerDialog.show();
            }
        });
    }
}
