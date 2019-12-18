package com.example.products.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Intent;

import com.example.products.Model.ProductModel;

public class DetailViewModel extends ViewModel {
    Intent intent;
    private MutableLiveData<ProductModel> mproductMutableLiveData;

    public LiveData<ProductModel> getResult(Intent intent) {
        this.intent = intent;
        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<ProductModel>();
            loadData();

        }
        return mproductMutableLiveData;

    }

    private void loadData() {

        mproductMutableLiveData.setValue(new ProductModel(intent.getStringExtra("id"), intent.getStringExtra("name")
                , intent.getStringExtra("price"), intent.getStringExtra("descrption"), intent.getStringExtra("image")));

    }
}

