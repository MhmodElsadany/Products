package com.example.products.Adapter;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.products.UI.DetailProduct;
import com.example.products.Model.ProductModel;
import com.example.products.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolderProduct> {
    Context mContext;
    ArrayList<ProductModel> productModels;
    Typeface typefaceuser;
    public CustomAdapter() {
    }

    public CustomAdapter(Context mContext, ArrayList<ProductModel> productModels) {
        this.mContext = mContext;
        this.productModels = productModels;
    }

    @NonNull
    @Override
    public ViewHolderProduct onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_row, viewGroup, false);
        return new ViewHolderProduct(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderProduct viewHolderProduct, int i) {
        Log.i("mmmm", productModels.get(i).getProduct_name());

        viewHolderProduct.title.setText(productModels.get(i).getProduct_name());

        Glide.with(mContext)
                .load("http://gethost81.000webhostapp.com/product_mangment/cars/"+productModels.get(i).getPhoto()+".jpg")
                .into(viewHolderProduct.img);

    }

    @Override
    public int getItemCount() {
        return productModels.size();
    }

    public class ViewHolderProduct extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView title;
        ImageView img;

        public ViewHolderProduct(@NonNull View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.titl);
            img = (ImageView) itemView.findViewById(R.id.img);
            mCardView=(CardView)itemView.findViewById(R.id.placeCard);
            mCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent mIntent=new Intent(mContext, DetailProduct.class);
                    mIntent.putExtra("id",productModels.get(getAdapterPosition()).getId_product());
                    mIntent.putExtra("name",productModels.get(getAdapterPosition()).getProduct_name());
                    mIntent.putExtra("price",productModels.get(getAdapterPosition()).getPrice());
                    mIntent.putExtra("descrption",productModels.get(getAdapterPosition()).getDecrption());
                    mIntent.putExtra("image",productModels.get(getAdapterPosition()).getPhoto());
                mContext.startActivity(mIntent);

                }
            });
            typefaceuser = Typeface.createFromAsset(mContext.getAssets(), "fonts/Xerox Serif Wide Bold Italic.ttf");
            title.setTypeface(typefaceuser);

        }

    }
}
