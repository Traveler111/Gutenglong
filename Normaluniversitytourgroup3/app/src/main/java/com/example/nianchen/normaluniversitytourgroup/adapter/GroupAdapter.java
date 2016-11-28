package com.example.nianchen.normaluniversitytourgroup.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendTwo;
import com.example.nianchen.normaluniversitytourgroup.R;

import java.util.List;

/**
 * Created by nianchen on 2016/11/24.
 */
public class GroupAdapter extends BaseAdapter {
    private List <FriendTwo> friends;
    private Context c;
    private ImageView img;
    private TextView name;
    private TextView desc;
    private ImageView imgs;

    public GroupAdapter(Context c, List<FriendTwo> friends) {
        this.c = c;
        this.friends = friends;
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
        if(convertView==null ){
            convertView= LayoutInflater.from(c).inflate(R.layout.array_item_two,null);//jiazaibujv
        }
        img=(ImageView) convertView.findViewById(R.id.left);
        img.setImageResource(friends.get(position).getLeft());//fuzhi
        name=(TextView) convertView.findViewById(R.id.top);
        name.setText(friends.get(position).getTop().toString());
        desc=(TextView)convertView.findViewById(R.id.bottom);
        desc.setText(friends.get(position).getBottom().toString());
        imgs=(ImageView) convertView.findViewById(R.id.right);
        imgs.setImageResource(friends.get(position).getRight());
        return convertView;
    }
}
