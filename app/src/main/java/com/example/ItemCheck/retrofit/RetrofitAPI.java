package com.example.ItemCheck.retrofit;

import com.example.ItemCheck.Dto.Item.ItemSuccessResponseDto;
import com.example.ItemCheck.Dto.Item.ItemRequestDto;
import com.example.ItemCheck.Dto.Item.ItemResponseDto;
import com.example.ItemCheck.Dto.User.UserAdminRequestDto;
import com.example.ItemCheck.Dto.User.UserAdminResponseDto;
import com.example.ItemCheck.Dto.User.UserLoginRequestDto;
import com.example.ItemCheck.Dto.User.UserLoginResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetrofitAPI {
    /*
    [[ USER ]]
     */
    @POST("/users/login")
    Call<UserLoginResponseDto> login(@Body UserLoginRequestDto userloginRequestDto);

    @POST("/admin/users/signup")
    Call<UserAdminResponseDto> register(@Body UserAdminRequestDto userAdminRequestDto);

    @GET("/admin/users")
    Call<UserAdminResponseDto> getUsers();

    @DELETE("/admin/users/delete")
    Call<String> deleteUser(@Query("name") String name);

    /*
    [[ ITEM ]]
     */
    @GET("/admin/items/itemNames")
    Call<ItemResponseDto> getItemNames();

    @GET("/admin/items/itemDetailName")
    Call<ItemResponseDto> getItemDetails(@Query("itemName") String itemName);

    @GET("/admin/items")
    Call<ItemResponseDto> getItems();

    @POST("/admin/items")
    Call<ItemSuccessResponseDto> createItem(@Body ItemRequestDto itemRequestDto);

    @DELETE("/admin/items/delete")
    Call<ItemSuccessResponseDto> deleteItem(@Query("itemDetailName") String itemDetailName);
}