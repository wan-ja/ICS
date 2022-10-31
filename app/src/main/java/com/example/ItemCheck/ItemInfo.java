package com.example.ItemCheck;

public class ItemInfo {
    /* 아이템의 정보를 담기 위한 클래스 */

    String num;
    String name;
    int resId;

    public ItemInfo(String name, int resId) {
        this.name = name;
        this.resId = resId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getResId() {
        return resId;
    }
    public void setResId(int resId) {
        this.resId = resId;
    }
}
