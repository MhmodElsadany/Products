package com.example.products.UI;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;


import com.example.products.ViewModel.ClientViewModel;
import com.example.products.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LogIn extends AppCompatActivity {
    @BindView(R.id.usrusr)
    EditText userName;
    @BindView(R.id.pswrdd)
    EditText password;
    @BindView(R.id.chckbox)
    CheckBox mCheckBox;
    ClientViewModel model;
    SharedPreferences sharedPreferences, mSharedPreferencesID;
    SharedPreferences.Editor editor, mEditorID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        restorepreferance();
    }

    public void loginaction(View view) {

        if (registration()) {

            model = ViewModelProviders.of(this).get(ClientViewModel.class);

            model.getResult(userName.getText().toString(), password.getText().toString(), LogIn.this)
                    .observe(this, new Observer<String>() {
                        @Override
                        public void onChanged(@Nullable String s) {
                            Log.i("xasdxsadxas", s);
                            if (s.equals("-1")) {
                                Log.i("error", "ops");
                            } else {
                                Log.i("sssssssssss", s);
                                mSharedPreferencesID = getSharedPreferences("DataID", MODE_PRIVATE);
                                mEditorID = mSharedPreferencesID.edit();
                                mEditorID.putString("id_client", s);
                                mEditorID.commit();

                                startActivity(new Intent(LogIn.this, NavgationDrawer.class));

                            }
                        }


                    });

        }
    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    public void signUp(View view) {
        startActivity(new Intent(LogIn.this, SignUp.class));
    }

    public Boolean registration() {


        String usertxt = userName.getText().toString();
        String passtxt = password.getText().toString();

        if (TextUtils.isEmpty(usertxt)) {
            userName.setError("plz enter your username");

            userName.requestFocus();
            return false;

        }
        if (TextUtils.isEmpty(passtxt)) {
            password.setError("plz enter your password");
            password.requestFocus();
            return false;
        }

        return true;

    }

    public void checkbox() {
        if (mCheckBox.isChecked()) {

            sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
            editor = sharedPreferences.edit();
            editor.putString("username", userName.getText().toString());
            editor.putString("passward", password.getText().toString());
            editor.commit();
        }

    }

    public void restorepreferance() {
        sharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
        userName.setText(sharedPreferences.getString("username", ""));
        password.setText(sharedPreferences.getString("passward", ""));
        mCheckBox.setChecked(true);


    }

    @Override
    protected void onPause() {
        super.onPause();
        checkbox();
    }

}