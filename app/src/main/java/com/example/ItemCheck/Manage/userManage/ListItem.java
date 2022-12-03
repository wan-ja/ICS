package com.example.ItemCheck.Manage.userManage;

public class ListItem {
    private String num;
    private String name;
    private String state;

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    ListItem(String num, String name, String state){
        this.num = num;
        this.name = name;
        this.state = state;
    }
}
