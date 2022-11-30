package com.example.ItemCheck.retrofit;

import com.example.ItemCheck.Dto.User.UserAdminRequestDto;
import com.example.ItemCheck.Dto.User.UserAdminResponseDto;
import com.example.ItemCheck.Dto.User.UserLoginRequestDto;
import com.example.ItemCheck.Dto.User.UserLoginResponseDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @POST("/api/users/signup")
    Call<UserLoginResponseDto> saveMember(@Body UserLoginResponseDto userLoginResponseDto);

    @POST("/users/login")
    Call<UserLoginResponseDto> login(@Body UserLoginRequestDto userloginRequestDto);

    @POST("/admin/users/signup")
    Call<UserAdminResponseDto> register(@Body UserAdminRequestDto userAdminRequestDto);

    @GET("/admin/users")
    Call<UserAdminResponseDto> getUsers();

    @DELETE("/admin/users/delete")
    Call<String> deleteUser(@Query("name") String name);
}