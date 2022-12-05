package com.example.ItemCheck.User.ItemRental.Item;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.Item.ItemResponseDto;
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

public class AddedActivity extends AppCompatActivity {

    ListView listView;
    ListItemAdapter listItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_added);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);

        /*
        Intent intent = getIntent();
        String str = intent.getStringExtra("str");
        */
        retrofitAPI.getItems().enqueue(new Callback<ItemResponseDto>() {
            @Override
            public void onResponse(Call<ItemResponseDto> call, Response<ItemResponseDto> response) {
                if(response.isSuccessful()) {
                    String rental = "";
                    for(ItemResponseDto.ItemData.Item i : response.body().getItemData().getItems()) {
                        if (i.getIsRental().equals("CAN"))
                            rental = "대여 가능";
                        else
                            rental = "대여 불가";
                        listItemAdapter.addItem(new ListItem(i.getItemName(), i.getItemDetailName(), rental));
                    }
                    listView.setAdapter(listItemAdapter);
                    Toast.makeText(AddedActivity.this, "성공적으로 불러왔습니다.", Toast.LENGTH_SHORT).show();
                }
                // response 400~500 (에러 발생)
                else {
                    try {
                        // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                        JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(AddedActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                    } catch(IOException e) {
                        Toast.makeText(AddedActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("e = " + e);
                    }
                }
            }

            @Override
            public void onFailure(Call<ItemResponseDto> call, Throwable t) {
                Toast.makeText(AddedActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

    }
}