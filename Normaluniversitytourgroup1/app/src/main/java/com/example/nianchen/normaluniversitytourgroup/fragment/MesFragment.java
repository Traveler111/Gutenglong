package com.example.nianchen.normaluniversitytourgroup.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabWidget;

import com.example.nianchen.normaluniversitytourgroup.R;

/**
 * Created by nianchen on 2016/11/22.
 */
public class MesFragment extends Fragment{
    private View view;
    private LinearLayout chat;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        chat = (LinearLayout)view.findViewById(R.id.chat);
        Tabhosts();

        return view;
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
