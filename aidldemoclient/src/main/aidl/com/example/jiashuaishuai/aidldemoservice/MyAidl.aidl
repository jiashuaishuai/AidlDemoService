// MyAidl.aidl
package com.example.jiashuaishuai.aidldemoservice;
import com.example.jiashuaishuai.aidldemoservice.TestBean;
// Declare any non-default types here with import statements


interface MyAidl {
   void setTestBean(in TestBean testBean);//设置testBen,给服务器
   List<TestBean> getTestBeanList();//从服务器端获取testBean的list
}
