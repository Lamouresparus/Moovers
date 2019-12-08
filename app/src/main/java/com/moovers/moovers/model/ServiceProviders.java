package com.moovers.moovers.model;

import com.google.gson.annotations.SerializedName;

public class ServiceProviders {

    @SerializedName("name")
    private String mName;

    @SerializedName("phone_number")
    private String mPhone;

    @SerializedName("address")
    private String mAddress;

    @SerializedName("about")
    private String mAbout;

    @SerializedName("image")
    private String mImageUrl;

    @SerializedName("email")
    private String mEmail;


    public ServiceProviders(String name, String phone, String address, String about, String imageUrl, String email) {
        this.mName = name;
        this.mPhone = phone;
        this.mAddress = address;
        this.mAbout = about;
        this.mImageUrl = imageUrl;
        this.mEmail = email;
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

    public String getmEmail() { return mEmail;
    }
}
