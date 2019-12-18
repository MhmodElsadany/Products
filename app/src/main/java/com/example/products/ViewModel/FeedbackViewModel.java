package com.example.products.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.products.Data.FeedbackApi;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class FeedbackViewModel extends ViewModel {
    String feedback;
    Context mContext;
    private MutableLiveData<String> mproductMutableLiveData;
    CompositeDisposable compositeDisposable = new CompositeDisposable();


    public LiveData<String> getResult(Context mContext,String feedback) {
        this.feedback=feedback;
        this.mContext = mContext;
        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<String>();
            loadData();

        }
        return mproductMutableLiveData;

    }

    private void loadData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(FeedbackApi.BASE_URL).
                        addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


        FeedbackApi api = retrofit.create(FeedbackApi.class);

        compositeDisposable.add(api.getHeroes(feedback)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {

                    @Override
                    public void accept(String s) throws Exception {
                        Toast.makeText(mContext, "thanks your feedback", Toast.LENGTH_SHORT).show();

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

