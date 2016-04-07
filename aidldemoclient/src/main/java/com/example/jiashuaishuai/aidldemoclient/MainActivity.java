package com.example.jiashuaishuai.aidldemoclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.jiashuaishuai.aidldemoservice.MyAidl;
import com.example.jiashuaishuai.aidldemoservice.TestBean;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MyClient----";
    private String action = "com.example.jiashuaishuai.aidldemoservice.Myservice";
    private MyAidl mMyAidl;
    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mMyAidl = MyAidl.Stub.asInterface(service);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG, "读取");
                    try {
                        List<TestBean> testBeanList = mMyAidl.getTestBeanList();
                        Log.i(TAG,"读取"+testBeanList.get(0).name);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.i(TAG,"写入");
                    TestBean testBean=new TestBean();
                    testBean.name = "大熊";
                    testBean.age = 12;
                    testBean.sex="女";
                    try {
                        mMyAidl.setTestBean(testBean);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mMyAidl = null;
        }
    };
    private Button button, button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(action);
        bindService(intent, mServiceConnection, BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(mServiceConnection);
    }
}
