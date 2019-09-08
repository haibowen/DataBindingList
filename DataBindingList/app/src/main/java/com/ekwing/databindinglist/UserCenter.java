package com.ekwing.databindinglist;

public class UserCenter {
    public String mName;
    public String mImageId;

    public UserCenter(String mName, String mImageId) {
        this.mName = mName;
        this.mImageId = mImageId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageId() {
        return mImageId;
    }

    public void setmImageId(String mImageId) {
        this.mImageId = mImageId;
    }
}
