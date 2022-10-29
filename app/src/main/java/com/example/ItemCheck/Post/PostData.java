package com.example.ItemCheck.Post;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

public class PostData {

    @SerializedName("writerId")
    String writerId;

    @SerializedName("title")
    String title;

    @SerializedName("content")
    String content;

    @SerializedName("postedTime")
    String date;

    public String getWriterId() {
        return writerId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public void setWriterId(String writerId) {
        this.writerId = writerId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
