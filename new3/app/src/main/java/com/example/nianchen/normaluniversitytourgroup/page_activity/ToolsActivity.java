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
public class ToolsActivity extends Activity{
    private Button btn;
    private TextView tv;
    private EditText et;
    private EditText et1;
    String str="";
    String url="http://op.juhe.cn/189/bus/busline";
    String key="0faad55a4abbfc08fe0f10d38062ec59";

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
        setContentView(R.layout.jiu_tools);
        btn=(Button)findViewById(R.id.btn);
        tv= (TextView)findViewById(R.id.tv);
        et=(EditText)findViewById(R.id.et);
        et1=(EditText)findViewById(R.id.et1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyncHttpClient client =new AsyncHttpClient();
                RequestParams rp=new RequestParams();
                rp.put("city",et.getText().toString());
                rp.put("bus",et1.getText().toString());
                rp.put("key",key);
                client.get(url,rp,new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        super.onSuccess(statusCode, headers, response);
                        try {
                            JSONArray j1=response.getJSONArray("result");
                            for (int j=0;j<j1.length();j++){
                                JSONObject jj1=j1.getJSONObject(j);
                                String s="始发站-终点站："+jj1.getString("front_name")+"-"+jj1.getString("terminal_name")+"\n";
                                JSONArray ja=jj1.getJSONArray("stationdes");
                                for (int i=0;i<ja.length();i++){
                                    JSONObject json=ja.getJSONObject(i);
                                    String ss1="第"+json.getString("stationNum")+"站："+json.getString("name")+"\n";
                                    str=str+ss1;
                                }
                                str=s+str;
                            }
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
