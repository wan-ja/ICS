package com.example.ItemCheck.Manage.itemManage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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

import com.example.ItemCheck.Dto.SuccessResponseDto;
import com.example.ItemCheck.Dto.Item.ItemRequestDto;
import com.example.ItemCheck.Dto.Item.ItemResponseDto;
import com.example.ItemCheck.Dto.Item.Used;
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

public class LendcorrectActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> list = new ArrayList<String>();
    Button btnAdd;
    Button btnDel;
    Button btnMove;
    TextView textView;
    ArrayAdapter<String> adapter;
    private SwipeRefreshLayout mysrl;

    RetrofitClient retrofit = RetrofitClient.getInstance();
    RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lendcorrect);

        mysrl = findViewById(R.id.content_srl);


        retrofitAPI.getItemNames().enqueue(new Callback<ItemResponseDto>() {
            @Override
            public void onResponse(Call<ItemResponseDto> call, Response<ItemResponseDto> response) {
                for(ItemResponseDto.ItemData.Item i : response.body().getItemData().getItems()) {
                    String itemName = i.getItemName();
                    System.out.println("itemName = " + itemName);
                    list.add(itemName);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ItemResponseDto> call, Throwable t) {
                Toast.makeText(LendcorrectActivity.this, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
            }
        });
        listView = (ListView) findViewById(R.id.listView1);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnMove = (Button) findViewById(R.id.btnMove);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, list);

        listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                String item = list.get(position);
                Toast.makeText(LendcorrectActivity.this, "????????????:" +item, Toast.LENGTH_SHORT).show();
            }
        });

        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final EditText edt2 = (EditText) findViewById(R.id.edt2);

        /*
        [[ ?????? ?????? ?????? ?????? ]]
         */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemName = edt1.getText().toString();
                String itemDetailName = edt2.getText().toString();
                Used isRental = Used.CAN;
                ItemRequestDto itemRequestDto = new ItemRequestDto(itemName, itemDetailName, isRental);

                retrofitAPI.createItem(itemRequestDto).enqueue(new Callback<SuccessResponseDto>() {
                    @Override
                    public void onResponse(Call<SuccessResponseDto> call, Response<SuccessResponseDto> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(LendcorrectActivity.this, response.body().getItemData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // response 400~500 (?????? ??????)
                        else {
                            try {
                                // string ????????? ???????????? JSON?????? ?????? ??? JSON?????? ???????????? getString ??????
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(LendcorrectActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(LendcorrectActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResponseDto> call, Throwable t) {
                        Toast.makeText(LendcorrectActivity.this, "????????? ?????????????????????.", Toast.LENGTH_SHORT).show();
                    }
                });
                list.add(itemName);
                adapter.notifyDataSetChanged();
                edt1.setText("");
                edt2.setText("");
            }
        });
        /*
        [[ ?????? ?????? ????????? ?????? ]]
         */
        btnMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = listView.getCheckedItemPosition();
                System.out.println(list.get(pos));

                Intent intent = new Intent(LendcorrectActivity.this, SubList1Activity.class);
                intent.putExtra("str", list.get(pos)); // ?????? ????????? str ?????????
                startActivity(intent);
            }
        });

        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // ??????????????? ??????
                adapter.notifyDataSetChanged();

                // ??????
                mysrl.setRefreshing(false);
            }
        });
    }
}