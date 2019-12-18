package com.example.products.UI;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.util.Log;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.widget.Toast;

import com.example.products.Adapter.CustomAdapter;
import com.example.products.Model.ProductModel;
import com.example.products.ViewModel.ProductsViewModel;
import com.example.products.R;
import com.example.products.Dialogs.ViewDialogAbout;
import com.example.products.Dialogs.ViewDialogFeedBack;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NavgationDrawer extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    SharedPreferences mSharedPreferences;
    SharedPreferences.Editor mEditor;
    MenuItem login;
    ProductsViewModel model;
    CustomAdapter mCustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navgation_drawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        Menu menu = navigationView.getMenu();
        // find MenuItem you want to change
        login = menu.findItem(R.id.login);
        mSharedPreferences = getSharedPreferences("DataID", MODE_PRIVATE);
        Log.i(";;,,,", mSharedPreferences.getString("id_client", ""));

        if (mSharedPreferences.getString("id_client", "").equals("")) {
            login.setTitle("login");

        } else {
            login.setTitle("logout");

        }

        // set new title to the MenuItem

        model = ViewModelProviders.of(this).get(ProductsViewModel.class);
        model.getResult(NavgationDrawer.this).observe(this, new Observer<ArrayList<ProductModel>>() {
            @Override
            public void onChanged(@Nullable ArrayList<ProductModel> s) {
                mCustomAdapter = new CustomAdapter(NavgationDrawer.this, s);
                recyclerView.setAdapter(mCustomAdapter);
            }


        });

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
        } else if (id == R.id.fav) {
            startActivity(new Intent(NavgationDrawer.this, ProductsSaved.class));

        } else if (id == R.id.offer) {
            startActivity(new Intent(NavgationDrawer.this, OffersActivity.class));

        } else if (id == R.id.location) {
            startActivity(new Intent(NavgationDrawer.this, Mylocation.class));

        } else if (id == R.id.about) {
            ViewDialogAbout alert = new ViewDialogAbout();
            alert.showDialog(this);

        } else if (id == R.id.feedback) {
            ViewDialogFeedBack alert = new ViewDialogFeedBack();
            alert.showDialog(this, "");


        } else if (id == R.id.contact) {
            mSharedPreferences = getSharedPreferences("DataID", MODE_PRIVATE);
            Log.i(";;,,,", mSharedPreferences.getString("id_client", ""));

            if (mSharedPreferences.getString("id_client", "").equals("")) {
                Toast.makeText(NavgationDrawer.this, "please sign in to contact with us", Toast.LENGTH_LONG).show();
            } else {
                Intent intent = new Intent(NavgationDrawer.this, LiveChat.class);
                intent.putExtra("id_clent", mSharedPreferences.getString("id_client", ""));
                startActivity(intent);
            }


        } else if (id == R.id.login) {
            Log.i("ll,,,,", login.getTitle().toString());
            if (login.getTitle().toString().equals("logout")) {
                Toast.makeText(NavgationDrawer.this, "Now Already Logout", Toast.LENGTH_SHORT).show();
                mSharedPreferences = getSharedPreferences("DataID", MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();
                mEditor.putString("id_client", "");
                mEditor.commit();
                mSharedPreferences = getSharedPreferences("Data", MODE_PRIVATE);
                mEditor = mSharedPreferences.edit();
                mEditor.putString("username", "");
                mEditor.putString("passward", "");
                mEditor.commit();
                login.setTitle("login");


            } else {
                startActivity(new Intent(NavgationDrawer.this, LogIn.class));
            }
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}