package com.example.hp.project;

import java.sql.Date;
import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class NutritionEvaluator extends Activity
{
    private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    static final int DATE_DIALOG_ID = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //EditText username= (EditText) findViewById(R.id.username_) ;
        Button Food_Diary= (Button) findViewById(R.id.Food_diary);
        Button dietSuggestion = (Button) findViewById(R.id.btn_diet_suggestions);

        databasehelper_ db= new databasehelper_(this);
        final String email = db.get_Username();

        final String Date=GetDate();
        pPickDate = (Button) findViewById(R.id.pickDate);
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);

        //  username.setVisibility(View.GONE);
        Food_Diary.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i= new Intent(NutritionEvaluator.this,  FoodDiary.class);
                i.putExtra("email", email);
                i.putExtra("Date",  Date);
                startActivity(i);
            }

        });



        dietSuggestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NutritionEvaluator.this,ViewDietSuggestions.class);
                startActivity(intent);
            }
        });

    ;
    }


    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;

                }
            };

    /** Updates the date in the TextView */
    private String GetDate() {
        StringBuilder sb = new StringBuilder(15);
        sb.append(pYear);
        sb.append("/");
        sb.append(pMonth);
        sb.append("/");
        sb.append(pDay);
        sb.append("/");
        return sb.toString();
    }



    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate.getText()),  Toast.LENGTH_SHORT).show();

    }
}