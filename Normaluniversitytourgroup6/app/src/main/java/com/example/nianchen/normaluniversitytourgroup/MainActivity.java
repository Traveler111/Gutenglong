package com.example.nianchen.normaluniversitytourgroup;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.nianchen.normaluniversitytourgroup.fragment.AttFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.FindFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.HomeFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.MesFragment;
import com.example.nianchen.normaluniversitytourgroup.fragment.MyFragment;

public class MainActivity extends FragmentActivity {
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
//    声明Fragment属性
    private HomeFragment mHome;
    private FindFragment mFind;
    private MyFragment mMy;
    private MesFragment mMes;
    private AttFragment mAtt;
//    private ViewPager vp_fragment_viewpage;
//    private List<Fragment> fragments;
    private MyListener mylistener;
    private LinearLayout ll;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        findView();
        setListener();
        setDefaultPage();
    }
    private void setDefaultPage(){
        android.app.FragmentManager fm = getFragmentManager();
        //获取fragmentTranSaction
        android.app.FragmentTransaction transaction = fm.beginTransaction();
        //默认页面
        ResetTabsImg();
        mHome = new  HomeFragment();
        transaction.replace(R.id.contaner,mHome);
        SetTabsSelectedImg(0);
        //执行更改
        transaction.commit();
    }

    private void setListener() {
        mylistener = new MyListener();
        liner_home.setOnClickListener(mylistener);
        liner_find.setOnClickListener(mylistener);
        liner_mes.setOnClickListener(mylistener);
        liner_attractions.setOnClickListener(mylistener);
        liner_my.setOnClickListener(mylistener);
//        vp_fragment_viewpage.setOnPageChangeListener(new vpOnChangeListener());
    }

    private void findView() {
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
//    class vpOnChangeListener extends SimpleOnPageChangeListener {
//        @Override
//        public void onPageSelected(int position) {
//            //设置 tab 背景 
//            ResetTabsImg();
//            SetTabsSelectedImg(position);
//        }
//   }
    class MyListener implements View.OnClickListener{
//    public void onPageSelected(int position) {
//        //设置 tab 背景 
//        ResetTabsImg();
//        SetTabsSelectedImg(position);
//    }
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
//    public void onClick(View v) {
//        android.app.FragmentManager fm = getFragmentManager();
//        android.app.FragmentTransaction transaction = fm.beginTransaction();
//        ResetTabsImg();
//        switch (v.getId()) {
//            case R.id.liner_home:
//                SetTabsSelectedImg(0);
//                break;
//            case R.id.liner_find:
//                SetTabsSelectedImg(1);
//                break;
//            case R.id.liner_mes:
//                SetTabsSelectedImg(2);
//                break;
//            case R.id.liner_attractions:
//                SetTabsSelectedImg(3);
//                break;
//            case R.id.liner_my:
//                SetTabsSelectedImg(4);
//                break;
//        }
//    }
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
    private void ResetTabsImg() {
        image_home.setImageResource(R.drawable.home1);
        image_find.setImageResource(R.drawable.find1);
        image_mes.setImageResource(R.drawable.mess1);
        image_attractions.setImageResource(R.drawable.jing1);
        image_my.setImageResource(R.drawable.my1);
    }
}
//public class MainActivity extends AppCompatActivity {
//
//    public static LinearLayout ll;
//    private Button mBtnHome;
//    private Button mBtnFind;
//    private Button mBtnMy;
//    private Button mBtnMes;
//    private Button mBtnAtt;//景点
//
//    //声明Fragment属性
//    private HomeFragment mHome;
//    private FindFragment mFind;
//    private MyFragment mMy;
//    private MesFragment mMes;
//    private AttFragment mAtt;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        setContentView(R.layout.activity_main);
//        //获取界面控件
//        getViews();
//        //注册事件监听器
//        setListener();
//        //设置默认页面
//        setDefaultPage();
//    }
//
//    private void setListener() {
//        MyListener listener = new MyListener();
//        mBtnHome.setOnClickListener(listener);
//        mBtnFind.setOnClickListener(listener);
//        mBtnMy.setOnClickListener(listener);
//        mBtnMes.setOnClickListener(listener);
//        mBtnAtt.setOnClickListener(listener);
//    }
//    //she值事件监听器
//
//    //
//    private void setDefaultPage(){
//        android.app.FragmentManager fm = getFragmentManager();
//        //获取fragmentTranSaction
//        android.app.FragmentTransaction transaction = fm.beginTransaction();
//        //默认页面
//        mHome = new  HomeFragment();
//        transaction.replace(R.id.contaner,mHome);
//        //执行更改
//        transaction.commit();
//    }
//
//    //获取控件
//    private void getViews(){
//        ll=(LinearLayout)findViewById(R.id.ll);
//        mBtnHome = (Button) findViewById(R.id.btn_home);
//        mBtnFind = (Button) findViewById(R.id.btn_find);
//        mBtnMy = (Button) findViewById(R.id.btn_my);
//        mBtnMes = (Button) findViewById(R.id.btn_mes);
//        mBtnAtt = (Button) findViewById(R.id.btn_attractions);
//
//    }
//
//
//    class MyListener implements View.OnClickListener{
//        @Override
//        public void onClick(View v) {
//            android.app.FragmentManager fm = getFragmentManager();
//            android.app.FragmentTransaction transaction = fm.beginTransaction();
//            switch (v.getId()) {
//                case R.id.btn_home:
//                    if (mHome == null) {
//                        mHome = new HomeFragment();
//                    }
//                    transaction.replace(R.id.contaner, mHome);
//                    break;
//                case R.id.btn_find:
//                    if (mFind == null) {
//                        mFind = new FindFragment();
//                    }
//                    transaction.replace(R.id.contaner, mFind);
//                    break;
//                case R.id.btn_mes:
//                    if (mMes == null) {
//                        mMes = new MesFragment();
//                    }
//                    transaction.replace(R.id.contaner, mMes);
//                    break;
//                case R.id.btn_attractions:
//                    if (mAtt == null) {
//                        mAtt = new AttFragment();
//                    }
//                    transaction.replace(R.id.contaner, mAtt);
//                    break;
//                case R.id.btn_my:
//                    if (mMy == null) {
//                        mMy = new MyFragment();
//                    }
//                    transaction.replace(R.id.contaner, mMy);
//                    break;
//            }
//            transaction.commit();
//            ll.invalidate();
//        }
//    }
