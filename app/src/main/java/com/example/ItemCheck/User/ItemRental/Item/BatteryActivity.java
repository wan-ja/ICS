package com.example.ItemCheck.User.ItemRental.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ItemCheck.R;

public class BatteryActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");

        textView.setText(str);
    }
}