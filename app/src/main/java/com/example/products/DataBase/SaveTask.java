package com.example.products.DataBase;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class SaveTask extends AsyncTask<Void, Void, Void> {
    Task task;
    Context mContext;

    public SaveTask(Task task, Context mContext) {
        this.task = task;
        this.mContext = mContext;

    }

    @Override
    protected Void doInBackground(Void... voids) {

        //creating a task

        Log.i("mmmmnnnvv",task.getDecrption());
        //adding to database
        DatabaseClient.getInstance(mContext).getAppDatabase()
                .taskDao()
                .insert(task);
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(mContext.getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
    }
}
