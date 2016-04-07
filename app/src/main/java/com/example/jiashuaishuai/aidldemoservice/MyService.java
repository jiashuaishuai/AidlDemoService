package com.example.jiashuaishuai.aidldemoservice;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jiashuaishuai on 2016/4/7.
 */
public class MyService extends Service {
    private static final String TAG = "MyService----";
    private List<TestBean> testBeanList = new ArrayList<>();

    @Override
    public void onCreate() {
        super.onCreate();
        TestBean testBean = new TestBean();
        testBean.name = "张亮";
        testBean.age = 18;
        testBean.sex = "男";
        testBeanList.add(testBean);
        Log.i(TAG, "加载数据OnCreate");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind执行");
        return binder;
    }

    /**
     * 实例化AidlStub，通过onBind返回给客户端，实现交互
     */
    private final MyAidl.Stub binder = new MyAidl.Stub() {
        @Override
        public void setTestBean(TestBean testBean) throws RemoteException {
            Log.i(TAG, "setTestBean执行"+testBean.name);
            if (testBean != null)
                testBeanList.add(testBean);
        }

        @Override
        public List<TestBean> getTestBeanList() throws RemoteException {
            Log.i(TAG, "getTestBeanList执行");
            return testBeanList;
        }
    };
}
