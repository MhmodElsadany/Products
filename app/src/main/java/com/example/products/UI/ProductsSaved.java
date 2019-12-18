package com.example.products.UI;

import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.products.Adapter.CustomAdapter;
import com.example.products.DataBase.Task;
import com.example.products.DataBase.ViewModell;
import com.example.products.Model.ProductModel;
import com.example.products.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductsSaved extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    ViewModell model;
    CustomAdapter mCustomAdapter;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    ArrayList<ProductModel> mTasks = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.poducts);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        toolbar.setTitle("Saved Product");

        setSupportActionBar(toolbar);

        getSupportActionBar().setHomeButtonEnabled(true);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        model = ViewModelProviders.of(this).get(ViewModell.class);
        model.getResult(ProductsSaved.this).observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(@Nullable List<Task> s) {
                if (s.size() != 0) {
                    for (int i = 0; i < s.size(); i++) {
                        mTasks.add(new ProductModel(s.get(i).getId_product() + "", s.get(i).getProduct_name(),
                                s.get(i).getPrice(), s.get(i).getDecrption(), s.get(i).getPhoto()));
                    }
                    Log.i("llloooooooollll", s.get(0).getProduct_name());
                    mCustomAdapter = new CustomAdapter(ProductsSaved.this, mTasks);
                    recyclerView.setAdapter(mCustomAdapter);
                }
            }


        });


    }

}
