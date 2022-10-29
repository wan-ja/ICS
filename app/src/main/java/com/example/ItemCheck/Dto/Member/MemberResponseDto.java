package com.example.ItemCheck.Dto.Member;

import com.google.gson.annotations.SerializedName;

public class MemberResponseDto {
    @SerializedName("studentId")
    private Long studentId;

    @SerializedName("password")
    private String password;

    public MemberResponseDto(Long studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }

    @Override
    public String toString() {
        return "MemberResponseDto{" +
                "studentId=" + studentId +
                ", password='" + password + '\'' +
        '}';
    }
}
