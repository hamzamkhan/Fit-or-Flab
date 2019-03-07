package com.example.hp.project;

import android.os.Bundle;
import android.renderscript.Double2;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hamza on 11/4/2017.
 */

public class BodyFatPercentage extends AppCompatActivity {

    EditText bfp_Height;
    EditText bfp_Weight;
    EditText bfp_Age;
    TextView bfp_res;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_bfp);

        bfp_Age = (EditText) findViewById(R.id.edittext_bfp_age);
        bfp_Height = (EditText) findViewById(R.id.edittext_bfp_height);
        bfp_Weight = (EditText) findViewById(R.id.edittxt_bfp_weight);
        bfp_res = (TextView) findViewById(R.id.txt_res_bfp);

        Button calc_bfp = (Button) findViewById(R.id.btn_calc_bfp);

        calc_bfp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                double weight = Float.parseFloat(bfp_Weight.getText().toString());
                double height = Float.parseFloat(bfp_Height.getText().toString());
                int age = Integer.parseInt(bfp_Age.getText().toString());

                double res = (weight/Math.pow(height,2))*703;
                double final_res = (1.20 * res) + (0.23 * age) - 16.2;
                bfp_res.setText(Double.toString(final_res));


            }
        });

    }

}
