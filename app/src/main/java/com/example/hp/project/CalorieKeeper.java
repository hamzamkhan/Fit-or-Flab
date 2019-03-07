package com.example.hp.project;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.view.View;

public class CalorieKeeper extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_keeper);

        ImageView imageViewAddBreakfast= (ImageView) findViewById(R.id.imageViewAddBreakfast);
        ImageView  imageViewEnergyLunch= (ImageView) findViewById(R.id. imageViewAddLunch);
        ImageView imageViewAddBeforeTraining= (ImageView) findViewById(R.id. imageViewAddBeforeTraining);
        ImageView imageViewAddAfterTraining = (ImageView) findViewById(R.id.   imageViewAddAfterTraining );
        ImageView imageViewAddDinner= (ImageView) findViewById(R.id.   imageViewAddDinner);
        ImageView  imageViewAddSnacks= (ImageView) findViewById(R.id.   imageViewAddSnacks);
        ImageView  imageViewAddSupper= (ImageView) findViewById(R.id. imageViewAddSupper);






        imageViewAddBreakfast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });

        imageViewEnergyLunch.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });


        imageViewAddBeforeTraining.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });


        imageViewAddAfterTraining .setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });


        imageViewAddDinner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });

        imageViewAddSnacks.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });

        imageViewAddBreakfast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });

        imageViewAddSupper.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(CalorieKeeper.this, FoodSearch.class);
                startActivity(i);
            }
        });
    }
}
