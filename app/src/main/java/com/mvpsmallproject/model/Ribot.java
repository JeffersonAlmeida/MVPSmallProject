package com.mvpsmallproject.model;

import com.google.gson.annotations.SerializedName;

public class Ribot {

    @SerializedName("profile")
    Profile profile;

    public Ribot(Profile profile) {
        this.profile = profile;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "Ribot{" +
                "profile=" + profile +
                '}';
    }
}
