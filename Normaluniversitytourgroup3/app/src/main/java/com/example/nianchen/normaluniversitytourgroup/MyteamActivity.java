package com.example.nianchen.normaluniversitytourgroup;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.MyApp;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MyteamActivity extends AppCompatActivity {

    private TextView title;
    private LinearLayout ll;
    String sname;
    String stitle;
    String sname1;
    String stitle1;
    String s;
    String title1;
    String name="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myteam);
        title=(TextView)findViewById(R.id.title);
        ll=(LinearLayout)findViewById(R.id.ll);
        SharedPreferences spf= MyApp.getAppContext().getSharedPreferences("User",MODE_PRIVATE);
        s=spf.getString("uname","");
        System.out.println("用户名"+s);
        String url="http://123.207.228.232/blog/Xianshijiaru";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(MyteamActivity.this,url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                super.onSuccess(statusCode, headers, response);
                for (int i=0;i<response.length();i++){
                    try {
                        JSONObject json=response.getJSONObject(i);
                        sname=json.getString("Uname");
                        stitle=json.getString("Title");
                        System.out.println(sname+" 丨 "+stitle);
                        if (s.equals(sname)){
                            title1=stitle;
                            System.out.println("111");
                            title.setText(title1);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
//
//        AsyncHttpClient client1=new AsyncHttpClient();
//        client1.get(MyteamActivity.this,url,new JsonHttpResponseHandler(){
//            @Override
//            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
//                super.onSuccess(statusCode, headers, response);
//                for (int i=0;i<response.length();i++){
//                    try {
//                        JSONObject json=response.getJSONObject(i);
//                        sname1=json.getString("Uname");
//                        stitle1=json.getString("Title");
//                        if (title1.equals(stitle)){
//                            name=name+" 丨 "+sname1+" 丨 ";
//                            System.out.println("222");
//                            cy.setText(name);
//                        }
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
        ll.invalidate();
    }
}
