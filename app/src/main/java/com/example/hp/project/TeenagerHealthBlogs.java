package com.example.hp.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by hamza on 11/25/2017.
 */

public class TeenagerHealthBlogs extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teen_health_blogs);

        webView = (WebView) findViewById(R.id.webView3);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://m.huffpost.com/us/topic/teen-health");

    }
}
