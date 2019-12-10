package com.moovers.moovers.model;

public class ServiceCategories {

    private int mImageResourceId;
    private String mCategoryName;

    public ServiceCategories(String categoryName, int imageResourceId){

        mImageResourceId = imageResourceId;
        mCategoryName = categoryName;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public String getmCategoryName() {
        return mCategoryName;
    }
}
