package com.example.hp.project;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalorieDiary extends Activity implements View.OnClickListener {


    databasehelper_ myDB;
    final Calendar cal = Calendar.getInstance();
    DateFormat dateFormat = new SimpleDateFormat("dd/mm/yy", Locale.ENGLISH);

    public Button btn_addbreakfast;
    public Button btn_addlunch;
    public Button btn_adddinner;
    public Button summaryButton;
    public String dateSelected;
    public CalendarView calView;
    public ListView diaryListView;
    public int state;
    public TextView tv1;
    public TextView tv2;
    public TextView tv3;
    public String today;
    public String userSignedIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caloriediary);

        btn_addbreakfast = (Button)findViewById(R.id.fAddButtonBreakfast);
        btn_adddinner = (Button) findViewById(R.id.fAddButtonDInner);
        btn_addlunch = (Button) findViewById(R.id.fAddButtonLunch);
        summaryButton = (Button)findViewById(R.id.fsummaryButton);
        calView = (CalendarView)findViewById(R.id.calendarView);
        diaryListView = (ListView)findViewById(R.id.diaryListView);

        tv1 = (TextView)findViewById(R.id.dateSelect1);
        tv2 = (TextView)findViewById(R.id.dateSelect2);
        tv3 = (TextView)findViewById(R.id.dateSelect3);

        today = getCurrentDate();

        dateSelected = getCurrentDate();

        tv1.setTextColor(Color.argb(100, 0, 0, 0));
        tv3.setTextColor(Color.argb(100, 0, 0, 0));

        tv1.setOnClickListener(this);
        tv3.setOnClickListener(this);

        updateScreen();

        userSignedIn = getIntent().getStringExtra("email");

        state = 0;

        long longToday = getMaxDate(today);
        calView.setMaxDate(new Date().getTime());
        calView.setOnDateChangeListener(new CalendarView.OnDateChangeListener(){

            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                month++;
                tv3.setVisibility(View.VISIBLE);
                String[] newYear = Integer.toString(year).split("");
                String newDay;
                String newMonth;

                if (dayOfMonth<10){
                    newDay = "0" + Integer.toString(dayOfMonth);
                }else{
                    newDay = Integer.toString(dayOfMonth);
                }
                if (month<10){
                    newMonth = "0" + Integer.toString(month);
                }else{
                    newMonth = Integer.toString(month);
                }

                dateSelected = newDay + "/" + newMonth + "/" + newYear[3] + newYear[4];
                updateScreen();

            }
        });

        if (today.equals(dateSelected)){
            tv3.setVisibility(View.INVISIBLE);
        }

        tv2.setOnClickListener(this);
        btn_addbreakfast.setOnClickListener(this);
        btn_addlunch.setOnClickListener(this);
        btn_adddinner.setOnClickListener(this);
        summaryButton.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateScreen();
    }

    public String getCurrentDate(){
        int dd = cal.get(Calendar.DAY_OF_MONTH);
        int mm = cal.get(Calendar.MONTH);
        int yy = cal.get(Calendar.YEAR);
        String today = String.valueOf(dd) + "/" + (mm + 1) + "/" + yy;
        Date temp = null;
        try {
            temp = dateFormat.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        today = dateFormat.format(temp);
        //String today = dateFormat.format(cal.getTime());
        return today;
    }

    public String dateSelect(String currentDate, int operation){
        try {
            Date tempDate = dateFormat.parse(currentDate);
            cal.setTime(tempDate);
            cal.add(Calendar.DATE,operation);
            int dd = cal.get(Calendar.DATE);
            int mm = cal.get(Calendar.MONTH);
            int yy = cal.get(Calendar.YEAR);
            //String newDate = String.valueOf(dd) + "/" + (mm+1) + "/" + yy;
            String newDate = dateFormat.format(cal.getTime());
            return newDate;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fAddButtonBreakfast:
                addDiaryItem();
                break;
            case R.id.fsummaryButton:
                openSummary();
                break;
            case R.id.dateSelect2:
                ShowHide();
                break;
            case R.id.dateSelect1:
                tv3.setVisibility(View.VISIBLE);
                dateSelected = tv1.getText().toString();
                updateScreen();
                break;
            case R.id.dateSelect3:
                tv3.setVisibility(View.VISIBLE);
                dateSelected = tv3.getText().toString();
                updateScreen();
                break;
            case R.id.fAddButtonDInner:
                addDiaryItem();
                break;
            case R.id.fAddButtonLunch:
                addDiaryItem();
                break;
        }

    }


    public void addDiaryItem(){
        Intent intent = new Intent(this, FoodSearch.class);
        intent.putExtra("date",dateSelected);
        intent.putExtra("fromDiary", true);
        intent.putExtra("email", userSignedIn);
        startActivity(intent);
    }

    public void openSummary(){
        Intent intent = new Intent(this, Summary.class);
        intent.putExtra("DATE", dateSelected);
        intent.putExtra("email", userSignedIn);
        startActivity(intent);
    }


    public Long getMaxDate(String today){
        String[] todayArray= today.split("/");
        todayArray[2] = "20" + todayArray[2];
        String month = todayArray[1];
        todayArray[1] = todayArray [0];
        todayArray[0] = month;
        String newToday = todayArray[0]+todayArray[1]+todayArray[2];
        return Long.parseLong(newToday);
    }

    public void ShowHide(){
        if (state == 0){
            diaryListView.setVisibility(View.GONE);
            calView.setVisibility(View.VISIBLE);
            state = 1;
        }
        else{
            diaryListView.setVisibility(View.VISIBLE);
            calView.setVisibility(View.GONE);
            state = 0;
        }
    }

    public void Get_Calories(){
        ListView diaryListView2 = (ListView) findViewById(R.id.diaryListView2);
        myDB = new databasehelper_(this);
        Cursor cursor = myDB.getDiary(dateSelected, userSignedIn);
        String[] from = new String[]{"CALORIES", "CALORIES"};
        int[] to = new int[]{R.id.listViewName, R.id.diaryListView2};
        SimpleCursorAdapter sca = new SimpleCursorAdapter(getBaseContext(), R.layout.food_layout, cursor, from, to, 0);
        diaryListView2.setAdapter(sca);

        myDB.close();
    }

    public void populateDiary(){
        myDB = new databasehelper_(this);
        Cursor cursor = myDB.getDiary(dateSelected, userSignedIn);
        String[] from = new String[]{"NAME", "CALORIES"};
        int[] to = new int[]{R.id.listViewName, R.id.listViewCal};

        SimpleCursorAdapter sca = new SimpleCursorAdapter(getBaseContext(), R.layout.food_layout, cursor, from, to, 0);
        diaryListView.setAdapter(sca);
        myDB.close();
    }



    public void updateScreen(){
        tv2.setText(dateSelected);
        tv1.setText(dateSelect(dateSelected, -1));
        tv3.setText(dateSelect(dateSelected, 1));

        tv1.setVisibility(View.INVISIBLE);
        tv3.setVisibility(View.INVISIBLE);
        /*if (dateSelected.equals(today)){
            tv3.setVisibility(View.INVISIBLE);
        }*/
        Get_Calories();
        populateDiary();
    }
}
