package com.example.ItemCheck.Dto.Item;

import com.google.gson.annotations.SerializedName;

public class ItemRequestDto {

    @SerializedName("itemName")
    private String itemName;

    @SerializedName("itemDetailName")
    private String itemDetailName;

    @SerializedName("isRental")
    private Used isRental;

    public ItemRequestDto(String itemName, String itemDetailName, Used isRental) {
        this.itemName = itemName;
        this.itemDetailName = itemDetailName;
        this.isRental = isRental;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDetailName() {
        return itemDetailName;
    }

    public void setItemDetailName(String itemDetailName) {
        this.itemDetailName = itemDetailName;
    }

    public Used getIsRental() {
        return isRental;
    }

    public void setIsRental(Used isRental) {
        this.isRental = isRental;
    }

}
