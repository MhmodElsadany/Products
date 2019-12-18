package com.example.products.Data;

import com.example.products.Model.ClientsModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiUser {
    String BASE_URL = "http://gethost81.000webhostapp.com/product_mangment/";

    @FormUrlEncoded
    @POST("userName.php")
    Observable<ArrayList<ClientsModel>> getHeroes(@Field("id_user") String id_user);
}

