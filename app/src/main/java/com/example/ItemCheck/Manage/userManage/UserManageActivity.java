package com.example.ItemCheck.Manage.userManage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ItemCheck.Dto.User.UserAdminRequestDto;
import com.example.ItemCheck.Dto.User.UserAdminResponseDto;
import com.example.ItemCheck.MainActivity;
import com.example.ItemCheck.R;
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

public class UserManageActivity extends AppCompatActivity {

    ListView listView;
    Button btnAdd;
    ListItemAdapter listItemAdapter;
    private SwipeRefreshLayout mysrl;
    EditText edt1, edt2, edt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);

        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        listView.setAdapter(listItemAdapter);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final EditText edt2 = (EditText) findViewById(R.id.edt2);
        final EditText edt3 = (EditText) findViewById(R.id.edt3);

        mysrl.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 새로고침시 동작
                listItemAdapter.notifyDataSetChanged();

                // 종료
                mysrl.setRefreshing(false);
            }
        });

        /*
        [[ user 받아오기 및 화면 출력 ]]
         */
        retrofitAPI.getUsers().enqueue(new Callback<UserAdminResponseDto>() {
            @Override
            public void onResponse(Call<UserAdminResponseDto> call, Response<UserAdminResponseDto> response) {
                response.body().getUserData().getUser();
                for (UserAdminResponseDto.UserData.User u : response.body().getUserData().getUser()) {
                    String studentId = u.getStudentId();
                    String name = u.getName();
                    String password = u.getPassword();
                    listItemAdapter.addItem(new ListItem(studentId, name, password));
                    listItemAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<UserAdminResponseDto> call, Throwable t) {
                //Toast.makeText(UserManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        /*
        [[ user 추가하기 버튼 클릭 ]]
         */
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String str1 = edt1.getText().toString();
                String str2 = edt2.getText().toString();
                String str3 = edt3.getText().toString();

                UserAdminRequestDto userAdminRequestDto = new UserAdminRequestDto(str1, str2, str3);

                retrofitAPI.register(userAdminRequestDto).enqueue(new Callback<UserAdminResponseDto>() {
                    @Override
                    public void onResponse(Call<UserAdminResponseDto> call, Response<UserAdminResponseDto> response) {
                        // response 200~300대
                        if (response.isSuccessful()) {
                            //String s = response.body().getUserData().getUser().get(1).getName();
                            Toast.makeText(UserManageActivity.this, " 회원님이 성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(UserManageActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch (IOException e) {
                                Toast.makeText(UserManageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }
                    // 통신 실패
                    @Override
                    public void onFailure(Call<UserAdminResponseDto> call, Throwable t) {
                        System.out.println("t = " + t);
                        Toast.makeText(UserManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        Logger.getLogger(MainActivity.class.getName()).log(Level.SEVERE, "로그인 통신 실패!");
                    }
                });
                // Apapter 추가
                listItemAdapter.addItem(new ListItem(str1, str2, str3));
                listItemAdapter.notifyDataSetChanged();
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
            }

        });

        /*
        [[ user 삭제 버튼 클릭 ]] 길게 눌러야 됨
         */
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                ListItem listItem = listItemAdapter.items.get(position);
                String name = listItem.getName();
                retrofitAPI.deleteUser(name).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = response.body();
                        System.out.println("s = " + s);
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        System.out.println(t.getMessage());
                        Toast.makeText(UserManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                listItemAdapter.items.remove(position);
                listItemAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
    /*@Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(this,"onPause 호출됨", Toast.LENGTH_LONG).show();
        SharedPreferences pref = getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("name", edt1.getText().toString());
        editor.putString("name", edt2.getText().toString());
        editor.putString("name", edt3.getText().toString());
        editor.commit();
    }*/
/*
    @Override
    protected void onResume(){
        super.onResume();

        listItemAdapter = new ListItemAdapter();
        listView = (ListView) findViewById(R.id.listView1);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        listView.setAdapter(listItemAdapter);

        RetrofitClient retrofit = RetrofitClient.getInstance();
        RetrofitAPI retrofitAPI = RetrofitClient.getRetrofitAPI();

        final EditText edt1 = (EditText) findViewById(R.id.edt1);
        final EditText edt2 = (EditText) findViewById(R.id.edt2);
        final EditText edt3 = (EditText) findViewById(R.id.edt3);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {

                String str1 = edt1.getText().toString();
                String str2 = edt2.getText().toString();
                String str3 = edt3.getText().toString();

                UserAdminRequestDto userAdminRequestDto = new UserAdminRequestDto(str1, str2, str3);

                retrofitAPI.register(userAdminRequestDto).enqueue(new Callback<UserAdminResponseDto>() {
                    @Override
                    public void onResponse(Call<UserAdminResponseDto> call, Response<UserAdminResponseDto> response) {

                        // response 200~300대
                        if(response.isSuccessful()) {
                            String s = response.body().getUserData().getUser().get(1).getName();
                            Toast.makeText(UserManageActivity.this, s + " 회원님이 성공적으로 등록되었습니다.", Toast.LENGTH_SHORT).show();
                        }
                        // response 400~500 (에러 발생)
                        else {
                            try {
                                // string 자체는 객체라서 JSON으로 변환 후 JSON에서 제공하는 getString 이용
                                JSONObject jsonObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(UserManageActivity.this, jsonObjError.getJSONObject("result").getString("msg"), Toast.LENGTH_LONG).show();
                            } catch(IOException e) {
                                Toast.makeText(UserManageActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                                System.out.println("e = " + e);
                            }
                        }
                    }
                    // 통신 실패
                    @Override
                    public void onFailure(Call<UserAdminResponseDto> call, Throwable t) {
                        Toast.makeText(UserManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                // Apapter 추가
                listItemAdapter.addItem(new ListItem(str1,str2,str3));
                listItemAdapter.notifyDataSetChanged();
                edt1.setText("");
                edt2.setText("");
                edt3.setText("");
            }

        });


        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                ListItem listItem = listItemAdapter.items.get(position);
                String name = listItem.getName();
                retrofitAPI.deleteUser(name).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String s = response.body();
                        Toast.makeText(UserManageActivity.this, s, Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(UserManageActivity.this, "통신에 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                });
                listItemAdapter.items.remove(position);
                listItemAdapter.notifyDataSetChanged();
                return false;
            }
        });
    }
}
*/