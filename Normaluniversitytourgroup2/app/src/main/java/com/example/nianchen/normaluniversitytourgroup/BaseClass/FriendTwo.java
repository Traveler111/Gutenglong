package com.example.nianchen.normaluniversitytourgroup.BaseClass;

/**
 * Created by nianchen on 2016/11/24.
 */
public class FriendTwo {
    private int left;
    private String top;
    private String bottom;
    private int right;

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
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

    public FriendTwo(int left, String top, String bottom, int right) {
        this.left = left;
        this.top = top;
        this.bottom = bottom;
        this.right = right;
    }
}
