package com.example.ItemCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LendActivity extends AppCompatActivity {

    private Button btn_recycle, btn_first_aid, btn_battery, btn_count, btn_umbrella;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend);

        btn_recycle = findViewById(R.id.btn_recycle);
        btn_first_aid = findViewById(R.id.btn_first_aid);
        btn_battery = findViewById(R.id.btn_battery);
        btn_count = findViewById(R.id.btn_count);
        btn_umbrella = findViewById(R.id.btn_umbrella);

        btn_recycle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LendActivity.this, RecycleActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_first_aid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LendActivity.this, FirstaidActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_battery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LendActivity.this, BatteryActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LendActivity.this, CountActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_umbrella.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LendActivity.this, UmbrellaActivity.class);
                startActivity(intent); // 화면 전환
            }
        });
    }
}