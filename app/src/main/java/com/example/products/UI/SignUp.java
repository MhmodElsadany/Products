package com.example.products.UI;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.products.Model.ClientsModel;
import com.example.products.R;
import com.example.products.ViewModel.SignUpViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SignUp extends AppCompatActivity {
    @BindView(R.id.fullname)
    EditText fullname;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.username)
    EditText userName;
    @BindView(R.id.address)
    EditText address;
    @BindView(R.id.mobphone)
    EditText mobphone;
    SignUpViewModel model;
    ClientsModel clients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signout);
        ButterKnife.bind(this);


    }

    public void accountDone(View view) {
        clients = new ClientsModel(userName.getText().toString(), password.getText().toString()
                , fullname.getText().toString(), address.getText().toString(), mobphone.getText().toString());
        if (check_element()) {
            model = ViewModelProviders.of(this).get(SignUpViewModel.class);
            model.getResult(clients, SignUp.this).observe(this, new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    Toast.makeText(SignUp.this, s + "pppppppp", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void toLogIn(View view) {
        startActivity(new Intent(SignUp.this, LogIn.class));
    }

    public boolean check_element() {
        boolean result = true;
        String getfullname = fullname.getText().toString();
        String getuserName = userName.getText().toString();
        String getpassword = password.getText().toString();
        String getmobphone = mobphone.getText().toString();
        String getaddress = address.getText().toString();

        if (getfullname.isEmpty()) {
            fullname.setError("please enter your Name");
            fullname.requestFocus();
            result = false;
        }
        if (getuserName.isEmpty()) {
            userName.setError("plz enter your name");
            userName.requestFocus();
            result = false;
        }
        if (getaddress.isEmpty()) {
            address.setError("please enter your address");
            address.requestFocus();
            result = false;
        }

        if (getmobphone.isEmpty()) {
            mobphone.setError("plphonez enter your ");
            mobphone.requestFocus();
            result = false;
        }
        if (getpassword.isEmpty() || getpassword.length() < 9) {
            password.setError("plz enter your password");
            password.requestFocus();
            result = false;
        }
        return result;
    }
}
