package com.mvpsmallproject.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Profile {

    @SerializedName("id")
    private String id;

    @SerializedName("email")
    private String email;

    @SerializedName("avatar")
    private String avatar;

    @SerializedName("dateOfBirth")
    private String dateOfBirth;

    @SerializedName("hexColor")
    private String hexColor;

    @SerializedName("bio")
    private String bio;

    @SerializedName("isActive")
    private boolean isActive;

    @SerializedName("name")
    @Expose
    private Name name;

    public Profile(String id, String email, Name name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getHexColor() {
        return hexColor;
    }

    public void setHexColor(String hexColor) {
        this.hexColor = hexColor;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", avatar='" + avatar + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", hexColor='" + hexColor + '\'' +
                ", bio='" + bio + '\'' +
                ", isActive=" + isActive +
                ", name=" + name +
                '}';
    }
}
