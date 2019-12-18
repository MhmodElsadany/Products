package com.example.products.DataBase;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.products.UI.DetailProduct;

import java.util.List;

public class GetTasks extends AsyncTask<Void, Void, Task> {
    Context mContext;
    String name;
    public boolean flag = false;
    Task task;

    public GetTasks(Context mContext, String name, Task task) {
        this.mContext = mContext;
        this.name = name;
        this.task = task;
    }


    @Override
    public Task doInBackground(Void... voids) {
        Task taskList = DatabaseClient
                .getInstance(mContext.getApplicationContext())
                .getAppDatabase()
                .taskDao()
                .getfilm(name);
        return taskList;
    }

    @Override
    protected void onPostExecute(Task tasks) {
        super.onPostExecute(tasks);
        if (tasks != null) {
            Toast.makeText(mContext, "this product added before", Toast.LENGTH_SHORT).show();
        } else {
            SaveTask st = new SaveTask(task, mContext);
            st.execute();

        }

    }


}