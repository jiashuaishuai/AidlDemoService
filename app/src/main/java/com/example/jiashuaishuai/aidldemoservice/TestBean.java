package com.example.jiashuaishuai.aidldemoservice;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by jiashuaishuai on 2016/4/7.
 */
public class TestBean implements Parcelable {
    public String name;
    public String sex;
    public int age;
public TestBean(){}
    protected TestBean(Parcel in) {
        name = in.readString();
        sex = in.readString();
        age = in.readInt();
    }

    public static final Creator<TestBean> CREATOR = new Creator<TestBean>() {
        @Override
        public TestBean createFromParcel(Parcel in) {
            return new TestBean(in);
        }

        @Override
        public TestBean[] newArray(int size) {
            return new TestBean[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(sex);
        dest.writeInt(age);
    }
}
