package com.example.hp.project;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.view.View;
import android.support.v7.widget.Toolbar;


public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnSignUp;
    databasehelper_ db;


    GridView grid;
    String[] web = {
            "Fitness Calculator", "Exercise", "Nutrition",
            "Pedometer", "Diseases", "Blogs", "Calorie Keeper"

    };
    int[] imageId = {
            R.drawable.fit_flab_home_fitness_btn, R.drawable.fit_flab_home_exercise_btn,
            R.drawable.fit_flab_home_nutrition_btn, R.drawable.fit_flab_home_pedometer_btn, R.drawable.fit_flab_home_diseases_btn, R.drawable.fit_flab_home_blogs_btn,
            R.drawable.calorie


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
      //  setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        CustomGrid adapter = new CustomGrid(MainActivity.this, web, imageId);
        grid = (GridView) findViewById(R.id.gridView);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(MainActivity.this, "You Clicked at " + web[+position], Toast.LENGTH_SHORT).show();


                if (position == 0) {
                    Intent i = new Intent(MainActivity.this, FitnessMenu.class);
                    startActivity(i);
                }

                if (position == 1) {
                    Intent i = new Intent(MainActivity.this, Exercise.class);
                    startActivity(i);
                }
                if (position == 2) {
                    Intent i = new Intent(MainActivity.this, NutritionEvaluator.class);
                    startActivity(i);
                }
                if (position == 3) {
                    Intent i = new Intent(MainActivity.this, Pedometer.class);
                    startActivity(i);
                }



                if (position == 4) {
                    Intent i = new Intent(MainActivity.this, DiseaseMenu.class);
                    startActivity(i);
                }
                if (position == 5) {
                    Intent i = new Intent(MainActivity.this, Blogs.class);
                    startActivity(i);
                }
                if (position == 6) {

                    Intent i = new Intent(MainActivity.this, Goal.class);
                    startActivity(i);
                }
            }

        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            Intent i = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(i);
            Toast.makeText(MainActivity.this,"Log Out Successful", Toast.LENGTH_LONG).show();
          ;
            // ends the activity
            //this.finish();
        }

        return super.onOptionsItemSelected(item);
    }

}

