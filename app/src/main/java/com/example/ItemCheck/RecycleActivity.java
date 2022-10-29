package com.example.ItemCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RecycleActivity extends AppCompatActivity {

    private Button btn_tear, btn_paste;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        btn_tear = findViewById(R.id.btn_tear);
        btn_paste = findViewById(R.id.btn_paste);

        btn_tear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecycleActivity.this, TearActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_paste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RecycleActivity.this, PasteActivity.class);
                startActivity(intent); // 화면 전환
            }
        });
    }
}