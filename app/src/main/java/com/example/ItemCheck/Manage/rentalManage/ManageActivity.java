package com.example.ItemCheck.Manage.rentalManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.ItemCheck.LoginActivity;
import com.example.ItemCheck.Manage.itemManage.LendcorrectActivity;
import com.example.ItemCheck.Manage.userManage.UserManageActivity;
import com.example.ItemCheck.R;

public class ManageActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4;
    TextView textView;
    boolean i = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        textView = findViewById(R.id.textView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, LendManageActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, UserManageActivity.class);
                startActivity(intent); // 화면 전환
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, LendcorrectActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (i == true){
                    textView.setText("○");
                    i = false;

                    Intent intent = new Intent(ManageActivity.this , LoginActivity.class);
                    intent.putExtra("str", textView.toString());


                }else {
                    textView.setText("X");
                    i = true;

                    Intent intent = new Intent(ManageActivity.this , LoginActivity.class);
                    intent.putExtra("str", textView.toString());
                }
            }
        });
    }
}