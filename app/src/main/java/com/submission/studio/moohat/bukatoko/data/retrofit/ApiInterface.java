package com.submission.studio.moohat.bukatoko.data.retrofit;

import com.submission.studio.moohat.bukatoko.data.model.Detail;
import com.submission.studio.moohat.bukatoko.data.model.Product;
import com.submission.studio.moohat.bukatoko.data.model.User;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApiInterface {

    //menampilkan produk
    @GET("products")
    Call<Product> getProducts();

    @GET("product/{id}")
    Call<Detail> getDetail(@Path("id") int id);


    //login
    @FormUrlEncoded
    @POST("auth/login")
    Call<User> authLogin (
            @Field("email") String email,
            @Field("password") String password
    );


    //Regiseter
    @FormUrlEncoded
    @POST("auth/register")
    Call<User> authRegister (
            @Field("name") String name,
            @Field("email") String email,
            @Field("password") String password
    );

}
