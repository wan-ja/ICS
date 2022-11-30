package com.example.ItemCheck.Dto.User;

import com.google.gson.annotations.SerializedName;

public class UserAdminRequestDto {
    @SerializedName("studentId")
    private String studentId;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserAdminRequestDto(String studentId, String name, String password) {
        this.studentId = studentId;
        this.name = name;
        this.password = password;
    }
}
