package com.example.ItemCheck.Dto.Item;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ItemResponseDto {
    @SerializedName("success")
    private String success;

    @SerializedName("code")
    private String code;

    @SerializedName("result")
    private ItemData itemData;

    public ItemResponseDto(String success, String code, ItemData itemData) {
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
            @SerializedName("itemName")
            private String itemName;

            @SerializedName("itemDetailName")
            private String itemDetailName;

            @SerializedName("isRental")
            private String isRental;

            public Item(String itemName, String itemDetailName, String isRental) {
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

            public String getIsRental() {
                return isRental;
            }

            public void setIsRental(String isRental) {
                this.isRental = isRental;
            }
        }
    }
}
