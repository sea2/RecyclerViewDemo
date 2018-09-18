package com.chad.baserecyclerviewadapterhelper;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.leakcanary.RefWatcher;

public class WelcomeActivity extends AppCompatActivity implements  CallBack.Listener{


    private int i = 0;
    private android.widget.TextView tvtext;
    private android.widget.RelativeLayout activitywelcome;
    CallBack callBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.tvtext = (TextView) findViewById(R.id.tv_text);


         callBack=new CallBack();
        callBack.setListener(this);


new Handler().postDelayed(mRunnable,2000);



    }


    public void requestPower() {
        //判断是否已经赋予权限
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            //如果应用之前请求过此权限但用户拒绝了请求，此方法将返回 true。
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                //这里可以写个对话框之类的项向用户解释为什么要申请权限，并在对话框的确认键后续再次申请权限
            } else {
                //申请权限，字符串数组内是一个或多个要申请的权限，1是申请权限结果的返回参数，在onRequestPermissionsResult可以得知申请结果
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,}, 1);
            }
        }
    }


    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tvtext.setText(i + "");
            mHandler.sendEmptyMessageDelayed(1, 1000);
        }
    };

    Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            Intent intent = new Intent(WelcomeActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }
    };


    @Override
    protected void onDestroy() {
           if (mHandler != null) mHandler.removeCallbacksAndMessages(null);
        //  if (mHandler != null) mHandler.removeCallbacks(mRunnable);

        super.onDestroy();
        RefWatcher refWatcher = MyApplication.getRefWatcher(this);
        refWatcher.watch(callBack);
    }

    @Override
    public void doSoming() {
        tvtext.setText(i + "");
        Toast.makeText(this,""+i,Toast.LENGTH_LONG).show();
    }



}
