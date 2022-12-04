package com.example.ItemCheck.Dto.Rental;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class RentalResponseDto {
    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private String code;

    @SerializedName("result")
    private ItemData itemData;

    public RentalResponseDto(String success, String code, ItemData itemData) {
        this.success = success;
        this.code = code;
        this.itemData = itemData;
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

    public ItemData getItemData() {
        return itemData;
    }

    public void setItemData(ItemData itemData) {
        this.itemData = itemData;
    }

    public class ItemData {
        @SerializedName("data")
        private ArrayList<Item> items;

        public ItemData(ArrayList<Item> items) {
            this.items = items;
        }

        public ArrayList<Item> getItems() {
            return items;
        }

        public void setItems(ArrayList<Item> items) {
            this.items = items;
        }

        public class Item {
            @SerializedName("studentId")
            private String studentId;

            @SerializedName("itemDetailName")
            private String itemDetailName;

            @SerializedName("content")
            private String content;

            @SerializedName("date")
            private String Date;

            public Item(String studentId, String itemDetailName, String content, String date) {
                this.studentId = studentId;
                this.itemDetailName = itemDetailName;
                this.content = content;
                Date = date;
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
                return Date;
            }

            public void setDate(String date) {
                Date = date;
            }
        }
    }
}