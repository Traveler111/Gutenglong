package com.example.nianchen.normaluniversitytourgroup.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String str;
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

//        imgbtn=(ImageView)convertView.findViewById(R.id.delete);
//        imgbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                deletegroup();
//            }
//        });
        name=(TextView)convertView.findViewById(R.id.groupname);
        name.setText(groups.get(position).getGroupname().toString().trim());
        desc=(TextView)convertView.findViewById(R.id.groupdesc);
        desc.setText(groups.get(position).getDesc().toString().trim());
//        async_pic(groups.get(position).getGroupname().toString().trim());

        async_pic(groups.get(position).getGroupname().toString().trim(),convertView);

        return convertView;

    }
    public void async_pic(String abc, final View convertView){
        final Bitmap[] bitmap = new Bitmap[1];
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp = new RequestParams() ;
        rp.put("Title",abc);
        String parm = "http://jiahanglee.cn/blog/downFile3";
        client.get(parm, rp, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                BitmapFactory bitmapFactory = new BitmapFactory();
                //工厂对象的decodeByteArray把字节转换成Bitmap对象
                 bitmap[0] = bitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                img=(ImageView)convertView.findViewById(R.id.img);
                img.setImageBitmap(bitmap[0]);
            }

            @Override
            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

            }
        });

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
