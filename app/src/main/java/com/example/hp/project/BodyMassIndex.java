package com.example.hp.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by hamza on 11/4/2017.
 */

public class BodyMassIndex extends Activity {
    EditText bmi_Height;
    EditText bmi_Weight;
    TextView bmi_Result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_bmi);

        bmi_Result = (TextView) findViewById(R.id.res_bmi);
        bmi_Height = (EditText) findViewById(R.id.editText_bmi_height);
        bmi_Weight = (EditText) findViewById(R.id.editText_bmi_weight);
        Button calcBMI = (Button) findViewById(R.id.btn_calc_bmi);

        calcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double weight = Float.parseFloat(bmi_Weight.getText().toString());
                double height = Float.parseFloat(bmi_Height.getText().toString());

                double res_bmi = (weight / Math.pow(height, 2)) * 703;
                bmi_Result.setText(Double.toString(res_bmi));
            }
        });
    }
}
