package com.example.nianchen.normaluniversitytourgroup.fragment;

import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianchen.normaluniversitytourgroup.BaseClass.FriendOne;
import com.example.nianchen.normaluniversitytourgroup.BaseClass.Friendlistadapter;
import com.example.nianchen.normaluniversitytourgroup.BaseClass.Myfriendzzx;
import com.example.nianchen.normaluniversitytourgroup.MainActivity;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.example.nianchen.normaluniversitytourgroup.adapter.MesFragmentMesAdapter;
import com.example.nianchen.normaluniversitytourgroup.adapter.MesFragmentContactAdapter;
import com.example.nianchen.normaluniversitytourgroup.page_activity.ChatActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.hyphenate.easeui.EaseConstant;
import com.hyphenate.easeui.ui.EaseConversationListFragment;
import com.hyphenate.exceptions.HyphenateException;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nianchen on 2016/11/22.
 */
public class MesFragment extends Fragment {
    private ArrayList <Friendlistadapter> friendlistadapters=new ArrayList<>();
    private View view;
    private SearchView search;
    private LinearLayout chat;
    private ListView list11;
    private String findresult;
    private ListView lv;
    private MesFragmentMesAdapter myadapter;
    private ListView list12;
    private ArrayList <String >myfriends=new ArrayList<>();
    private MesFragmentContactAdapter myadapter1;
    private ArrayList<Myfriendzzx> friends = new ArrayList<>();
    private ArrayList<Myfriendzzx> friends12;
    private Handler showfriendlsit=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Toast.makeText(getActivity(),"你的网络太慢了。。",Toast.LENGTH_LONG).show();
                return;
            }
            Friendlistadapter friendlistadapter= (Friendlistadapter) msg.obj;
            friendlistadapters.add(friendlistadapter);
            myadapter1.notifyDataSetChanged();
        }
    };
    private Handler getfriendhandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            List<String> lists = (List<String>) msg.obj;
            myfriends.clear();

            for(int i=0;i<lists.size();i++){
                Log.e("my friend",lists.get(i));
            }
            for (int i = 0; i < lists.size()-1; i++) {
                if(lists.get(i).equals(lists.get(i+1))){

                }
                else if(i+1!=lists.size()-1&&lists.get(i)!=(lists.get(i+1))){
                    myfriends.add(lists.get(i));
                    Log.e("" + lists.get(i), "get");
                }
                else {
                    myfriends.add(lists.get(i));
                    myfriends.add(lists.get(i+1));
                }

            }
             ArrayList <String >lists1=new ArrayList<>();
             lists1.addAll(myfriends);
             getfriendmes(lists1);
             Log.e("myfriend size",lists1.size()+"");
        }
    };
    private Handler myhandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            final String str=(String )msg.obj;
            AlertDialog.Builder adb=new AlertDialog.Builder(getActivity());
            View view=getActivity().getLayoutInflater().inflate(R.layout.findfriend,null);
            adb.setView(view);
            adb.setTitle("寻找朋友");
            TextView name=(TextView)view.findViewById(R.id.username);
            name.setText(str);
            adb.setPositiveButton("加为好友", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    try {
                        EMClient.getInstance().contactManager().addContact(str, "做个朋友吧");
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }
                }
            });
            adb.setNegativeButton("取消",null);
            if(findresult.equals("ok"))
            adb.create().show();
            else
                Toast.makeText(getActivity(),"用户不存在",Toast.LENGTH_LONG).show();
        }
    };
    private EaseConversationListFragment conversationListFragment;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        chat = (LinearLayout) view.findViewById(R.id.chat);
        Tabhosts();
        getViews();
        //  getdata1();
        // getdata2();
       // getfriendlist();
        searchfriend();
        //  myadapter = new MesFragmentMesAdapter(getActivity(),friends1);
        myadapter1 = new MesFragmentContactAdapter(friendlistadapters,getActivity());
       // list11.setAdapter(myadapter);
        list12.setAdapter(myadapter1);
        list12.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent ina=new Intent(getActivity(), ChatActivity.class);
                ina.putExtra(EaseConstant.EXTRA_CHAT_TYPE, EaseConstant.CHATTYPE_SINGLE);
                ina.putExtra(EaseConstant.EXTRA_USER_ID,friendlistadapters.get(position).getUsername().toString().trim());
                startActivity(ina);
            }
        });
        return view;
    }
    private void getViews() {
        list12 = (ListView) view.findViewById(R.id.mes_list1);
        lv=(ListView)view.findViewById(R.id.findlist);
//        btn=(Button)view.findViewById(R.id.btn);
        search=(SearchView)view.findViewById(R.id.search);
        Log.e("find","run");
    }
    private void Tabhosts() {
        TabHost tabHost = (TabHost) view.findViewById(R.id.tabhost);
        tabHost.setup();
        TabWidget tabWidget = tabHost.getTabWidget();
        TabHost.TabSpec tab001 = tabHost.newTabSpec("tab001")
                .setIndicator("联系人")
                .setContent(R.id.tab001);
        tabHost.addTab(tab001);
        TabHost.TabSpec tab002 = tabHost.newTabSpec("tab002")
                .setIndicator("添加好友")
                .setContent(R.id.tab002);
        tabHost.addTab(tab002);
        if (getId() == R.id.liner_mes) {
            tabHost.addTab(tab001);
            tabHost.addTab(tab002);
        } else if (getId() == R.id.chat) {
            tabHost.addTab(tab002);
            tabHost.addTab(tab001);
        }
    }
    public void onPause() {
       // friends12.clear();
        Log.e("pause","run");
        super.onPause();
    }
    public void getfriendlist() {
        Thread th = new Thread() {
            @Override
            public void run() {
                super.run();
                try {
                    List<String> usernames = EMClient.getInstance().contactManager().getAllContactsFromServer();
                    Message msg = new Message();
                    msg.obj = usernames;
                    getfriendhandler.sendMessage(msg);
                    Log.e("getfriendlist", "run");
                } catch (HyphenateException e) {
                    Message msg = new Message();
                    msg.what=1;
                    getfriendhandler.sendMessage(msg);
                    e.printStackTrace();
                }
            }
        };
        th.start();
    };
    public void searchfriend(){
        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                if(query==null){
                    Toast.makeText(getActivity(),"好友不可为空",Toast.LENGTH_LONG).show();
                    return false;
                }
                AsyncHttpClient client=new AsyncHttpClient();
                String str="http://123.207.228.232/blog/findfriend";
                RequestParams params=new RequestParams();
                params.put("username",query);
                client.get(str, params, new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        byte [] mybytes=bytes;
                        findresult=new String (mybytes);
                        Message msg=new Message();
                        msg.obj=query;
                        myhandler.sendMessage(msg);

                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {

                    }
                });
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
    @Override
    public void onResume() {
        friendlistadapters.clear();
        myadapter1.notifyDataSetChanged();
        getfriendlist();
        Log.e("resume","run");
        super.onResume();
    }
    public void getfriendmes(final ArrayList <String> friends1){
        String str="http://123.207.228.232/blog/downFile2";
        for(int i=0;i<friends1.size();i++) {
            AsyncHttpClient client =new AsyncHttpClient();
            RequestParams params = new RequestParams();
            params.put("username", friends1.get(i));
            final String username=friends1.get(i);
            Log.e("ss",username);
            client.get(str, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    BitmapFactory bitmapFactory = new BitmapFactory();
                    //工厂对象的decodeByteArray把字节转换成Bitmap对象
                    Bitmap bitmap = bitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                 //  Log.e("ss",""+i);
                    Message msg=new Message();
                    msg.obj=new Friendlistadapter(username,bitmap);
                    showfriendlsit.sendMessage(msg);
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    Log.e("failed code",i+"");
                    BitmapFactory bitmapFactory = new BitmapFactory();
                    //工厂对象的decodeByteArray把字节转换成Bitmap对象
                    Bitmap bitmap = bitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    Message msg=new Message();
                    msg.obj=new Friendlistadapter(username,bitmap);
                    showfriendlsit.sendMessage(msg);
                }
            });

        }
        }
}
