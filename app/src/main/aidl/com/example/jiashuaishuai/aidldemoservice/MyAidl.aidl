// MyAidl.aidl
package com.example.jiashuaishuai.aidldemoservice;
import com.example.jiashuaishuai.aidldemoservice.TestBean;
// Declare any non-default types here with import statements


interface MyAidl {
   void setTestBean(in TestBean testBean);
   List<TestBean> getTestBeanList();
}
