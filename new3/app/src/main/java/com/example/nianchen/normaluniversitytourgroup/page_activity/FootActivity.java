package com.example.nianchen.normaluniversitytourgroup.page_activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendThree;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.FriendsAdapter;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianchen on 2016/11/23.
 */
public class FootActivity extends Activity{//友聊
    private Button btn;
    private TextView tv;
    private EditText et;
    String str="";
    String url="http://op.juhe.cn/onebox/bus/query";
    String key="a97232cd6e8eb94838e1efd5a8a48bee";

    Handler h=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText((String)msg.obj);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jiu_foot);
        btn=(Button)findViewById(R.id.btn);
        tv= (TextView)findViewById(R.id.tv);
        et=(EditText)findViewById(R.id.et);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client =new AsyncHttpClient();
                RequestParams rp=new RequestParams();
                rp.put("station",et.getText().toString());
                rp.put("key",key);
                client.get(url,rp,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONObject j1=response.getJSONObject("result");
                            String s="标题"+j1.getString("title")+"\n";
                            JSONArray j2=j1.getJSONArray("list");
                            for (int i=0;i<j2.length();i++){
                                JSONObject json=j2.getJSONObject(i);
                                String s1=json.getString("name")+"\n";
                                String s2=json.getString("tel")+"\n";
                                String s3=json.getString("adds")+"\n";
                                str=str+s1+s2+s3;
                            }
                            str=s+str;
                            Message msg=h.obtainMessage();
                            msg.obj=str;
                            h.sendMessage(msg);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }
}
