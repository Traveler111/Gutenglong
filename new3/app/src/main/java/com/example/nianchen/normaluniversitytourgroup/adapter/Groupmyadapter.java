package com.example.nianchen.normaluniversitytourgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.Group;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;

/**
 * Created by zhangzhixin on 2016/12/11.
 */

public class Groupmyadapter extends BaseAdapter {
    private Context c;
    private ArrayList<Group>groups;
    private ImageView img;
    private TextView name;
    private TextView desc;
    private int iplace;
    private ImageView imgbtn;

    public Groupmyadapter(Context c, ArrayList<Group> groups) {
        this.c = c;
        this.groups = groups;
    }

    @Override
    public int getCount() {
        return groups.size();
    }

    @Override
    public Object getItem(int position) {
        return groups.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        iplace=position;
        if(convertView==null){
            convertView= LayoutInflater.from(c).inflate(R.layout.groupitem,null);
        }
        img=(ImageView)convertView.findViewById(R.id.img);
        img.setImageResource(groups.get(position).getImg());
        imgbtn=(ImageView)convertView.findViewById(R.id.delete);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletegroup();
            }
        });
        name=(TextView)convertView.findViewById(R.id.groupname);
        name.setText(groups.get(position).getGroupname().toString().trim());
        desc=(TextView)convertView.findViewById(R.id.groupdesc);
        desc.setText(groups.get(position).getDesc().toString().trim());
        return convertView;

    }
    public void deletegroup(){
        try {
            EMClient.getInstance().groupManager().leaveGroup(groups.get(iplace).getDesc().toString().trim());
            groups.remove(iplace);
            notifyDataSetChanged();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }
    }
}