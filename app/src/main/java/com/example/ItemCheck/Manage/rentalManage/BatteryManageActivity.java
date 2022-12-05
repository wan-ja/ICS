package com.example.ItemCheck.Manage.rentalManage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.Rental.RentalRequestDto;
import com.example.ItemCheck.Dto.SuccessResponseDto;
import com.example.ItemCheck.R;
import com.example.ItemCheck.retrofit.RetrofitAPI;
import com.example.ItemCheck.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BatteryManageActivity extends AppCompatActivity {

    Button btn_lend, btn_return;
    EditText edt_item, edt_num;
    ImageView grn;

    RetrofitClient retrofit = RetrofitClient.getInstance();
    RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery_manage);

        btn_lend = findViewById(R.id.btn_lend);
        btn_return = findViewById(R.id.btn_return);
        edt_item = findViewById(R.id.txt1);
        edt_num = findViewById(R.id.edt_num);
        grn = findViewById(R.id.grn);

        btn_lend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemDetailName = edt_item.getText().toString();
                String studentId = edt_num.getText().toString();
                RentalRequestDto rentalRequestDto = new RentalRequestDto("물품 대여", itemDetailName, studentId);

                retrofitAPI.createRental(rentalRequestDto).enqueue(new Callback<SuccessResponseDto>() {
                    @Override
                    public void onResponse(Call<SuccessResponseDto> call, Response<SuccessResponseDto> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(BatteryManageActivity.this, response.body().getItemData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(BatteryManageActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(BatteryManageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResponseDto> call, Throwable t) {
                        Toast.makeText(BatteryManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        btn_return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String itemDetailName = edt_item.getText().toString();
                String studentId = edt_num.getText().toString();
                RentalRequestDto rentalRequestDto = new RentalRequestDto("물품 반납", itemDetailName, studentId);

                retrofitAPI.returnRental(rentalRequestDto).enqueue(new Callback<SuccessResponseDto>() {
                    @Override
                    public void onResponse(Call<SuccessResponseDto> call, Response<SuccessResponseDto> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(BatteryManageActivity.this, response.body().getItemData().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(BatteryManageActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(BatteryManageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SuccessResponseDto> call, Throwable t) {
                        Toast.makeText(BatteryManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}