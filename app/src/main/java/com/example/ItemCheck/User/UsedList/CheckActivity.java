package com.example.ItemCheck.User.UsedList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.Rental.RentalResponseDto;
import com.example.ItemCheck.Manage.itemManage.LendcorrectActivity;
import com.example.ItemCheck.Manage.userManage.ListItem;
import com.example.ItemCheck.Manage.userManage.ListItemAdapter;
import com.example.ItemCheck.R;
import com.example.ItemCheck.retrofit.RetrofitAPI;
import com.example.ItemCheck.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CheckActivity extends AppCompatActivity {

    ListView listView;
    private TextView tv_sub;
    ListItemAdapter listItemAdapter;
    private SwipeRefreshLayout mysrl;

    RetrofitClient retrofit = RetrofitClient.getInstance();
    RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        tv_sub = findViewById(R.id.tv_sub);

        Intent intent = getIntent();
        String str = intent.getStringExtra("str"); //문자열 받기

        tv_sub.setText(str);

        mysrl = findViewById(R.id.content_srl);

        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);

        retrofitAPI.searchRental(str).enqueue(new Callback<RentalResponseDto>() {
            @Override
            public void onResponse(Call<RentalResponseDto> call, Response<RentalResponseDto> response) {
                if(response.isSuccessful()) {
                    for(RentalResponseDto.RentalData.Rental r : response.body().getRentalData().getRentals()) {
                        ListItem listItem = new ListItem(r.getItemDetailName(), r.getDate(), r.getContent());
                        listItemAdapter.addItem(listItem);
                    }
                    listView.setAdapter(listItemAdapter);
                }
                // response 400~500 (에러 발생)
                else {
                    try {
                        // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                        JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(CheckActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                    } catch(IOException e) {
                        Toast.makeText(CheckActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("e = " + e);
                    }
                }
            }

            @Override
            public void onFailure(Call<RentalResponseDto> call, Throwable t) {
                Toast.makeText(CheckActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

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