package com.example.hp.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by hamza on 11/25/2017.
 */

public class SeniorCitizenBlogs extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_senior_citizen_blogs);

        webView = (WebView) findViewById(R.id.webView4);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.everydayhealth.com/senior-health-all-articles/");
    }



}
