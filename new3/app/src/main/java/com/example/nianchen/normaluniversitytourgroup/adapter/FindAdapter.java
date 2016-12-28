package com.example.nianchen.normaluniversitytourgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendOne;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import java.util.List;

/**
 * Created by nianchen on 2016/11/24.
 */
public class FindAdapter extends BaseAdapter {
    private List <FriendOne> friends;
    private Context c;
    private ImageView img;
    private TextView name;
    private TextView desc;
    private ImageView delete;
    private int ipalce;

    public FindAdapter(List<FriendOne> friends, Context c) {
        this.friends = friends;
        this.c = c;
    }


    @Override
    public int getCount() {
        return friends.size();//获取长度
    }

    @Override
    public Object getItem(int position) {//获取一条
        return friends.get(position);
    }

    @Override
    public long getItemId(int position) {//获取一条的id
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ipalce=position;
        if(convertView==null ){
            convertView= LayoutInflater.from(c).inflate(R.layout.array_item_one,null);//jiazaibujv
        }
        img=(ImageView) convertView.findViewById(R.id.hearder1);
        img.setImageResource(friends.get(position).getImg());//fuzhi
        name=(TextView) convertView.findViewById(R.id.name1);
        name.setText(friends.get(position).getName().toString());
        desc=(TextView) convertView.findViewById(R.id.desc1);
//        delete=(ImageView)convertView.findViewById(R.id.delete);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                        deletefriend();
//                Toast.makeText(c,"onclick",Toast.LENGTH_LONG).show();
//            }
//        });
        return convertView;
    }
    public void deletefriend(){
        try {
            EMClient.getInstance().contactManager().deleteContact(friends.get(ipalce).getName().toString().trim());
            friends.remove(ipalce);
            notifyDataSetChanged();
        } catch (HyphenateException e) {
            e.printStackTrace();
        }

    }
}
