package com.example.products.ViewModel;

import android.content.Context;
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

public class UserViewModel extends ViewModel {
    Context mContext;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LiveData<String> getResult(
            Context mContext) {
        this.mContext = mContext;
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<String>();
            loadClient();

        }
        return mClientsMutableLiveData;

    }

    private void loadClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiUser.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiUser api = retrofit.create(ApiUser.class);

        compositeDisposable.add(api.getHeroes("5")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<ArrayList<ClientsModel>>() {

                    @Override
                    public void accept(ArrayList<ClientsModel> s) throws Exception {

                        Toast.makeText(mContext, s.get(0).getUsername(), Toast.LENGTH_SHORT).show();
                        mClientsMutableLiveData.setValue(s.get(0).getUsername());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext, "plz check your internet", Toast.LENGTH_SHORT).show();
                        mClientsMutableLiveData = null;

                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));
    }
}