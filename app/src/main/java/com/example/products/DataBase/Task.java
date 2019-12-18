package com.example.products.DataBase;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Task implements Serializable {


    @PrimaryKey(autoGenerate = true)
    private int id_product;

    @ColumnInfo(name = "task")
    private String product_name;

    @ColumnInfo(name = "desc")
    private String price;

    @ColumnInfo(name = "finish_by")
    private String Decrption;

    @ColumnInfo(name = "finished")
    private String photo;

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
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