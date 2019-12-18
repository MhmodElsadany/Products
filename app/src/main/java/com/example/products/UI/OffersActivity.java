package com.example.products.UI;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.products.Adapter.CustomAdapterOffer;
import com.example.products.Model.ProductModel;
import com.example.products.R;
import com.example.products.ViewModel.ViewModelOffer;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OffersActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    ViewModelOffer model;
    CustomAdapterOffer mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offers);
        ButterKnife.bind(this);
        toolbar.setTitle("Saved Product");

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        model = ViewModelProviders.of(this).get(ViewModelOffer.class);
        model.getResult(OffersActivity.this).observe(this, new Observer<ArrayList<ProductModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ProductModel> s) {
                mCustomAdapter = new CustomAdapterOffer(OffersActivity.this, s);
                recyclerView.setAdapter(mCustomAdapter);
            }


        });

    }
}
