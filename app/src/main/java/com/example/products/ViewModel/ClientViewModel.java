package com.example.products.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.products.Data.API;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientViewModel extends ViewModel {
    String username, password;
    Context mContext;
    private MutableLiveData<String> mClientsMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LiveData<String> getResult(String username, String password, Context mContext) {
        this.username = username;
        this.password = password;
        this.mContext = mContext;
        if (mClientsMutableLiveData == null) {
            mClientsMutableLiveData = new MutableLiveData<String>();
            loadClient();

        }
        return mClientsMutableLiveData;

    }

    private void loadClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        API api = retrofit.create(API.class);

        compositeDisposable.add(api.getHeroes(username, password)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {

                        if (s.equals("-1")) {
                            Toast.makeText(mContext, "user name or password is invaild", Toast.LENGTH_SHORT).show();
                            mClientsMutableLiveData.setValue(s);
                        } else {

                            Log.i("id_client", s);
                            Toast.makeText(mContext, "you have already login", Toast.LENGTH_SHORT).show();
                            mClientsMutableLiveData.setValue(s);
                        }
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mContext, "plz check your internet", Toast.LENGTH_SHORT).show();
                        mClientsMutableLiveData=null;

                        throwable.printStackTrace();

                        if (throwable instanceof HttpException) {
                            int responseCode = ((HttpException) throwable).code();
                        }

                    }
                }));
/*        Call<String> call = api.getHeroes(username, password);


        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("oooooo", "llllll");

                Log.i("oooooo", response.body());

                mClientsMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("oooooo", t.getMessage()
                );

            }
        });*/

    }
}
