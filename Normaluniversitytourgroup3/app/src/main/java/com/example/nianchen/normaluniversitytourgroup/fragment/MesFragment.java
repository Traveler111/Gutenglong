package com.example.nianchen.normaluniversitytourgroup.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendOne;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.MesFragmentMesAdapter;
import com.example.nianchen.normaluniversitytourgroup.adapter.MesFragmentContactAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianchen on 2016/11/22.
 */
public class MesFragment extends Fragment{
    private List<FriendOne> friends1 = new ArrayList<FriendOne>();
    private List<FriendOne> friends2 = new ArrayList<FriendOne>();
    private View view;
    private LinearLayout chat;
    private ListView list11;
    private MesFragmentMesAdapter myadapter;
    private ListView list12;
    private MesFragmentContactAdapter myadapter1;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        chat = (LinearLayout)view.findViewById(R.id.chat);
        Tabhosts();
        getViews();
        getdata1();
        getdata2();
        myadapter = new MesFragmentMesAdapter(getActivity(),friends1);
        myadapter1 = new MesFragmentContactAdapter(getActivity(),friends2);
        list11.setAdapter(myadapter);
        list12.setAdapter(myadapter1);
        return view;
    }

    private void getdata2() {
        friends2.add(new FriendOne(R.drawable.a1,"李佳航","人是个神马东西"));
        friends2.add(new FriendOne(R.drawable.a2,"李佳航","人是个神马东西"));
        friends2.add(new FriendOne(R.drawable.a3,"李佳航","人是个神马东西"));
        friends2.add(new FriendOne(R.drawable.a4,"李佳航","人是个神马东西"));
        friends2.add(new FriendOne(R.drawable.a1,"李佳航","人是个神马东西"));
    }

    private void getViews() {
        list11=(ListView)view.findViewById(R.id.mes_list);
        list12=(ListView)view.findViewById(R.id.mes_list1);
    }

    private void getdata1() {
        friends1.add(new FriendOne(R.drawable.a2,"李佳航","人是个神马东西"));
        friends1.add(new FriendOne(R.drawable.a2,"李佳航","人是个神马东西"));
        friends1.add(new FriendOne(R.drawable.a2,"李佳航","人是个神马东西"));
        friends1.add(new FriendOne(R.drawable.a2,"李佳航","人是个神马东西"));
        friends1.add(new FriendOne(R.drawable.a2,"李佳航","人是个神马东西"));
    }

    private void Tabhosts() {
        TabHost tabHost = (TabHost)view.findViewById(R.id.tabhost);
        tabHost.setup();
        TabWidget tabWidget = tabHost.getTabWidget();
        TabHost.TabSpec tab001= tabHost.newTabSpec("tab001")
                .setIndicator("消息")
                .setContent(R.id.tab001);
        tabHost.addTab(tab001);
        TabHost.TabSpec tab002= tabHost.newTabSpec("tab002")
                .setIndicator("联系人")
                .setContent(R.id.tab002);
        tabHost.addTab(tab002);
        if (getId()==R.id.btn_mes){
            tabHost.addTab(tab001);
            tabHost.addTab(tab002);
        }else if (getId() == R.id.chat){
            tabHost.addTab(tab002);
            tabHost.addTab(tab001);
        }
    }
}
