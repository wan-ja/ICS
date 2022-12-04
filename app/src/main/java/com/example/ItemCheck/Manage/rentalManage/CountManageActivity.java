package com.example.ItemCheck.Manage.rentalManage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.ItemCheck.R;

public class CountManageActivity extends AppCompatActivity {

    Button btn_lend, btn_return;
    EditText edt_item, edt_num;
    ImageView grn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count_manage);

        btn_lend = findViewById(R.id.btn_lend);
        btn_return = findViewById(R.id.btn_return);
        edt_item = findViewById(R.id.edt_item);
        edt_num = findViewById(R.id.edt_num);
        grn = findViewById(R.id.grn);

        btn_lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}