package com.example.ItemCheck;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ItemCheck.Dto.User.UserLoginRequestDto;
import com.example.ItemCheck.Dto.User.UserLoginResponseDto;
import com.example.ItemCheck.Manage.rentalManage.ManageActivity;
import com.example.ItemCheck.retrofit.RetrofitAPI;
import com.example.ItemCheck.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private Button btn_login, btn_manage;
    private EditText et_id, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        et_id = findViewById(R.id.et_id);
        et_pass = findViewById(R.id.et_pass);
        btn_manage = findViewById(R.id.btn_manage);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String studentId = et_id.getText().toString();
                String password = et_pass.getText().toString();

                UserLoginRequestDto userloginRequestDto = new UserLoginRequestDto(studentId, password);

                retrofitAPI.login(userloginRequestDto).enqueue(new Callback<UserLoginResponseDto>() {
                    // 통신 성공
                    @Override
                    public void onResponse(Call<UserLoginResponseDto> call, Response<UserLoginResponseDto> response) {
                        // HTTP 상태코드 저장
                        Integer resultCode = response.code();

                        UserLoginResponseDto userLoginResponseDto = response.body();

                        // response 200~300대
                        if(response.isSuccessful()) {
                            if(response.body().getUserData().getUser().getUserRole().equals("ADMIN")) {
                                Toast.makeText(MainActivity.this, "관리자 접속 성공", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, ManageActivity.class);
                                startActivity(intent);
                                MainActivity.this.finish();
                            } else {
                                Toast.makeText(MainActivity.this, "로그인되었습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                MainActivity.this.finish();
                            }
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(MainActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }
                    // 통신 실패
                    @Override
                    public void onFailure(Call<UserLoginResponseDto> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "로그인 통신 실패!");
                    }
                });

            }
        });

        /*
        btn_manage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ManageActivity.class);
                startActivity(intent); // 화면 전환
            }
        });
         */
    }
}