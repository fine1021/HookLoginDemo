package com.example.hooklogin;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = findViewById(R.id.hookText);
        String[] array = getResources().getStringArray(R.array.hook_method);
        StringBuilder builder = new StringBuilder();

        for (String value : array) {
            builder.append(value).append("\n");
        }
        textView.setText(builder.toString());
    }
}

