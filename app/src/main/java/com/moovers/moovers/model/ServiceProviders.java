package com.moovers.moovers.model;

public class ServiceProviders {

    private String mName;
    private String mPhone;
    private String mAddress;
    private String mAbout;
    private String mImageUrl;


    public ServiceProviders(String name, String phone, String address, String about, String imageUrl) {
        this.mName = name;
        this.mPhone = phone;
        this.mAddress = address;
        this.mAbout = about;
        this.mImageUrl = imageUrl;
    }

    public String getmName() {
        return mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmAbout() {
        return mAbout;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }
}
