package com.example.nianchen.normaluniversitytourgroup.BaseClass;

import android.graphics.Bitmap;

/**
 * Created by jiahang Lee on 2016/12/25.
 */

public class Friendlistadapter  {
    private String username;
    private Bitmap img;

    public Friendlistadapter(String username, Bitmap img) {
        this.username = username;
        this.img = img;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Bitmap getImg() {
        return img;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }
}
