package com.example.ItemCheck.Manage.rentalManage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.Union.UnionResponseDto;
import com.example.ItemCheck.LoginActivity;
import com.example.ItemCheck.Manage.itemManage.LendcorrectActivity;
import com.example.ItemCheck.Manage.itemManage.SubList1Activity;
import com.example.ItemCheck.Manage.userManage.UserManageActivity;
import com.example.ItemCheck.R;
import com.example.ItemCheck.retrofit.RetrofitAPI;
import com.example.ItemCheck.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageActivity extends AppCompatActivity {

    private Button btn1, btn2, btn3, btn4;
    TextView textView;
    boolean i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        textView = findViewById(R.id.textView);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, LendManageActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, UserManageActivity.class);
                startActivity(intent); // 화면 전환
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManageActivity.this, LendcorrectActivity.class);
                startActivity(intent); // 화면 전환
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                retrofitAPI.changeOpen().enqueue(new Callback<UnionResponseDto>() {
                    @Override
                    public void onResponse(Call<UnionResponseDto> call, Response<UnionResponseDto> response) {
                        if(response.isSuccessful()) {
                            i = response.body().getResult().getUnionData().getIsOpen();
                            if (i == true)
                                textView.setText("○");
                            else
                                textView.setText("X");
                            Toast.makeText(ManageActivity.this, "성공적으로 변경되었습니다." + i, Toast.LENGTH_SHORT).show();
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(ManageActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(ManageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
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
        });
    }
}