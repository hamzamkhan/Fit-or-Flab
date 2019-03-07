package com.example.hp.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

/**
 * Created by hamza on 11/25/2017.
 */

public class ViewWeightGain extends AppCompatActivity {
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_wgain);

        webView = (WebView) findViewById(R.id.webView6);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://www.google.com.pk/amp/www.stylecraze.com/articles/4-simple-diet-tips-and-a-diet-chart-to-gain-weight/%3famp=1");
    }
}
