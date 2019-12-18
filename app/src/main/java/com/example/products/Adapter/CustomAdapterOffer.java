package com.example.products.Adapter;


import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.products.UI.DetailProduct;
import com.example.products.Model.ProductModel;
import com.example.products.R;

import java.util.ArrayList;

public class CustomAdapterOffer extends RecyclerView.Adapter<CustomAdapterOffer.ViewHolderProduct> {
    Context mContext;
    ArrayList<ProductModel> productModels;

    public CustomAdapterOffer() {
    }

    public CustomAdapterOffer(Context mContext, ArrayList<ProductModel> productModels) {
        this.mContext = mContext;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.offer_row, viewGroup, false);
        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct viewHolderProduct, int i) {
        Glide.with(mContext)
                .load("http://gethost81.000webhostapp.com/product_mangment/cars/" + productModels.get(i).getPhoto() + ".jpg")
                .into(viewHolderProduct.img);

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        CardView mCardView;
        ImageView img;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.img);
            mCardView = (CardView) itemView.findViewById(R.id.placeCard);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent = new Intent(mContext, DetailProduct.class);
                    mIntent.putExtra("id", productModels.get(getAdapterPosition()).getId_product());
                    mIntent.putExtra("name", productModels.get(getAdapterPosition()).getProduct_name());
                    mIntent.putExtra("price", productModels.get(getAdapterPosition()).getPrice());
                    mIntent.putExtra("descrption", productModels.get(getAdapterPosition()).getDecrption());
                    mIntent.putExtra("image", productModels.get(getAdapterPosition()).getPhoto());
                    mContext.startActivity(mIntent);

                }
            });
        }

    }
}
