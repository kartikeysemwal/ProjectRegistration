package com.example.projectregistration;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class result_page extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        final String sname = intent.getStringExtra(MainActivity.EXTRA_TEXT_NAME);
        final String splace = intent.getStringExtra(MainActivity.EXTRA_TEXT_PLACE);
        final String sdob = intent.getStringExtra(MainActivity.EXTRA_TEXT_DOB);
        final String scountry = intent.getStringExtra(SecondPage.EXTRA_TEXT_COUNTRY);
        final String sstate = intent.getStringExtra(SecondPage.EXTRA_TEXT_STATE);
        final String spincode = intent.getStringExtra(SecondPage.EXTRA_TEXT_PINCODE);
        final String sgender = intent.getStringExtra(SecondPage.EXTRA_TEXT_GENDER);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        TextView name =  findViewById(R.id.name);
        name.setText(sname);
        TextView gender = findViewById(R.id.gender);
        gender.setText(sgender);
        TextView place = findViewById(R.id.place);
        place.setText(splace);
        TextView dob =  findViewById(R.id.dob);
        dob.setText(sdob);
        TextView country =  findViewById(R.id.country);
        country.setText(scountry);
        TextView state =  findViewById(R.id.state);
        state.setText(sstate);
        TextView pincode = findViewById(R.id.pincode);
        pincode.setText(spincode);
        //Toast.makeText(result_page.this, sname+" "+ splace + " " + sdob + " " + scountry + " " + sstate + " " + spincode, Toast.LENGTH_LONG).show();
    }
}
