package com.example.nianchen.normaluniversitytourgroup;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.Group;
import com.example.nianchen.normaluniversitytourgroup.adapter.Groupmyadapter;
import com.example.nianchen.normaluniversitytourgroup.page_activity.MyGroup;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMCursorResult;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupInfo;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.exceptions.HyphenateException;

import java.util.ArrayList;
import java.util.List;

public class MyteamActivity extends Activity {


    private ListView lv;
    private List<EMGroup> groupsList=new ArrayList<>();
    private int pageSize=20;
    private ArrayList<Group>mygroup=new ArrayList<>();
    private Groupmyadapter myadapter;
    private Handler deletehandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Toast.makeText(MyteamActivity.this,"群组解散成功！",Toast.LENGTH_LONG).show();
            }
            else if(msg.what==2){
                Toast.makeText(MyteamActivity.this,"退出群组成功！",Toast.LENGTH_LONG).show();
            }
        }
    };
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            mygroup.clear();
            super.handleMessage(msg);
            for(int i=0;i<groupsList.size();i++){
                Group group=new Group(groupsList.get(i).getGroupName(),groupsList.get(i).getGroupId()
                ,R.drawable.loginh);


                mygroup.add(group);
            }
            myadapter=new Groupmyadapter(MyteamActivity.this,mygroup);
            lv.setAdapter(myadapter);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myteam);
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
//        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        findview();
        getgrouplist();
    }
    public void  findview(){
        lv=(ListView)findViewById(R.id.lv);
        lv.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                menu.add(menu.NONE,0,0,"删除");
            }
        });
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ina=new Intent(MyteamActivity.this, MyGroup.class);
                ina.putExtra(EaseConstant.EXTRA_CHAT_TYPE,EaseConstant.CHATTYPE_GROUP);
                ina.putExtra(EaseConstant.EXTRA_USER_ID,mygroup.get(position).getDesc().toString().trim());
                startActivity(ina);
            }
        });
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo;
        menuInfo =(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        switch (item.getItemId()){
            case 0:
                deletegroup(mygroup.get(menuInfo.position).getDesc().toString().trim());
                onResume();
                //点击第一个菜单项要做的事，如获取点击listview的位置
//                Toast.makeText(MyteamActivity.this, String.valueOf(menuInfo.position), Toast.LENGTH_LONG).show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    public void getgrouplist(){
         Thread th=new Thread(){
             @Override
             public void run() {
                 super.run();
                 EMCursorResult<EMGroupInfo> result = null;//需异步处理
                 try {
                     //result = EMClient.getInstance().groupManager().getPublicGroupsFromServer(pageSize, "1");
                    // List<EMGroupInfo> returnGroups = result.getData();
                     List<EMGroup> returnGroups = EMClient.getInstance().groupManager().getJoinedGroupsFromServer();
                     groupsList =returnGroups;
                     Message msg=new Message();
                     myhandler.sendMessage(msg);
                 } catch (HyphenateException e) {
                     e.printStackTrace();
                 }
             }
         };
         th.start();
    }
    public void deletegroup(final String groupid) {
        Thread th = new Thread() {
            @Override
            public void run() {
                super.run();
                EMGroup group=EMClient.getInstance().groupManager().getGroup(groupid);
                if(EMClient.getInstance().getCurrentUser().equals(group.getOwner())){
                    try {
                        EMClient.getInstance().groupManager().destroyGroup(groupid);
                        Message msg=new Message();
                        msg.what=1;
                        deletehandler.sendMessage(msg);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    try {
                        EMClient.getInstance().groupManager().leaveGroup(groupid);
                        Message msg=new Message();
                        msg.what=2;
                        deletehandler.sendMessage(msg);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        th.start();
    }

    @Override
    protected void onResume() {
        findview();
        getgrouplist();
        super.onResume();
    }
}
