package com.example.hp.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by hamza on 11/25/2017.
 */

public class ViewDietSuggestions extends Activity {
    Button wloss,wgain;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diet_suggestions);

        wloss = (Button) findViewById(R.id.btn_wloss);
        wgain = (Button) findViewById(R.id.btn_wgain);

        wloss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDietSuggestions.this,ViewWeightLoss.class);
                startActivity(intent);
            }
        });

        wgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDietSuggestions.this,ViewWeightGain.class);
                startActivity(intent);
            }
        });
    }
}
