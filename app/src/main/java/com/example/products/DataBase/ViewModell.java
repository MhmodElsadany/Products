package com.example.products.DataBase;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;


public class ViewModell extends ViewModel {
    private MutableLiveData<List<Task>> mproductMutableLiveData;
    Context mContext;

    public LiveData<List<Task>> getResult(Context mContext) {
        this.mContext = mContext;
        if (mproductMutableLiveData == null) {
            mproductMutableLiveData = new MutableLiveData<List<Task>>();
            loadData();

        }
        return mproductMutableLiveData;

    }

    private void loadData() {

        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(mContext.getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                if (tasks.size() != 0) {
                    mproductMutableLiveData.setValue(tasks);
                }
                else {
                    Toast.makeText(mContext,"no items added before ",Toast.LENGTH_LONG).show();
                }
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }


}

