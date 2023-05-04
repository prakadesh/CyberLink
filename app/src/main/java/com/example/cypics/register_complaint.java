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
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.*;

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
        int initialTimeoutMs = 5000;
        int maxNumRetries = 3;
        float backoffMultiplier = 1.5f;

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
        // Set an OnClickListener for the button
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the user input from the EditText view
                String userInput = editText.getText().toString();
                // Create a JSON object with the message parameter
                JSONObject jsonBody = new JSONObject();
                try {
                    jsonBody.put("message", userInput);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                //create a request with volley
                String url = "https://flask-ml-model-epics.azurewebsites.net/predict";
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonBody, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Update the UI with the response
                        try {
                            String prediction1 = response.getString("prediction_1");
                            String prediction2 = response.getString("prediction_2");
                            String prediction_ensemble = response.getString("prediction_ensemble");
                            prediction1ResultTextView.setText(prediction1);
                            prediction2ResultTextView.setText(prediction2);
                            ensemblePredictionResultTextView.setText(prediction_ensemble);

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

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                });
                request.setRetryPolicy(new DefaultRetryPolicy(
                        initialTimeoutMs,
                        maxNumRetries,
                        backoffMultiplier
                ));

                // Add the request to the Volley request queue
                Volley.newRequestQueue(register_complaint.this).add(request);

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
        });
    }
}
