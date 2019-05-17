package com.nextgeni.task.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.nextgeni.task.R;
import com.nextgeni.task.entities.ImgName;
import com.nextgeni.task.entities.Products;
import com.nextgeni.task.utils.App;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>{


    ArrayList<Products> list;
    Context context;
    ArrayList<ImgName> imgList;
    boolean checked;

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      //  context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view);
    }

    public ProductAdapter(List<Products> list, Context context) {
        this.list = (ArrayList<Products>) list;
        this.context = context;


    }

    @Override
    public void onBindViewHolder(@NonNull final ProductViewHolder holder, int position) {

        holder.productName.setText(list.get(position).getProductName());
        holder.ownerName.setText(list.get(position).getProductOwner());
        holder.productPrice.setText(list.get(position).getPrice());
        holder.ivHeart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        holder.ivHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!checked){
                    holder.ivHeart.setImageResource(R.drawable.ic_favorite_black_24dp);
                    checked= true;
                }else{
                    holder.ivHeart.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    checked = false;
                }

            }
        });

        Glide.with(context.getApplicationContext()).load(list.get(position).getImgNames().get(0).getImgName())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        public TextView productName;
        public TextView ownerName;
        public TextView productPrice;
        public ImageView imageView;
        public ImageView ivHeart;

        public ProductViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.product_name);
            ownerName = itemView.findViewById(R.id.product_name_2);
            productPrice = itemView.findViewById(R.id.price);
            imageView = itemView.findViewById(R.id.iv);
            ivHeart = itemView.findViewById(R.id.iv_heart);
        }
    }
}
