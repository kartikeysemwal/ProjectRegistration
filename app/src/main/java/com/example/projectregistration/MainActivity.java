package com.example.projectregistration;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_TEXT_NAME = "com.example.projectregistration.EXTRA_TEXT_NAME";
    public static final String EXTRA_TEXT_GENDER = "com.example.projectregistration.EXTRA_TEXT_GENDER";
    public static final String EXTRA_TEXT_DOB = "com.example.projectregistration.EXTRA_TEXT_DOB";
    public static final String EXTRA_TEXT_PLACE = "com.example.projectregistration.EXTRA_TEXT_PLACE";

    private static final String TAG = "MainActivity";

    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;

    String date;

    public void resetFunction(View view){
        EditText firstName = findViewById(R.id.firstName);
        firstName.setText("");

        EditText lastName = findViewById(R.id.lastName);
        lastName.setText("");

        EditText place = findViewById(R.id.textPlace);
        place.setText("");

        RadioButton buttonClicked = findViewById(R.id.radioMale);
        buttonClicked.setChecked(true);

        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        mDisplayDate.setText("PICK A DATE");
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDisplayDate = (TextView) findViewById(R.id.tvDate);
        addListenerOnButton();


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        MainActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                Log.d(TAG, "onDateSet: mm/dd/yyy: " + month + "/" + day + "/" + year);

                date = month + "/" + day + "/" + year;
                mDisplayDate.setText(date);
            }
        };

        Button button1 = (Button) findViewById(R.id.nextButton);
        button1.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        Intent myIntent = new Intent(v.getContext(), SecondPage.class);
                        EditText firstName = (EditText) findViewById(R.id.firstName);
                        EditText lastName = (EditText) findViewById(R.id.lastName);
                        int selectedId = radioSexGroup.getCheckedRadioButtonId();
                        radioSexButton = (RadioButton) findViewById(selectedId);
                        String gender = radioSexButton.getText().toString();


                        String fullName = firstName.getText().toString() + " " + lastName.getText().toString();
                        EditText place = (EditText) findViewById(R.id.textPlace);
                        String place_name = place.getText().toString();

                        if(firstName.getText().toString().length() == 0)
                            firstName.setError("First name is required");
                        else if(lastName.getText().toString().length() == 0)
                            lastName.setError("Last name is required");
                        else if(date == "null")
                            Toast.makeText(MainActivity.this, "Date of birth is required", Toast.LENGTH_SHORT).show();
                        else if(place.getText().toString().length() == 0)
                            place.setError("Place is required");
                        else {
                            myIntent.putExtra(EXTRA_TEXT_NAME, fullName);
                            myIntent.putExtra(EXTRA_TEXT_PLACE, place_name);
                            myIntent.putExtra(EXTRA_TEXT_DOB, date);
                            myIntent.putExtra(EXTRA_TEXT_GENDER, gender);

                            startActivity(myIntent);
                        }
                    }
                }
        );

    }

    public void addListenerOnButton() {

        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        btnDisplay = (Button) findViewById(R.id.nextButton);

        btnDisplay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(selectedId);
            }

        });
    }
}

