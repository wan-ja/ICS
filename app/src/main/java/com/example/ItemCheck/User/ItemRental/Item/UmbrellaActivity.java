package com.example.ItemCheck.User.ItemRental.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.ItemCheck.R;

public class UmbrellaActivity extends AppCompatActivity {

    TextView textView, txt1, txt2, txt3, txt4, txt5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umbrella);

        textView = findViewById(R.id.textView);
        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);
        txt5 = findViewById(R.id.txt5);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");

        textView.setText(str);
    }
}