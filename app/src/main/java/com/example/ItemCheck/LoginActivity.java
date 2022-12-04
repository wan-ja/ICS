package com.example.ItemCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ItemCheck.User.UsedList.CheckActivity;
import com.example.ItemCheck.User.ItemRental.LendActivity;

public class LoginActivity extends AppCompatActivity {

    private Button btn1, btn2;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.textView);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LendActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CheckActivity.class);
                intent.putExtra("str", str);
                startActivity(intent); // 화면 전환
            }
        });


    }

}

