package com.example.hp.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by hamza on 11/4/2017.
 */

public class FitnessMenu extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_menu);

        Button btn_bmi = (Button) findViewById(R.id.btn_bmi);
        Button btn_bmr = (Button) findViewById(R.id.btn_bmr);
        Button btn_bfp = (Button) findViewById(R.id.btn_bfp);

        btn_bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FitnessMenu.this,BodyMassIndex.class);
                startActivity(i);
            }
        });

        btn_bfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FitnessMenu.this,BodyFatPercentage.class);
                startActivity(i);
            }
        });

        btn_bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(FitnessMenu.this,BasalMetabolicRate.class);
                startActivity(i);
            }
        });
    }

}
