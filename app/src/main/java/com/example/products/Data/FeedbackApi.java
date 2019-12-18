package com.example.products.Data;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface FeedbackApi {
    String BASE_URL = "http://gethost81.000webhostapp.com/product_mangment/";

    @FormUrlEncoded
    @POST("feedback.php")
     Observable<String> getHeroes(@Field("feedback") String feedback);
}

