package com.submission.studio.moohat.bukatoko.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.submission.studio.moohat.bukatoko.R;
import com.submission.studio.moohat.bukatoko.activity.DetailActivity;
import com.submission.studio.moohat.bukatoko.activity.MainActivity;
import com.submission.studio.moohat.bukatoko.data.model.Product;
import com.submission.studio.moohat.bukatoko.utils.Converter;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    private List<Product.Data> products;
    Context context;

    public ProductAdapter(Context context, List<Product.Data> data ) {
        this.context = context;
        this.products = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_main, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.txtName.setText(products.get(i).getProduct());
//        int price = products.get(i).getPrice();

        viewHolder.txtPrice.setText("IDR "+Converter.rupiah(products.get(i).getPrice()));

        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(R.drawable.image_icon_png_thumbnail)
                .error(R.drawable.image_icon_png_notfound)
                .priority(Priority.HIGH);
//
        Glide.with(context)
                .load(products.get(i).getImage())
                .apply(options)
                .into(viewHolder.imgProd);

        viewHolder.imgProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("PRODUCT_ID", products.get(i).getId());
                intent.putExtra("PRODUCT_IMAGE", products.get(i).getImage());
                context.startActivity(intent);
            }
        });

    }


    //ini untuk mereturn data agar tampil dan menampilkan size() jumlah data yang ada
    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgProd;
        TextView txtPrice, txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgProd = itemView.findViewById(R.id.imgProd);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}
