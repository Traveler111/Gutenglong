package com.example.nianchen.normaluniversitytourgroup.page_activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendThree;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.FriendsAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianchen on 2016/11/23.
 */
public class FootActivity extends Activity{//友聊
    private List<FriendThree> friends = new ArrayList<FriendThree>();
    private ListView list;
    private FriendsAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiu_foot);
        getId();
        getData();
        myadapter = new FriendsAdapter(friends,FootActivity.this);
        list.setAdapter(myadapter);
    }
    private void getData() {
        friends.add(new FriendThree(R.drawable.a1,"李佳航","人是个神马东西",R.drawable.a11,"求男伴一名，师大徒步到经贸\n友聊号：232332332"));
        friends.add(new FriendThree(R.drawable.a1,"李佳航","人是个神马东西",R.drawable.a11,"求男伴一名，师大徒步到科大\n友聊号：232332332"));
        friends.add(new FriendThree(R.drawable.a1,"李佳航","人是个神马东西",R.drawable.a10,"今年12月12日晚7点我在帝威斯果醋冰淇淋酒吧等你，不见不散，美女免费，帅哥100元\n地址：石家庄市育才街301-3号\n报名电话：15254594188"));
    }

    private void getId() {
        list = (ListView) findViewById(R.id.foots);
    }
    public void onPause(){
        super.onPause();
        friends.clear();
    }
}
