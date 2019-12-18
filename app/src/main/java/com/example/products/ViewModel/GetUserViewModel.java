package com.example.products.ViewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.products.Model.ClientsModel;
import com.example.products.Data.ApiUser;

import java.util.ArrayList;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetUserViewModel extends ViewModel {

    Context mContext;
    private MutableLiveData<ClientsModel> mproductMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String id_client;
    String id_product;
    Intent intent;
    ClientsModel mClients = new ClientsModel();

    public LiveData<ClientsModel> getResult(Context mContext, Intent intent) {
        this.intent = intent;
        id_client = intent.getStringExtra("id_clent");
        id_product = intent.getStringExtra("id_product");
        this.mContext = mContext;
        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<ClientsModel>();

            loadData();

        }
        return mproductMutableLiveData;

    }

    private void loadData() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUser.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        Toast.makeText(mContext, id_client, Toast.LENGTH_SHORT).show();


        ApiUser api = retrofit.create(ApiUser.class);

        compositeDisposable.add(api.getHeroes(id_client)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<ClientsModel>>() {

                    @Override
                    public void accept(ArrayList<ClientsModel> s) throws Exception {
                        Toast.makeText(mContext, "s", Toast.LENGTH_SHORT).show();

                        mClients.setId(id_client);
                        mClients.setPhone(id_product);
                        Toast.makeText(mContext, id_product, Toast.LENGTH_SHORT).show();
                        mClients.setUsername(s.get(0).getUsername());
                        mproductMutableLiveData.setValue(mClients);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext, "plz check your internet", Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            Log.i("llllk", "mahmoudelsadan ..");
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));

    }
}

