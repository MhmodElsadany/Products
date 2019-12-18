package com.example.products.Data;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiSignUp {

    String BASE_URL = "http://gethost81.000webhostapp.com/product_mangment/";

    @FormUrlEncoded
    @POST("newuser.php")
    Observable<String> getHeroes(@Field("user") String username,
                                 @Field("user_pass") String password
                                 , @Field("fullname") String fullname
                                 , @Field("address") String address
                                 , @Field("phone") String phone
    );

}
