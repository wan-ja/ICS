package com.example.ItemCheck.Manage.itemManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.Item.ItemResponseDto;
import com.example.ItemCheck.Dto.SuccessResponseDto;
import com.example.ItemCheck.R;
import com.example.ItemCheck.retrofit.RetrofitAPI;
import com.example.ItemCheck.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubList1Activity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    Button btnAdd;
    Button btnDel;
    Button btnMove;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_list1);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        Intent intent = getIntent();
        String str = intent.getStringExtra("str"); //문자열 받기

        retrofitAPI.getItemDetails(str).enqueue(new Callback<ItemResponseDto>() {
            @Override
            public void onResponse(Call<ItemResponseDto> call, Response<ItemResponseDto> response) {
                for(ItemResponseDto.ItemData.Item i : response.body().getItemData().getItems()) {
                    System.out.println("i.getItemDetailName() = " + i.getItemDetailName());
                    list.add(i.getItemDetailName());
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ItemResponseDto> call, Throwable t) {
                Toast.makeText(SubList1Activity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });
        listView = (ListView) findViewById(R.id.listView1);
        //btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDel = (Button) findViewById(R.id.btnDel);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = list.get(position);
                Toast.makeText(SubList1Activity.this, "선택항목:" +item, Toast.LENGTH_SHORT).show();
            }
        });

        final EditText edt = (EditText) findViewById(R.id.edt1);

        /*
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = edt.getText().toString();
                list.add(str);
                adapter.notifyDataSetChanged();
                edt.setText("");
            }
        });

         */

        /*
        [[ 물품 삭제 버튼 클릭 ]]
         */

        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = listView.getCheckedItemPosition();

                retrofitAPI.deleteItem(list.get(pos)).enqueue(new Callback<SuccessResponseDto>() {
                    @Override
                    public void onResponse(Call<SuccessResponseDto> call, Response<SuccessResponseDto> response) {
                        if(response.isSuccessful()) {
                            list.remove(pos);
                            Toast.makeText(SubList1Activity.this, response.body().getItemData().getMessage(), Toast.LENGTH_SHORT).show();
                            adapter.notifyDataSetChanged();
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(SubList1Activity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(SubList1Activity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResponseDto> call, Throwable t) {
                        Toast.makeText(SubList1Activity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });

                listView.clearChoices();
            }
        });

    }
}