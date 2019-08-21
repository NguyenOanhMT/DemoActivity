package com.nguyenoanh.demoactivity.Model;

import android.graphics.drawable.Drawable;

public class ItemNew {
    int id ;
    String username;
    String time;
    String content;
    String price;

    int profileImage;
    Drawable image;

    public ItemNew(int id, String username, String time, String content, String price, int profileImage, Drawable image) {
        this.id = id;
        this.username = username;
        this.time = time;
        this.content = content;
        this.price = price;
        this.profileImage = profileImage;
        this.image = image;
    }

    public ItemNew() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(int profileImage) {
        this.profileImage = profileImage;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
