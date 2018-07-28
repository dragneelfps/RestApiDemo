package com.nooblabs.srawa.restapidemo.service.model;


import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ResponseAPI implements Serializable {

    public String success = "";
    public String error = "";

    public ArrayList<ResponseMessage> message;


    @Override
    public String toString() {
        return "ResponseAPI{" +
                "success='" + success + '\'' +
                ", error='" + error + '\'' +
                ", message=" + message +
                '}';
    }

    public class ResponseMessage implements Serializable {
        @SerializedName("Status")
        public String status;

        @SerializedName("Message")
        public String message;

        public String phone;

        @Override
        public String toString() {
            return "ResponseMessage{" +
                    "Status='" + status + '\'' +
                    ", Message='" + message + '\'' +
                    ", phone='" + phone + '\'' +
                    '}';
        }
    }
}
