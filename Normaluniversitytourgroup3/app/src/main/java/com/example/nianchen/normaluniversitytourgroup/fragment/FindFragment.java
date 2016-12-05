package com.example.nianchen.normaluniversitytourgroup.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendOne;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.FindFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianchen on 2016/11/22.
 */
public class FindFragment extends Fragment {
    private List<FriendOne> friends=new ArrayList<FriendOne>();
    private View view;
    private ListView lv;
    private FindFragmentAdapter myadapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_find, container, false);
//        ListView list = (ListView) view.findViewById(R.id.findlist);
//        List<Map<String,Object>> listItems1 = new ArrayList<Map<String,Object>>();
//        for (int i=0;i<name1.length;i++){
//            HashMap<String, Object> listItem1=new HashMap<String, Object>();
//            listItem1.put("header1",imagesId1[i]);
//            listItem1.put("name1",name1[i]);
//            listItem1.put("desc1",desc1[i]);
//            listItems1.add(listItem1);
//        }
//        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(),listItems1,
//                R.layout.array_item_one,
//                new String[]{"name1","hearder1","desc1"},
//                new int[]{R.id.name1 ,R.id.hearder1, R.id.desc1});
//        list.setAdapter(simpleAdapter);
        getdata();
        findview();
        myadapter=new FindFragmentAdapter(getActivity(),friends);
        lv.setAdapter(myadapter);
        return view;
    }
    public void findview(){
        lv=(ListView)view.findViewById(R.id.findlist);
        Log.e("find","run");
    }
    public void getdata(){
        friends.add(new FriendOne(R.drawable.lei,"雷达加朋友","添加身边的朋友"));
        friends.add(new FriendOne(R.drawable.jia,"面对面加群","与身边的朋友进入同一个群聊"));
        friends.add(new FriendOne(R.drawable.sao,"扫一扫","扫描二维码名片"));
        friends.add(new FriendOne(R.drawable.shou,"手机联系人","邀请通讯录中的好友"));
        friends.add(new FriendOne(R.drawable.gong,"公众号","获取更多资源和服务"));
        Log.e("getdata","run");
    }
}
