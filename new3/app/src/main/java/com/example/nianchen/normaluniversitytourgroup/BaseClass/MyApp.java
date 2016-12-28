package com.example.nianchen.normaluniversitytourgroup.BaseClass;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.hyphenate.EMContactListener;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.hyphenate.easeui.controller.EaseUI;
import com.hyphenate.easeui.domain.EaseUser;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zhangzhixin on 2016/11/29.
 */
public class MyApp extends Application {
    private static Context context;
    // 上下文菜单
    private Context mContext;
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Myuser myuser= (Myuser) msg.obj;
            Boolean getuser=queryuser(myuser.getUsername());
            if(getuser==false){
                ContentValues c=new ContentValues();
                c.put("username",myuser.getUsername());
                c.put("nick",myuser.getNick());
                c.put("img",myuser.getImg());
                c.put("gg",myuser.getGg());
                Log.e("new ","insert");
                tableuser.insert("user",null,c);
            }
            else{
                ContentValues c=new ContentValues();
                c.put("nick",myuser.getNick());
                c.put("img",myuser.getImg());
                c.put("gg",myuser.getGg());
                Log.e("update","run");
                tableuser.update("user",c,"username=?",new String []{myuser.getUsername()});
            }
        }
    };

    // 记录是否已经初始化
    private boolean isInit = false;
    private Mysql sql;
    private SQLiteDatabase tableuser;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        MyApp.context = getApplicationContext();
        opensql();
        // 初始化环信SDK
        initEasemob();
        setfriendlistener();
    }
    public static Context getAppContext() {
        return MyApp.context;
    }
    /**
     *
     */
    private void initEasemob() {
        // 获取当前进程 id 并取得进程名
        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
        /**
         * 如果app启用了远程的service，此application:onCreate会被调用2次
         * 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
         * 默认的app会在以包名为默认的process name下运行，如果查到的process name不是app的process name就立即返回
         */
        if (processAppName == null || !processAppName.equalsIgnoreCase(mContext.getPackageName())) {
            // 则此application的onCreate 是被service 调用的，直接返回
            return;
        }
        if (isInit) {
            return;
        }
        /**
         * SDK初始化的一些配置
         * 关于 EMOptions 可以参考官方的 API 文档
         * http://www.easemob.com/apidoc/android/chat3.0/classcom_1_1hyphenate_1_1chat_1_1_e_m_options.html
         */
        EMOptions options = new EMOptions();
        // 设置Appkey，如果配置文件已经配置，这里可以不用设置
        // options.setAppKey("guaju");
        // 设置自动登录
       options.setAutoLogin(true);
        // 设置是否需要发送已读回执
        options.setRequireAck(true);
        // 设置是否需要发送回执，TODO 这个暂时有bug，上层收不到发送回执
        options.setRequireDeliveryAck(true);
        // 设置是否需要服务器收到消息确认
        options.setRequireServerAck(true);
        // 收到好友申请是否自动同意，如果是自动同意就不会收到好友请求的回调，因为sdk会自动处理，默认为true
        options.setAcceptInvitationAlways(false);
        // 设置是否自动接收加群邀请，如果设置了当收到群邀请会自动同意加入
        options.setAutoAcceptGroupInvitation(false);
        // 设置（主动或被动）退出群组时，是否删除群聊聊天记录
        options.setDeleteMessagesAsExitGroup(false);
        // 设置是否允许聊天室的Owner 离开并删除聊天室的会话
        options.allowChatroomOwnerLeave(true);
        // 设置google GCM推送id，国内可以不用设置
        // options.setGCMNumber(MLConstants.ML_GCM_NUMBER);
        // 设置集成小米推送的appid和appkey
        // options.setMipushConfig(MLConstants.ML_MI_APP_ID, MLConstants.ML_MI_APP_KEY);

        // 调用初始化方法初始化sdk
        EaseUI.getInstance().init(this,options);

        // 设置开启debug模式
        EMClient.getInstance().setDebugMode(true);

//需要EaseUI库显示用户头像和昵称设置此provider
        EaseUI.getInstance().setUserProfileProvider(new EaseUI.EaseUserProfileProvider() {

            @Override
            public EaseUser getUser(String username) {

                EaseUser user1= getUserInfo(username);
                Log.e("username",username);
                return user1;
            }
        });

        // 设置初始化已经完成
        isInit = true;
    }

    private EaseUser getUserInfo(String username) {
        EaseUser user=new EaseUser(username);

        String str="http://123.207.228.232/blog/getuser";
        AsyncHttpClient client =new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",username.toString().trim());
        Cursor cursor=fromsqlgetuser(username);
        if(cursor.getCount()>0){
            cursor.moveToNext();
            String nick=cursor.getString(cursor.getColumnIndex("nick"));
            String imgname=cursor.getString(cursor.getColumnIndex("img"));
            String gg=cursor.getString(cursor.getColumnIndex("gg"));
            Log.e("nick",nick);
            Log.e("imgname",imgname);
            Log.e("gg",gg);
            if(nick.equals("null")){
                Log.e("null","run");
            }
            else
            user.setNick(nick);
            user.setAvatar("http://123.207.228.232/blog/testpic?img="+imgname+"&gg="+gg);
        }
        Log.e("final test",cursor.moveToFirst()+"");
        cursor.close();
        client.get(str, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    Log.e("success","get");
                    String username=response.getString("username");
                    String gg=response.getString("gg");
                    String imgname=response.getString("img");
                    String nick=response.getString("nick");
                    Message msg=new Message();
                    Myuser myuser=new Myuser(username,nick,imgname,gg);
                    msg.obj=myuser;
                    myhandler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
       // user.setNick("张三");
       // user.setAvatar("http://pic4.nipic.com/20090901/715446_101334029483_2.jpg");
        return user;
    }

    /**
     * 根据Pid获取当前进程的名字，一般就是当前app的包名
     *
     * @param pid 进程的id
     * @return 返回进程的名字
     */
    private String getAppName(int pid) {
        String processName = null;
        ActivityManager activityManager = (ActivityManager) mContext.getSystemService(Context.ACTIVITY_SERVICE);
        List list = activityManager.getRunningAppProcesses();
        Iterator i = list.iterator();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pid) {
                    // 根据进程的信息获取当前进程的名字
                    processName = info.processName;
                    // 返回当前进程名
                    return processName;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // 没有匹配的项，返回为null
        return null;
    }
    public void setfriendlistener() {
        EMClient.getInstance().contactManager().setContactListener(new EMContactListener() {
            @Override
            public void onContactAdded(String s) {

            }

            @Override
            public void onContactDeleted(String s) {

            }

            @Override
            public void onContactInvited(String s, String s1) {
                Log.e("receive from " + s, "reason is" + s1);
                Intent intent = new Intent();
                //与清单文件的receiver的anction对应
                intent.setAction("com.broadcast.test");
                intent.putExtra("name", s);
                intent.putExtra("reason", s1);
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
    public void opensql(){
        sql=new Mysql(getAppContext(),"user",null,1);
        tableuser=sql.getReadableDatabase();
    }
    public boolean queryuser(String username){
        Cursor cursor=tableuser.query("user",new String[]{"username","nick","img","gg"},"username=?",new String []{username},null,null,null);
        if(cursor.getCount()>0){
            Log.e("queryuser",""+cursor.getCount());
            return true;
        }
        else{
            Log.e("queryuser",""+cursor.getCount());

            return false;
        }

    }
    public Cursor  fromsqlgetuser(String username){
        Log.e("fromsqlgetuser",username);
        Cursor cursor=tableuser.query("user",new String[]{"username","nick","img","gg"},"username=?",new String []{username},null,null,null);

        return cursor;
    }
}
