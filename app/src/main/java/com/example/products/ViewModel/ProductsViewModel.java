package com.example.products.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.products.Model.ProductModel;
import com.example.products.Data.ProdcutsAPI;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsViewModel extends ViewModel {

    Context mContext;
    private MutableLiveData<ArrayList<ProductModel>> mproductMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LiveData<ArrayList<ProductModel>> getResult(Context mContext) {
        this.mContext = mContext;
        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<ArrayList<ProductModel>>();
            loadData();

        }
        return mproductMutableLiveData;

    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ProdcutsAPI.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ProdcutsAPI api = retrofit.create(ProdcutsAPI.class);

        compositeDisposable.add(api.getHeroes()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<ProductModel>>() {

                    @Override
                    public void accept(ArrayList<ProductModel> productModels) throws Exception {

                        mproductMutableLiveData.setValue(productModels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext, "plz check your internet", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));

    }
}

