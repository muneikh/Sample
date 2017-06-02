package com.muneikh.driverclient.model;


import com.google.gson.annotations.SerializedName;

public class RequestTokenModel {
    private Data data;

    public RequestTokenModel(String phoneNumber, String pinCode) {
        this.data = new Data(phoneNumber, pinCode);
    }

    class Data {
        @SerializedName("phone_number")
        private String phoneNumber;

        private String code;

        public Data(String phoneNumber, String pinCode) {
            this.phoneNumber = phoneNumber;
            this.code = pinCode;
        }
    }
}
