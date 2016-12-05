package com.example.nianchen.normaluniversitytourgroup;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.nianchen.normaluniversitytourgroup.fragment.AttFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.FindFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.HomeFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.MesFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.MyFragment;

public class MainActivity extends AppCompatActivity {

    public static LinearLayout ll;

    //声明Fragment属性
    private HomeFragment mHome;
    private FindFragment mFind;
    private MyFragment mMy;
    private MesFragment mMes;
    private AttFragment mAtt;

    private LinearLayout liner_home;
    private LinearLayout liner_find;
    private LinearLayout liner_mes;
    private LinearLayout liner_attractions;
    private LinearLayout liner_my;

    private ImageButton image_home;
    private ImageButton image_find;
    private ImageButton image_mes;
    private ImageButton image_attractions;
    private ImageButton image_my;

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
    }

    private void setListener() {
        MyListener mylistener = new MyListener();
        liner_home.setOnClickListener(mylistener);
        liner_find.setOnClickListener(mylistener);
        liner_mes.setOnClickListener(mylistener);
        liner_attractions.setOnClickListener(mylistener);
        liner_my.setOnClickListener(mylistener);
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
        ResetTabsImg();
        SetTabsSelectedImg(0);
        //执行更改
        transaction.commit();
    }

    //获取控件
    private void getViews(){
        ll =(LinearLayout)findViewById(R.id.ll);
        liner_home = (LinearLayout)findViewById(R.id.liner_home);
        liner_find = (LinearLayout)findViewById(R.id.liner_find);
        liner_mes = (LinearLayout)findViewById(R.id.liner_mes);
        liner_attractions = (LinearLayout)findViewById(R.id.liner_attractions);
        liner_my = (LinearLayout)findViewById(R.id.liner_my);

        image_home = (ImageButton)findViewById(R.id.image_home);
        image_find = (ImageButton)findViewById(R.id.image_find);
        image_mes = (ImageButton)findViewById(R.id.image_mes);
        image_attractions = (ImageButton)findViewById(R.id.image_attractions);
        image_my = (ImageButton)findViewById(R.id.image_my);

    }


    class MyListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            android.app.FragmentManager fm = getFragmentManager();
            android.app.FragmentTransaction transaction = fm.beginTransaction();
            switch (v.getId()) {
                case R.id.liner_home:
                    if (mHome == null) {
                        mHome = new HomeFragment();
                    }
                    transaction.replace(R.id.contaner, mHome);
                    ResetTabsImg();
                    SetTabsSelectedImg(0);
                    break;
                case R.id.liner_find:
                    if (mFind == null) {
                        mFind = new FindFragment();
                    }
                    transaction.replace(R.id.contaner, mFind);
                    ResetTabsImg();
                    SetTabsSelectedImg(1);
                    break;
                case R.id.liner_mes:
                    if (mMes == null) {
                        mMes = new MesFragment();
                    }
                    transaction.replace(R.id.contaner, mMes);
                    ResetTabsImg();
                    SetTabsSelectedImg(2);
                    break;
                case R.id.liner_attractions:
                    if (mAtt == null) {
                        mAtt = new AttFragment();
                    }
                    transaction.replace(R.id.contaner, mAtt);
                    ResetTabsImg();
                    SetTabsSelectedImg(3);
                    break;
                case R.id.liner_my:
                    if (mMy == null) {
                        mMy = new MyFragment();
                    }
                    transaction.replace(R.id.contaner, mMy);
                    ResetTabsImg();
                    SetTabsSelectedImg(4);
                    break;
            }
            transaction.commit();
            ll.invalidate();
        }
    }
    private void ResetTabsImg() {
        image_home.setImageResource(R.drawable.home1);
        image_find.setImageResource(R.drawable.find1);
        image_mes.setImageResource(R.drawable.mess1);
        image_attractions.setImageResource(R.drawable.jing1);
        image_my.setImageResource(R.drawable.my1);
    }
    private void SetTabsSelectedImg(int i) {
        switch (i) {
            case 0:
                image_home.setImageResource(R.drawable.home2);
                break;
            case 1:
                image_find.setImageResource(R.drawable.find2);
                break;
            case 2:
                image_mes.setImageResource(R.drawable.mess2);
                break;
            case 3:
                image_attractions.setImageResource(R.drawable.jing2);
                break;
            case 4:
                image_my.setImageResource(R.drawable.my2);
                break;
        }
    }

}
