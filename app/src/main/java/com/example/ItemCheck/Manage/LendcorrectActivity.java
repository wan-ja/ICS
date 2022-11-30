package com.example.ItemCheck.Manage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ItemCheck.R;

import java.util.ArrayList;

public class LendcorrectActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    Button btnAdd;
    Button btnDel;
    Button btnMove;
    TextView textView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendcorrect);

        list.add("일회용품");
        list.add("구급약품");
        list.add("보조배터리");
        list.add("공학용계산기");
        list.add("우산");

        listView = (ListView) findViewById(R.id.listView1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnMove = (Button) findViewById(R.id.btnMove);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = list.get(position);
                Toast.makeText(LendcorrectActivity.this, "선택항목:" +item, Toast.LENGTH_SHORT).show();
            }
        });

        final EditText edt1 = (EditText) findViewById(R.id.edt1);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edt1.getText().toString();
                list.add(str);
                adapter.notifyDataSetChanged();
                edt1.setText("");
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

        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = listView.getCheckedItemPosition();
                String str = edt1.getText().toString();
                System.out.println(list.get(pos));
                System.out.println(str);
                if(list.get(pos) == "일회용품"){
                    Intent intent = new Intent(LendcorrectActivity.this, RecycleListActivity.class);
                    startActivityForResult(intent, 100);
                }
                else if(list.get(pos) == "구급약품"){
                    Intent intent = new Intent(LendcorrectActivity.this, FirstAidListActivity.class);
                    startActivityForResult(intent, 200);
                }
                else if(list.get(pos) == "보조배터리"){
                    Intent intent = new Intent(LendcorrectActivity.this, BatteryListActivity.class);
                    startActivity(intent);
                }
                else if(list.get(pos) == "공학용계산기"){
                    Intent intent = new Intent(LendcorrectActivity.this, CountListActivity.class);
                    startActivity(intent);
                }
                else if(list.get(pos) == "우산"){
                    Intent intent = new Intent(LendcorrectActivity.this, UmbrellaListActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent(LendcorrectActivity.this, SubList1Activity.class);
                    startActivity(intent);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==100){
            if (resultCode==RESULT_OK) {
                Toast.makeText(LendcorrectActivity.this, "result ok!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LendcorrectActivity.this, "result cancel!", Toast.LENGTH_SHORT).show();
            }
        }
        else if(requestCode==200){
            if (resultCode==RESULT_OK) {
                Toast.makeText(LendcorrectActivity.this, "result ok!", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(LendcorrectActivity.this, "result cancel!", Toast.LENGTH_SHORT).show();
            }
        }
    }

}