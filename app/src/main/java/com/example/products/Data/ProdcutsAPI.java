package com.example.products.Data;

import com.example.products.Model.ProductModel;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.http.POST;

public interface ProdcutsAPI {
    String BASE_URL = "https://gethost81.000webhostapp.com/product_mangment/";

    @POST("selectcar.php")
    Observable<ArrayList<ProductModel>> getHeroes();
}
