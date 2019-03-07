package com.example.hp.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by hamza on 12/4/2017.
 */

public class Depression extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depression_detection);
        webView = (WebView) findViewById(R.id.webView_depression);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.psycom.net/depression-test/");
    }
}
