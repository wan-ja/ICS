package com.example.ItemCheck.Dto.Item;

import com.google.gson.annotations.SerializedName;

public class ItemSuccessResponseDto {
    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private String code;

    @SerializedName("result")
    private ItemData itemData;

    public ItemSuccessResponseDto(String success, String code, ItemData itemData) {
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
        private String message;

        public ItemData(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
