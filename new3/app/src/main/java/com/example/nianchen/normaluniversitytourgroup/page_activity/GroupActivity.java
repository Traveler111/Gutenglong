package com.example.nianchen.normaluniversitytourgroup.page_activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendTwo;
import com.example.nianchen.normaluniversitytourgroup.BaseClass.MyApp;
import com.example.nianchen.normaluniversitytourgroup.ChooseActivity;
import com.example.nianchen.normaluniversitytourgroup.MainActivityFabu;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.GroupAdapter;
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
import java.util.List;

/**
 * Created by nianchen on 2016/11/22.
 */
public class GroupActivity extends Activity {
    private List<FriendTwo> friends = new ArrayList<FriendTwo>();
    private ListView list;
    private GroupAdapter myadpter;
    private ImageView jia;
    private ImageView imagshow;
    private TextView tv1;
    private TextView tv2;
    String s1;
    public int scrollStates;
    private String fwqreslut="1";
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(johnreslut.equals("ok")){
               mDialog.dismiss();
               Toast.makeText(GroupActivity.this,"加入成功！",Toast.LENGTH_LONG).show();
            }
            else if(johnreslut.equals("false")){
                mDialog.dismiss();
                Toast.makeText(GroupActivity.this,"加入失败！",Toast.LENGTH_LONG).show();
            }
        }
    };
    private String johnreslut="1";
    private ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiu_group);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        final String url="http://123.207.228.232/blog/Xianshifabu";
        final AsyncHttpClient client=new AsyncHttpClient();
        client.get(getApplicationContext(),url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject json=response.getJSONObject(i);
                        String scontent=json.getString("Content");
                        String stitle=json.getString("Title");
                        String url = json.getString("url");
                        String gg = json.getString("gg");
                        String id=json.getString("id");
                        if(url==null)
                            url="a";
                        friends.add(new FriendTwo("http://123.207.228.232/blog/downFile",stitle,scontent,R.drawable.b1,url,gg,id));
                        myadpter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getid();
        setlistener();
        myadpter = new GroupAdapter(this,friends,list);


        list.setAdapter(myadpter);


//

        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                scrollStates = scrollState;
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                int lastInScreen = firstVisibleItem + visibleItemCount;
            }
        });
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                View v1=getLayoutInflater().inflate(R.layout.layout_item,null);
                tv1=(TextView)v1.findViewById(R.id.tv1);
                tv2=(TextView)v1.findViewById(R.id.tv2);
                imagshow = (ImageView)v1.findViewById(R.id.imageshow);
                tv1.setText(friends.get(position).getTop());
                tv2.setText(friends.get(position).getBottom());
                imagshow.setImageResource(R.drawable.logo);
                s1=friends.get(position).getTop();
                AlertDialog.Builder adb=new AlertDialog.Builder(GroupActivity.this);
                adb.setView(v1);
                adb.setTitle("队伍信息");
                adb.setPositiveButton("我要加入", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mDialog=new ProgressDialog(GroupActivity.this);
                        mDialog.setMessage("加入ing...");
                        mDialog.show();
                        SharedPreferences spf= MyApp.getAppContext().getSharedPreferences("User",MODE_PRIVATE);
                        String s=spf.getString("uname","");
                        System.out.println(s);
                        String url1="http://123.207.228.232/blog/Jiarucontent";
                        AsyncHttpClient client=new AsyncHttpClient();
                        RequestParams rp1=new RequestParams();
                        rp1.put("Title",s1);
                        rp1.put("Uname",s);
                        johngroup(friends.get(position).getId().toString().trim());
                        client.get(url1, rp1, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                 fwqreslut="ok";
                                 Message msg=new Message();
                                 myhandler.sendMessage(msg);
                            }

                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                 fwqreslut="false";
                                 Message msg=new Message();
                                myhandler.sendMessage(msg);
                            }
                        });
                    }
                });
                adb.setNegativeButton("取消",null);
                adb.create().show();
            }
        });
    }

    private void setlistener() {
        jia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(GroupActivity.this, MainActivityFabu.class);
                startActivity(i);
            }
        });
    }

    private void getid() {
        list = (ListView)findViewById(R.id.group_list);
        jia =(ImageView)findViewById(R.id.img_jia);
    }

    //    private void getdata() {
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//        friends.add(new FriendTwo(R.drawable.a1,"抱犊寨","详情......",R.drawable.b1));
//    }

    class OnScrollListenerImpl implements AbsListView.OnScrollListener {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            scrollStates = scrollState;
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem,
                             int visibleItemCount, int totalItemCount) {
            int lastInScreen = firstVisibleItem + visibleItemCount;
        }
    }


        public void onPause(){
        super.onPause();
        friends.clear();
            finish();
    }
    public void  johngroup(final String groupid){
        Thread th=new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    EMClient.getInstance().groupManager().joinGroup(groupid);
                    johnreslut="ok";
                    Message msg=new Message();
                    myhandler.sendMessage(msg);
                    Log.e("john","try");
                } catch (HyphenateException e) {
                    e.printStackTrace();
                    mDialog.dismiss();
                    Toast.makeText(GroupActivity.this,"已加入",Toast.LENGTH_LONG).show();
                }
            }
        };
           th.start();

    }
}
