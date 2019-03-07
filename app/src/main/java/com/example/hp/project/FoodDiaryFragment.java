package com.example.hp.project;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class FoodDiaryFragment extends Fragment {

    databasehelper_ myDB;

    public FoodDiaryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_food_diary_fragment, container, false);
    }

}
