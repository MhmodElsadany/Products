package com.example.products.Model;

import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class ProductModel {
    String id_product, product_name, price, Decrption, photo;

    public ProductModel() {
    }

    public ProductModel(String id_product, String product_name, String price, String decrption, String photo) {
        this.id_product = id_product;
        this.product_name = product_name;
        this.price = price;
        Decrption = decrption;
        this.photo = photo;
    }


    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDecrption() {
        return Decrption;
    }

    public void setDecrption(String decrption) {
        Decrption = decrption;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }


}