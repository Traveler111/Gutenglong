package com.example.nianchen.normaluniversitytourgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.nianchen.normaluniversitytourgroup.fragment.AttFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.FindFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.HomeFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.MesFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.MyFragment;
import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;

public class MainActivity extends Activity {

    public static LinearLayout ll;
    private Button mBtnHome;
    private Button mBtnFind;
    private Button mBtnMy;
    private Button mBtnMes;
    private Button mBtnAtt;//景点

    //声明Fragment属性
    private HomeFragment mHome;
    private FindFragment mFind;
    private MyFragment mMy;
    private MesFragment mMes;
    private AttFragment mAtt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        //获取界面控件
        getViews();
        //注册事件监听器
        setListener();
        //设置默认页面
        setDefaultPage();
        setfriendlistener();

    }

    private void setListener() {
        MyListener listener = new MyListener();
        mBtnHome.setOnClickListener(listener);
        mBtnFind.setOnClickListener(listener);
        mBtnMy.setOnClickListener(listener);
        mBtnMes.setOnClickListener(listener);
        mBtnAtt.setOnClickListener(listener);
    }
    //she值事件监听器

    //
    private void setDefaultPage(){
        android.app.FragmentManager fm = getFragmentManager();
        //获取fragmentTranSaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        //默认页面
        mHome = new  HomeFragment();
        transaction.replace(R.id.contaner,mHome);
        //执行更改
        transaction.commit();
    }

    //获取控件
    private void getViews(){
        ll=(LinearLayout)findViewById(R.id.ll);
        mBtnHome = (Button) findViewById(R.id.btn_home);
        mBtnFind = (Button) findViewById(R.id.btn_find);
        mBtnMy = (Button) findViewById(R.id.btn_my);
        mBtnMes = (Button) findViewById(R.id.btn_mes);
        mBtnAtt = (Button) findViewById(R.id.btn_attractions);

    }


    class MyListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            android.app.FragmentManager fm = getFragmentManager();
            android.app.FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()) {
                case R.id.btn_home:
                    if (mHome == null) {
                        mHome = new HomeFragment();
                    }
                    transaction.replace(R.id.contaner, mHome);
                    break;
                case R.id.btn_find:
                    if (mFind == null) {
                        mFind = new FindFragment();
                    }
                    transaction.replace(R.id.contaner, mFind);
                    break;
                case R.id.btn_mes:
                    if (mMes == null) {
                        mMes = new MesFragment();
                    }
                    transaction.replace(R.id.contaner, mMes);
                    break;
                case R.id.btn_attractions:
                    if (mAtt == null) {
                        mAtt = new AttFragment();
                    }
                    transaction.replace(R.id.contaner, mAtt);
                    break;
                case R.id.btn_my:
                    if (mMy == null) {
                        mMy = new MyFragment();
                    }
                    transaction.replace(R.id.contaner, mMy);
                    break;
            }
            transaction.commit();
            ll.invalidate();
        }
    }
    public void setfriendlistener(){
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {

            }

            @Override
            public void onContactDeleted(String s) {

            }

            @Override
            public void onContactInvited(String s, String s1) {
                Log.e("receive from "+s,"reason is"+s1);
                Intent intent=new Intent();
                //与清单文件的receiver的anction对应
                intent.setAction("com.broadcast.test");
                intent.putExtra("name",s);
                intent.putExtra("reason",s1);
                //发送广播
                sendBroadcast(intent);
            }

            @Override
            public void onContactAgreed(String s) {

            }

            @Override
            public void onContactRefused(String s) {

            }
        });
    }
}
