package com.example.hp.project;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Created by hamza on 11/26/2017.
 */

public class Goal extends Activity {

    Spinner cSpinner;
    String gChoice[],choice;
    Boolean clicked;
    //EditText et_twight,et_cpday,et_cpweek;
    DatabaseHelper db;
   // databasehelper_ db_;
    databasehelper_ data;
    Button addGoal,updateGoal,viewGoal,tokeeper;
    int count1,count2;
    String Tw,wg,dg;

    private TextView pDisplayDate;
    private Button pPickDate;
    private int pYear;
    private int pMonth;
    private int pDay;
    static final int DATE_DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        clicked=false;
        db = new DatabaseHelper(this);
        setContentView(R.layout.activity_goal);


         data=new databasehelper_(this);

        final EditText TargetWeight = (EditText) findViewById(R.id.TargetWeight);
        final EditText DailyGoal = (EditText) findViewById(R.id.DailyGoal);
        final EditText WeeklyGoal= (EditText) findViewById(R.id.WeeklyGoal);
        addGoal = (Button) findViewById(R.id.btn_add_goal);
        updateGoal = (Button) findViewById(R.id.btn_update_goal);
        viewGoal = (Button) findViewById(R.id.btn_view_goal);
        tokeeper = (Button) findViewById(R.id.btn_to_keeper);
        initializeSpinners();



        final String email = data.get_Username();

        pPickDate = (Button) findViewById(R.id.btn_pick_date);
        pPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);
            }
        });
        final Calendar cal = Calendar.getInstance();
        pYear = cal.get(Calendar.YEAR);
        pMonth = cal.get(Calendar.MONTH);
        pDay = cal.get(Calendar.DAY_OF_MONTH);
        final String Date=GetDate();
        //  username.setVisibility(View.GONE);
        tokeeper.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                Intent i= new Intent(Goal.this,  CalorieDiary.class);
                i.putExtra("email", email);
                i.putExtra("Date",  Date);
                startActivity(i);
            }
        });


        addGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                count1 = db.getCount("Goal_Table");
                Tw= TargetWeight.getText().toString();
                wg= WeeklyGoal.getText().toString();
                dg= DailyGoal.getText().toString();
             //  int tweight = Integer.parseInt(et_twight.getText().toString());
              // int cpday = Integer.parseInt(et_cpday.getText().toString());
              //  int cpweek = Integer.parseInt(et_cpweek.getText().toString());
                if(count1<1)
                {   db.insertData(DailyGoal.getText().toString(),WeeklyGoal.getText().toString(),TargetWeight.getText().toString());
                    Toast.makeText(getApplicationContext(), "Goal Added Successfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                        Toast.makeText(getApplicationContext(), "Goal Already Exists", Toast.LENGTH_LONG).show();
                }
            }
        });

        updateGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count2=db.getCount("Goal_Table");
                if(count2>=1)
                {
                    String Tw= TargetWeight.getText().toString();
                    String wg= WeeklyGoal.getText().toString();
                    String dg= DailyGoal.getText().toString();


                         db.updateGoal(DailyGoal.getText().toString(),WeeklyGoal.getText().toString(),TargetWeight.getText().toString());
                        Toast.makeText(getApplicationContext(), "Goal Updated Successfully", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"No Goal Found",Toast.LENGTH_LONG).show();
                }
            }
        });

        viewGoal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Goal.this,ViewGoal.class);
                startActivity(intent);
            }
        });




    }




    //  username.setVisibility(View.GONE);


    private void initializeSpinners()
    {
        gChoice = getResources().getStringArray(R.array.goal_choice);
        cSpinner = (Spinner) findViewById(R.id.goal_choice_spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this,R.array.goal_choice,android.R.layout.simple_spinner_item);
        cSpinner.setAdapter(adapter);
        cSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (clicked)
                {
                    choice = cSpinner.getSelectedItem().toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        pDateSetListener,
                        pYear, pMonth, pDay);
        }
        return null;
    }


    private DatePickerDialog.OnDateSetListener pDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int year,
                                      int monthOfYear, int dayOfMonth) {
                    pYear = year;
                    pMonth = monthOfYear;
                    pDay = dayOfMonth;

                }
            };

    /** Updates the date in the TextView */
    private String GetDate() {
        StringBuilder sb = new StringBuilder(15);
        sb.append(pYear);
        sb.append("/");
        sb.append(pMonth);
        sb.append("/");
        sb.append(pDay);
        sb.append("/");
        return sb.toString();
    }



    private void displayToast() {
        Toast.makeText(this, new StringBuilder().append("Date choosen is ").append(pDisplayDate.getText()),  Toast.LENGTH_SHORT).show();

    }
}
