package com.example.ItemCheck.Manage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ItemCheck.R;

import java.util.ArrayList;

public class UmbrellaListActivity extends AppCompatActivity {


    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    Button btnAdd;
    Button btnDel;
    Button btnMove;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_umbrella_list);

        list.add("1번 우산");
        list.add("2번 우산");
        list.add("3번 우산");
        list.add("4번 우산");
        list.add("5번 우산");

        listView = (ListView) findViewById(R.id.listView1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDel = (Button) findViewById(R.id.btnDel);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = list.get(position);
                Toast.makeText(UmbrellaListActivity.this, "선택항목:" +item, Toast.LENGTH_SHORT).show();
            }
        });

        final EditText edt = (EditText) findViewById(R.id.edt1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edt.getText().toString();
                list.add(str);
                adapter.notifyDataSetChanged();
                edt.setText("");
            }
        });

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = listView.getCheckedItemPosition();
                list.remove(pos);
                adapter.notifyDataSetChanged();
                listView.clearChoices();
            }
        });

    }
}