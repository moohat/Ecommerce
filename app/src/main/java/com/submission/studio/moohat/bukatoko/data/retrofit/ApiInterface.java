package com.submission.studio.moohat.bukatoko.data.retrofit;

import com.submission.studio.moohat.bukatoko.data.model.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("products")
    Call<Product> getProducts();
}
