package com.example.ItemCheck.User.ItemRental.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.example.ItemCheck.Manage.userManage.ListItem;
import com.example.ItemCheck.Manage.userManage.ListItemAdapter;
import com.example.ItemCheck.R;

public class CountActivity extends AppCompatActivity {

    TextView textView;
    ListView listView;
    ListItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_count);

        textView = findViewById(R.id.textView);
        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);

        listItemAdapter.addItem(new ListItem("책상", "책상1", "대여중"));
        listView.setAdapter(listItemAdapter);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");

        textView.setText(str);

    }
}