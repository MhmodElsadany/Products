package com.example.products.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.products.Data.ApiSignUp;
import com.example.products.Model.ClientsModel;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpViewModel extends ViewModel {
    String username, password, fullname, mbphone, address;
    Context mContext;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LiveData<String> getResult(ClientsModel mClients, Context mContext) {
        this.username = mClients.getUsername();
        this.password = mClients.getPassword();
        this.address = mClients.getAddress();
        this.mbphone = mClients.getPhone();
        this.fullname = mClients.getFullName();
        this.mContext = mContext;
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<String>();
            loadData();

        }
        return mClientsMutableLiveData;

    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiSignUp.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        ApiSignUp api = retrofit.create(ApiSignUp.class);

        compositeDisposable.add(api.getHeroes(username, password, fullname, address, mbphone)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        Log.i("msg", s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext,"plz check your internet",Toast.LENGTH_SHORT).show();
                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            Log.i("llllk", "mahmoudelsadan ..");
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));

    }
}

