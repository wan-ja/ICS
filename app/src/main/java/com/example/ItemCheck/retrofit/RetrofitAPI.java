package com.example.ItemCheck.retrofit;

import android.widget.TextView;

import com.example.ItemCheck.Dto.Member.MemberResponseDto;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface RetrofitAPI {
    static Call<MemberResponseDto> login(TextView et_id, TextView et_pass) {
        return null;
    }

    @POST("/api/members/")
    Call<MemberResponseDto> saveMember(@Body MemberResponseDto memberResponseDto);

    @POST("/api/members/{id}/login")
    static Call<MemberResponseDto> login(@Body MemberResponseDto memberResponseDto) {
        return null;
    }
}
