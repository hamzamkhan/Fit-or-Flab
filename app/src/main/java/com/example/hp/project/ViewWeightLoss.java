package com.example.hp.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by hamza on 11/25/2017.
 */

public class ViewWeightLoss extends AppCompatActivity{
    WebView webView;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wloss);

        webView = (WebView) findViewById(R.id.webView5);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://www.goodhousekeeping.com/health/diet-nutrition/g4351/1200-calorie-diet-plan/");
    }
}
