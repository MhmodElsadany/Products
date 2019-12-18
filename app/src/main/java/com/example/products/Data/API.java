package com.example.products.Data;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface API {
    String BASE_URL = "http://gethost81.000webhostapp.com/product_mangment/";

    @FormUrlEncoded
    @POST("registeraion_user.php")
    Observable<String> getHeroes(@Field("user_name") String username,
                                 @Field("user_pass") String password
    );
}
