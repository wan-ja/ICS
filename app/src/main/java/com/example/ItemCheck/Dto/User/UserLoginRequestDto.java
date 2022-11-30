package com.example.ItemCheck.Dto.User;

import com.google.gson.annotations.SerializedName;

public class UserLoginRequestDto {
    @SerializedName("studentId")
    private String studentId;

    @SerializedName("password")
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserLoginRequestDto(String studentId, String password) {
        this.studentId = studentId;
        this.password = password;
    }
}
