package com.example.hp.project;

import android.app.Activity;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.util.*;
/**
 * Created by hamza on 11/4/2017.
 */

public class BasalMetabolicRate extends Activity {
    EditText bmr_Age;
    EditText bmr_Height;
    EditText bmr_Weight;
    EditText bmr_Gender;
    TextView bmr_Res;
    Button calc_bmr;

    int age;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fitness_bmr);
        bmr_Age = (EditText) findViewById(R.id.edittext_bmr_age);
        bmr_Height = (EditText) findViewById(R.id.edittext_bmr_height);
        bmr_Weight = (EditText) findViewById(R.id.edittext_bmr_weight);
        bmr_Gender = (EditText) findViewById(R.id.edittext_bmr_gender);
        bmr_Res = (TextView) findViewById(R.id.txt_bmr_res);




        calc_bmr = (Button) findViewById(R.id.btn_calc_bmr);

        calc_bmr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                age = Integer.parseInt(bmr_Age.getText().toString());

                double weight = Float.parseFloat(bmr_Weight.getText().toString());
                double height = Float.parseFloat(bmr_Height.getText().toString());
                double res_bmr =0;
                if("Male".equals(bmr_Gender.getText().toString()) || "male".equals(bmr_Gender.getText().toString()) || "M".equals(bmr_Gender.getText().toString()) || "m".equals(bmr_Gender.getText().toString())){

                  res_bmr = 66.5 + (13.75 * weight) + (5.003 * height * 2.54) - (6.775 * age);

                }
                else if("Female".equals(bmr_Gender.getText().toString()) || "female".equals(bmr_Gender.getText().toString()) || "f".equals(bmr_Gender.getText().toString()) || "F".equals(bmr_Gender.getText().toString()))
                {
                    res_bmr =  655.1 + (9.563 * weight) + (1.850 * height * 2.54) - (4.676 * age);

                }
                bmr_Res.setText(Double.toString(res_bmr));
            }
        });
}

}
