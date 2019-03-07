package com.example.hp.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TotalCalorie extends AppCompatActivity
{
    databasehelper_ myDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_keeper);


        TextView textViewEnergyBreakfast = (TextView)findViewById(R.id.textViewEnergyBreakfast);
        TextView textViewEnergyLunch= (TextView)findViewById(R.id.textViewEnergyLunch);
        TextView textViewEnergyBeforeTraining = (TextView)findViewById(R.id.textViewEnergyBeforeTraining);
        TextView  textViewEnergyAfterTraining = (TextView)findViewById(R.id.textViewEnergyAfterTraining);
        TextView textViewEnergyDinner= (TextView)findViewById(R.id.textViewEnergyDinner);
        TextView textViewEnergySnacks = (TextView)findViewById(R.id.textViewEnergySnacks);
        TextView textViewEnergySupper = (TextView)findViewById(R.id.textViewEnergySupper);

        myDB = new databasehelper_(this);
        String email= myDB.get_Username();
        String selectedDate= "null";
        float[] totals = myDB.getTotals(selectedDate, email);

        textViewEnergyBreakfast.setText(Float.toString(totals[0]));
        textViewEnergyLunch.setText(Float.toString(totals[0]));
        textViewEnergyBeforeTraining .setText(Float.toString(totals[0]));
        textViewEnergyAfterTraining.setText(Float.toString(totals[0]));
        textViewEnergyDinner.setText(Float.toString(totals[0]));
        textViewEnergySnacks.setText(Float.toString(totals[0]));
        textViewEnergySupper .setText(Float.toString(totals[0]));





    }

}
