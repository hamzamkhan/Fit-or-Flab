package com.example.hp.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by hamza on 12/4/2017.
 */

public class DiseaseMenu extends Activity {
    Button autism,depression,disease;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disease_prediction_menu);

        autism = (Button) findViewById(R.id.btn_dp_autism);
        depression = (Button) findViewById(R.id.btn_dp_depression);
        disease = (Button) findViewById(R.id.btn_dp_disease);


        autism.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiseaseMenu.this,Autism.class);
                startActivity(intent);
            }
        });

        disease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiseaseMenu.this,Symptoms.class);
                startActivity(intent);
            }
        });

        depression.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DiseaseMenu.this,Depression.class);
                startActivity(intent);
            }
        });



    }
}
