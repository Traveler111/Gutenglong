package com.example.nianchen.normaluniversitytourgroup.BaseClass;

/**
 * Created by zhangzhixin on 2016/12/22.
 */

public class Myuser {
    private String username;
    private String nick;
    private String img;
    private String gg;

    public Myuser(String username, String nick, String img, String gg) {
        this.username = username;
        this.nick = nick;
        this.img = img;
        this.gg = gg;
    }

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGg() {
        return gg;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }
}
