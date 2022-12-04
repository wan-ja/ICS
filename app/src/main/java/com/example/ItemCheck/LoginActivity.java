package com.example.ItemCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.Union.UnionResponseDto;
import com.example.ItemCheck.Manage.rentalManage.ManageActivity;
import com.example.ItemCheck.User.UsedList.CheckActivity;
import com.example.ItemCheck.User.ItemRental.LendActivity;
import com.example.ItemCheck.retrofit.RetrofitAPI;
import com.example.ItemCheck.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private Button btn1, btn2;
    TextView textView;
    private boolean i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        textView = findViewById(R.id.textView);

        // 통신하기 전에는 아직 개폐 여부를 몰라야 됨
        textView.setText("?");

        Intent intent = getIntent();
        String str = intent.getStringExtra("str");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LendActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, CheckActivity.class);
                intent.putExtra("str", str);
                startActivity(intent); // 화면 전환
            }
        });

        retrofitAPI.getOpen().enqueue(new Callback<UnionResponseDto>() {
            @Override
            public void onResponse(Call<UnionResponseDto> call, Response<UnionResponseDto> response) {
                if(response.isSuccessful()) {
                    i = response.body().getResult().getUnionData().getIsOpen();
                    if (i == true)
                        textView.setText("○");
                    else
                        textView.setText("X");
                }
                // response 400~500 (에러 발생)
                else {
                    try {
                        // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                        JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(LoginActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                    } catch(IOException e) {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                        System.out.println("e = " + e);
                    }
                }
            }

            @Override
            public void onFailure(Call<UnionResponseDto> call, Throwable t) {

            }
        });

    }

}

