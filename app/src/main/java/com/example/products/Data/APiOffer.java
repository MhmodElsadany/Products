package com.example.products.Data;

import com.example.products.Model.ProductModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface APiOffer {
    String BASE_URL = "http://gethost81.000webhostapp.com/product_mangment/";

    @POST("offer.php")
    Observable<ArrayList<ProductModel>> getHeroes();
}
