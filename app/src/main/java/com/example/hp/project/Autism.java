package com.example.hp.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by hamza on 12/4/2017.
 */

public class Autism extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autism);
        webView = (WebView) findViewById(R.id.webView_autism);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://psychcentral.com/quizzes/autism-quiz.htm");
    }
}
