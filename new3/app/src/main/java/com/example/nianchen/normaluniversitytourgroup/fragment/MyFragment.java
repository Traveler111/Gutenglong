package com.example.nianchen.normaluniversitytourgroup.fragment;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nianchen.normaluniversitytourgroup.ChooseAvatar;
import com.example.nianchen.normaluniversitytourgroup.MainActivity;
import com.example.nianchen.normaluniversitytourgroup.MainActivityFabu;
import com.example.nianchen.normaluniversitytourgroup.MyteamActivity;
import com.example.nianchen.normaluniversitytourgroup.R;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by nianchen on 2016/11/21.
 */
public class MyFragment extends Fragment{
    private View view;
    private Button exitbtn;
    private Button btn;
    private TextView number;
    private ProgressDialog mDialog;
    private ImageView image11;
    private Button nick;
    private EditText nickname;
    private TextView mynick;
    private Handler myhandler1=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                Toast.makeText(getActivity(),"上传失败,请检查网络",Toast.LENGTH_LONG).show();
            }
            else if(msg.what==2){
                mDialog.dismiss();
                Toast.makeText(getActivity(),"退出成功",Toast.LENGTH_LONG).show();
                getActivity().finish();
            }
            else{
                Toast.makeText(getActivity(),"上传成功！",Toast.LENGTH_LONG).show();
                mynick.setText(strnick.toString().trim());
            }
        }
    };
    private Handler myhanler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str=(String )msg.obj;
            if(str!=null){
                mynick.setText(str.toString().trim());
            }
            else
                mynick.setText("空");
        }
    };
    private String strnick;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_my, container, false);

        findview();
        btn=(Button)view.findViewById(R.id.btn);
        image11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ChooseAvatar.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent();
                i.setClass(getActivity(), MyteamActivity.class);
                startActivity(i);
            }
        });
        exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = new ProgressDialog(getActivity());
                mDialog.setMessage("正在退出，请稍后...");
                mDialog.show();
                exit();
            }
        });
        number.setText(EMClient.getInstance().getCurrentUser().toString().trim());
        updatenick();
        addImg();
        setnick();
        return view;
    }
    public void findview(){
        exitbtn=(Button)view.findViewById(R.id.exit);
        number=(TextView)view.findViewById(R.id.number);
        image11 = (ImageView)view.findViewById(R.id.ima);
        nick=(Button)view.findViewById(R.id.updatenick);
        mynick=(TextView)view.findViewById(R.id.name);
    }
    public void addImg(){
        String url1="http://jiahanglee.cn/blog/downFile2";
        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams rp2 = new RequestParams();
        rp2.put("username",EMClient.getInstance().getCurrentUser().toString().trim());
//        rp2.put("gg","jpg");
        client.get(url1, rp2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                if (statusCode == 200) {
                    //创建工厂对象
                    BitmapFactory bitmapFactory = new BitmapFactory();
                    //工厂对象的decodeByteArray把字节转换成Bitmap对象
                    Bitmap bitmap = bitmapFactory.decodeByteArray(responseBody, 0, responseBody.length);
                    //设置图片
//                                imageView.setImageBitmap(bitmap);
//                                Message msg = new Message();
//                                msg.obj = bitmap;
//                                msg.arg1 = postion;

                      image11 = (ImageView)view.findViewById(R.id.ima);
                        image11.setImageBitmap(bitmap);
                    }


//                               img1.setImageBitmap(bitmap);



            }

            @Override
            public void onFailure(int statusCode, Header[] headers,
                                  byte[] responseBody, Throwable error) {
                error.printStackTrace();
            }

            @Override
            public void onProgress(long bytesWritten, long totalSize) {
                super.onProgress(bytesWritten, totalSize);
//                            int count = (int) ((bytesWritten * 1.0 / totalSize) * 100);
//                            // 上传进度显示
//                            progress.setProgress(count);
//                            Log.e("上传 Progress>>>>>", bytesWritten + " / " + totalSize);
            }
        });


    }
    public void exit(){
        EMClient.getInstance().logout(true, new EMCallBack() {
            @Override
            public void onSuccess() {
                Message msg=new Message();
                msg.what=2;
                myhandler1.sendMessage(msg);
            }

            @Override
            public void onError(int i, String s) {

            }

            @Override
            public void onProgress(int i, String s) {

            }
        });
    }
    public void updatenick(){
        nick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder adb= new AlertDialog.Builder(getActivity());
                View view=LayoutInflater.from(getActivity()).inflate(R.layout.updatenick,null);
                adb.setView(view);
                adb.setNegativeButton("取消",null);
                nickname=(EditText)view.findViewById(R.id.nickname);
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String uri="http://jiahanglee.cn/blog/uploadnick";
                        AsyncHttpClient client =new AsyncHttpClient();
                        RequestParams params=new RequestParams();
                        params.put("username",number.getText().toString().trim());
                        params.put("nickname",nickname.getText().toString().trim());
                        client.get(uri, params, new AsyncHttpResponseHandler() {
                            @Override
                            public void onSuccess(int i, Header[] headers, byte[] bytes) {
                                   strnick=nickname.getText().toString().trim();
                                   Message msg=new Message();
                                   myhandler1.sendMessage(msg);

                            }

                            @Override
                            public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                                Message msg=new Message();
                                msg.what=1;
                                myhandler1.sendMessage(msg);
                            }
                        });
                    }
                });
                adb.create().show();
            }
        });
    }
    public void setnick(){
        String uri="http://jiahanglee.cn/blog/shownick";
        AsyncHttpClient client=new AsyncHttpClient();
        RequestParams params=new RequestParams();
        params.put("username",number.getText().toString().trim());
        client.get(uri, params,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                try {
                    String str=response.getString("nick");
                    Log.e("get nick",str);
                    Message msg=new Message();
                    msg.obj=str;
                    myhanler.sendMessage(msg);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
