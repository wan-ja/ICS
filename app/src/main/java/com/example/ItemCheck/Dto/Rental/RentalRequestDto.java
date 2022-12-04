package com.example.ItemCheck.Dto.Rental;

import com.google.gson.annotations.SerializedName;

public class RentalRequestDto {
    @SerializedName("content")
    private String content;

    @SerializedName("itemDetailName")
    private String itemDetailName;

    @SerializedName("studentId")
    private String studentId;

    public RentalRequestDto(String content, String itemDetailName, String studentId) {
        this.content = content;
        this.itemDetailName = itemDetailName;
        this.studentId = studentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getItemDetailName() {
        return itemDetailName;
    }

    public void setItemDetailName(String itemDetailName) {
        this.itemDetailName = itemDetailName;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
}
