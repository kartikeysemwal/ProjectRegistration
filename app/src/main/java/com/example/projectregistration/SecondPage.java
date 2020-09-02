package com.example.projectregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class SecondPage extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinnerCountry, spinnerCity;
    String state, country;

    public static final String EXTRA_TEXT_NAME = "com.example.projectregistration.EXTRA_TEXT_NAME";
    public static final String EXTRA_TEXT_GENDER = "com.example.projectregistration.EXTRA_TEXT_GENDER";
    public static final String EXTRA_TEXT_DOB = "com.example.projectregistration.EXTRA_TEXT_DOB";
    public static final String EXTRA_TEXT_PLACE = "com.example.projectregistration.EXTRA_TEXT_PLACE";
    public static final String EXTRA_TEXT_COUNTRY = "com.example.projectregistration.EXTRA_TEXT_COUNTRY";
    public static final String EXTRA_TEXT_STATE = "com.example.projectregistration.EXTRA_TEXT_STATE";
    public static final String EXTRA_TEXT_PINCODE = "com.example.projectregistration.EXTRA_TEXT_PINCODE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

        Intent intent = getIntent();
        final String name = intent.getStringExtra(MainActivity.EXTRA_TEXT_NAME);
        final String place = intent.getStringExtra(MainActivity.EXTRA_TEXT_PLACE);
        final String dob = intent.getStringExtra(MainActivity.EXTRA_TEXT_DOB);
        final String gender = intent.getStringExtra(MainActivity.EXTRA_TEXT_GENDER);

        //Toast.makeText(SecondPage.this, name + " "+ place+ " "+ dob,Toast.LENGTH_SHORT).show();
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);
        spinnerCountry.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
        Button prev = (Button) findViewById(R.id.previousActivity);
        prev.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Intent myIntent = new Intent(v.getContext(), MainActivity.class);
                        startActivity(myIntent);
                    }
                }
        );

        Button next = (Button) findViewById(R.id.submitButton);
        next.setOnClickListener(
                new Button.OnClickListener()
                {
                    public void onClick(View v)
                    {
                        Intent myIntent = new Intent(v.getContext(), result_page.class);

                        EditText pin = (EditText) findViewById(R.id.pincode);
                        String pincode = pin.getText().toString();
                        CheckBox mark = findViewById(R.id.checkBox);

                        if(pin.getText().toString().length() == 0)
                            pin.setError("Pincode is required");
                        if(!mark.isChecked())
                            Toast.makeText(SecondPage.this, "Agree terms and condition", Toast.LENGTH_SHORT).show();
                        else {
                            myIntent.putExtra(EXTRA_TEXT_NAME, name);
                            myIntent.putExtra(EXTRA_TEXT_PLACE, place);
                            myIntent.putExtra(EXTRA_TEXT_DOB, dob);
                            myIntent.putExtra(EXTRA_TEXT_COUNTRY, country);
                            myIntent.putExtra(EXTRA_TEXT_STATE, state);
                            myIntent.putExtra(EXTRA_TEXT_PINCODE, pincode);
                            myIntent.putExtra(EXTRA_TEXT_GENDER, gender);

                            startActivity(myIntent);
                        }
                    }
                }
        );

    }
    public void onItemSelected(final AdapterView<?> parent, View arg1, int pos, long arg3) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.BLACK);
        ((TextView) parent.getChildAt(0)).setTextSize(20);
        parent.getItemAtPosition(pos);
        if (pos == 0) {
            country = "India";
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.city_india,
                            android.R.layout.simple_dropdown_item_1line);
            spinnerCity.setAdapter(adapter);
            spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ((TextView) parent.getChildAt(0)).setTextSize(20);
                    parent.getItemAtPosition(i);
                    if(i==0){
                      state = "Mumbai";
                    }
                    if(i==1){
                        state = "Chennai";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } else if (pos == 1) {
            country = "Sri Lanka";
            ArrayAdapter<CharSequence> adapter = ArrayAdapter
                    .createFromResource(this, R.array.city_srilanka,
                            android.R.layout.simple_dropdown_item_1line);
            spinnerCity.setAdapter(adapter);
            spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    ((TextView) parent.getChildAt(0)).setTextSize(20);
                    parent.getItemAtPosition(i);
                    if(i==0){
                        state = "Colombo";
                    }
                    if(i==1){
                        state = "Moratuwa";
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


