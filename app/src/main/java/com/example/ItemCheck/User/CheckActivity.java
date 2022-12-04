package com.example.ItemCheck.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.ItemCheck.Manage.userManage.ListItem;
import com.example.ItemCheck.Manage.userManage.ListItemAdapter;
import com.example.ItemCheck.R;

public class CheckActivity extends AppCompatActivity {

    ListView listView;
    ListItemAdapter listItemAdapter;
    private SwipeRefreshLayout mysrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        mysrl = findViewById(R.id.content_srl);

        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);

        listItemAdapter.addItem(new ListItem("책상", "191919", "반납완뇨"));
        listView.setAdapter(listItemAdapter);


        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 새로고침시 동작
                listItemAdapter.notifyDataSetChanged();

                // 종료
                mysrl.setRefreshing(false);
            }
        });
    }
}