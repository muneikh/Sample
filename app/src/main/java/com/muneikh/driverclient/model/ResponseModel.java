package com.muneikh.driverclient.model;

public class ResponseModel {
    public Data data;

    public static class Data {
        public String id;
        public String created_at;
        public String updated_at;
        public String type;
        public String phone_number;
        public String token;

        public Data(String id, String created_at, String updated_at, String type, String phone_number, String token) {
            this.id = id;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.type = type;
            this.phone_number = phone_number;
            this.token = token;
        }

        @Override
        public String toString() {
            return "Data{" +
                    "id='" + id + '\'' +
                    ", created_at='" + created_at + '\'' +
                    ", updated_at='" + updated_at + '\'' +
                    ", type='" + type + '\'' +
                    ", phone_number='" + phone_number + '\'' +
                    ", token='" + token + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "data=" + data +
                '}';
    }

    public static ResponseModel createSample() {
        ResponseModel responseModel = new ResponseModel();
        responseModel.data = new Data("dvr_c39544fc-b441-4917-b35b-fddc3979d1c9", "2017-04-07T07:52:54Z", "2017-04-07T07:52:54Z", "driver_phone_verification_request", "+4917612345678", "");
        return responseModel;
    }
}


