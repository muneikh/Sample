package com.muneikh.driverclient.model;

import com.google.gson.annotations.SerializedName;

public class PhoneRequestModel {
    public PhoneRequestModel(String phoneNumber) {
        data = new Data(phoneNumber);
    }

    private Data data;

    class Data {
        @SerializedName("phone_number")
        String phoneNumber;

        public Data(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
    }

    @Override
    public String toString() {
        return "PhoneRequestModel{" +
                "data=" + data +
                '}';
    }
}


