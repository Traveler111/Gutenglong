package com.example.nianchen.normaluniversitytourgroup.page_activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ListView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendTwo;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.GroupAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianchen on 2016/11/22.
 */
public class GroupActivity extends Activity {
    private List<FriendTwo> friends = new ArrayList<FriendTwo>();
    private ListView list;
    private GroupAdapter myadpter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiu_group);
        getdata();
        getid();
        myadpter = new GroupAdapter(this,friends);
        list.setAdapter(myadpter);
    }

    private void getid() {
        list = (ListView)findViewById(R.id.group_list);
    }

    private void getdata() {
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
    }
}
