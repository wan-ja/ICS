package com.example.ItemCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FirstaidActivity extends AppCompatActivity {

    private Button btn_band, btn_painkiller, btn_digest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstaid);

        btn_band = findViewById(R.id.btn_band);
        btn_painkiller = findViewById(R.id.btn_painkiller);
        btn_digest = findViewById(R.id.btn_digest);

        btn_band.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstaidActivity.this, BandActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_painkiller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstaidActivity.this, PainkillerActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn_digest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FirstaidActivity.this, DigestActivity.class);
                startActivity(intent); // 화면 전환
            }
        });
    }
}