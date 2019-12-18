package com.example.products.UI;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.products.DataBase.GetTasks;
import com.example.products.DataBase.Task;
import com.example.products.Model.ProductModel;
import com.example.products.R;
import com.example.products.ViewModel.DetailViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailProduct extends AppCompatActivity {
    DetailViewModel model;

    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.descrption)
    TextView descrption;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.titl)
    TextView title;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btnlive)
    Button livechat;

    String id_product;
    Task mTask = new Task();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        ButterKnife.bind(this);
        toolbar.setTitle("Detail Product");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = ViewModelProviders.of(this).get(DetailViewModel.class);
        model.getResult(getIntent()).observe(this, new Observer<ProductModel>() {
            @Override
            public void onChanged(@Nullable ProductModel s) {
                id_product = s.getId_product();
                Log.i("nmmnnmmnmnmnmgfd", s.getDecrption());
                title.setText(s.getProduct_name());
                descrption.setText(s.getDecrption());
                price.setText(s.getPrice());
                Glide.with(DetailProduct.this)
                        .load("http://gethost81.000webhostapp.com/product_mangment/cars/" + s.getPhoto() + ".jpg")
                        .into(img);
                mTask.setPhoto(s.getPhoto());
                mTask.setPrice(s.getPrice());
                mTask.setDecrption(s.getDecrption());
                mTask.setProduct_name(s.getProduct_name());
            }


        });
        livechat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mSharedPreferences = getSharedPreferences("DataID", MODE_PRIVATE);
                if (mSharedPreferences.getString("id_client", "").equals("")) {
                    Toast.makeText(DetailProduct.this, "please sign in to contact with us", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(DetailProduct.this, LiveChat.class);
                    intent.putExtra("id_clent", mSharedPreferences.getString("id_client", ""));
                    intent.putExtra("id_product", id_product);
                    startActivity(intent);
                }


            }


        });

    }

    public void save(View view) {
        GetTasks gt = new GetTasks(DetailProduct.this, mTask.getProduct_name(), mTask);
        gt.execute();
        /*Toast.makeText(DetailProduct.this,gt.flag+"" , Toast.LENGTH_SHORT).show();
        Log.i("ooooooop", gt.flag+"");

        if (gt.!=null) {
            Toast.makeText(DetailProduct.this, "this product added before", Toast.LENGTH_SHORT).show();

        } else {
            Log.i("ooooooop", mTask.getDecrption());
            Toast.makeText(DetailProduct.this, mTask.getDecrption(), Toast.LENGTH_SHORT).show();
            SaveTask st = new SaveTask(mTask, DetailProduct.this);
            st.execute();
        }*/
    }
}
