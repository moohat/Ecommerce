package com.submission.studio.moohat.bukatoko.data.model;

import com.google.gson.annotations.SerializedName;

public class User {

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

        @SerializedName("id")
        private int id;

        @SerializedName("name")
        private String name;

        @SerializedName("email")
        private  String email;

        @SerializedName("api_token")
        private String api_token;

        @SerializedName("is_admin")
        private String is_admin;


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getApi_token() {
            return api_token;
        }

        public void setApi_token(String api_token) {
            this.api_token = api_token;
        }

        public String getIs_admin() {
            return is_admin;
        }

        public void setIs_admin(String is_admin) {
            this.is_admin = is_admin;
        }
    }




//      'id'    => $this->id,
//            'name'  => $this->name,
//            'email' => $this->email,
//            'username' => $this->username,
//            // 'date'  => $this->created_at->diffForHumans(),
//            'api_token' => $this->api_token,
//            'is_admin' => $this->is_admin
}
