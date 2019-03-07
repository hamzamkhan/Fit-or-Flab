package com.example.hp.project;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hamza on 11/26/2017.
 */

public class ViewGoal extends Activity {

    TextView goal;
    DatabaseHelper db;
    String viewgoal;

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_goal);

        db = new DatabaseHelper(this);
        goal = (TextView) findViewById(R.id.textView_goal);
        viewgoal = db.getGoal();
        int count = db.getCount("Goal_Table");
        if(count>=1)
        {
            goal.setText(viewgoal);

        }
        else
        {
            Toast.makeText(getApplicationContext(),"No Goal Found",Toast.LENGTH_LONG).show();
        }

    }
}
