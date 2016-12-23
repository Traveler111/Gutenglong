package com.example.nianchen.normaluniversitytourgroup.BaseClass;

/**
 * Created by nianchen on 2016/11/24.
 */
public class FriendTwo {
    private String left;
    private String top;
    private String mid;
    private String bottom;
    private int right;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPush() {
        return push;
    }

    public void setPush(String push) {
        this.push = push;
    }

    private String push;


    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getLeft() {
        return left;
    }

    public void setLeft(String left) {
        this.left = left;
    }

    public String getTop() {
        return top;
    }

    public void setTop(String top) {
        this.top = top;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
        this.bottom = bottom;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public FriendTwo(String left, String top, String bottom, int right,String mid,String push,String id) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
        this.mid = mid;
        this.push = push;
        this.id=id;
    }
}
