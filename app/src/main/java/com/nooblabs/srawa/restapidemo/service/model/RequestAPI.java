package com.nooblabs.srawa.restapidemo.service.model;

import com.google.gson.annotations.SerializedName;

public class RequestAPI {


    @SerializedName("first_name")
    public String firstName;

    @SerializedName("last_name")
    public String lastName;

    @SerializedName("phone")
    public String phoneNumber;

    @SerializedName("address")
    public String address;

    @SerializedName("restau_name")
    public String restaurantName;

    @SerializedName("request_by")
    public int requestBy;

    public RequestAPI(String firstName, String lastName, String phoneNumber, String address, String restaurantName, int requestBy) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.restaurantName = restaurantName;
        this.requestBy = requestBy;
    }
}
