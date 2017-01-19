package com.mvpsmallproject.model;

import com.google.gson.annotations.SerializedName;

public class Name {

    @SerializedName("first")
    private String first;

    @SerializedName("last")
    private String last;

    public Name(String first) {
        this.first = first;
    }

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }
}
