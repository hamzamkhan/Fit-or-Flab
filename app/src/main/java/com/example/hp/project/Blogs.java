package com.example.hp.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Blogs extends AppCompatActivity {
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blogs);
        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View arg0)
            {

                Intent intent = new Intent(context, WebViewActivity.class);
                startActivity(intent);
            }
        });


    }
}
