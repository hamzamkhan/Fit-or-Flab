package com.example.hp.project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
//import android.support.constraint.solver.Goal;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.*;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import android.app.Activity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Main_ extends Activity{

    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        mAuth = FirebaseAuth.getInstance();




//        databasehelper_ db = new databasehelper_(this);
//        db.open();
//
//        int numberRows = db.count("user_table");
//
//        if(numberRows < 1){
//
//            Intent i = new Intent(Main_.this, LoginActivity.class);
//            startActivity(i);
//            finish();
//        }
//        else{
//            Intent i = new Intent(Main_.this, MainActivity.class);
//            startActivity(i);
//            finish();
//
//        }

    }

    @Override
    public void onStart()
    {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        if (currentUser==null)
        {
            Intent i = new Intent(Main_.this,LoginActivity.class);
            startActivity(i);
            finish();
        }

        else
        {
            Intent i = new Intent(Main_.this,MainActivity.class);
            startActivity(i);
            finish();
        }

    }



}
