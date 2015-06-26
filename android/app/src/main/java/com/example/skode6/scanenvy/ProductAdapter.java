package com.example.skode6.scanenvy;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.skode6.scanenvy.backend.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    private Context context;
    private List<Product> productList;

    public ProductAdapter(Context context, List<Product> productList) {
        this.context = context;
        this.productList = productList;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rowView = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_layout, parent, false);

        //rowView.setOnClickListener(listener);
        return new ProductViewHolder(rowView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, final int position) {
        final Product product = productList.get(position);
        holder.name.setText(product.getName());
        holder.upc.setText(product.getUpc());
        holder.manuf.setText(product.getManufact());
        holder.rType.setText(""+product.getRType());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, product.getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public void add(Product p) {
        productList.add(p);
        notifyDataSetChanged();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        protected TextView name;
        protected TextView upc;
        protected TextView manuf;
        protected TextView rType;

        public ProductViewHolder(View v) {
            super(v);
            name =  (TextView) v.findViewById(R.id.product_field);
            upc = (TextView)  v.findViewById(R.id.upc_field);
            manuf = (TextView)  v.findViewById(R.id.manufact_field);
            rType = (TextView) v.findViewById(R.id.recycle_field);
        }
        @Override
        public void onClick(View view) {
            Toast toast = Toast.makeText(view.getContext(), name.getText().toString(), Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}
