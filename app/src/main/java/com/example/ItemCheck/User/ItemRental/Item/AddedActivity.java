package com.example.ItemCheck.User.ItemRental.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ItemCheck.Manage.userManage.ListItem;
import com.example.ItemCheck.Manage.userManage.ListItemAdapter;
import com.example.ItemCheck.R;

public class AddedActivity extends AppCompatActivity {

    ListView listView;
    ListItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added);

        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);

        listItemAdapter.addItem(new ListItem("책상", "책상1", "대여중"));
        listView.setAdapter(listItemAdapter);

    }
}