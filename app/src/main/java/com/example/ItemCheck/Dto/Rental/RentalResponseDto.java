package com.example.ItemCheck.Dto.Rental;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RentalResponseDto {
    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private String code;

    @SerializedName("result")
    private RentalData rentalData;

    public RentalResponseDto(String success, String code, RentalData rentalData) {
        this.success = success;
        this.code = code;
        this.rentalData = rentalData;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public RentalData getRentalData() {
        return rentalData;
    }

    public void setItemData(RentalData itemData) {
        this.rentalData = itemData;
    }

    public class RentalData {
        @SerializedName("data")
        private ArrayList<Rental> rentals;

        public RentalData(ArrayList<Rental> rentals) {
            this.rentals = rentals;
        }

        public ArrayList<Rental> getRentals() {
            return rentals;
        }

        public void setRentals(ArrayList<Rental> rentals) {
            this.rentals = rentals;
        }

        public class Rental {
            @SerializedName("studentId")
            private String studentId;

            @SerializedName("itemDetailName")
            private String itemDetailName;

            @SerializedName("content")
            private String content;

            @SerializedName("date")
            private String date;

            public Rental(String studentId, String itemDetailName, String content, String date) {
                this.studentId = studentId;
                this.itemDetailName = itemDetailName;
                this.content = content;
                this.date = date;
            }

            public String getStudentId() {
                return studentId;
            }

            public void setStudentId(String studentId) {
                this.studentId = studentId;
            }

            public String getItemDetailName() {
                return itemDetailName;
            }

            public void setItemDetailName(String itemDetailName) {
                this.itemDetailName = itemDetailName;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }
        }
    }
}