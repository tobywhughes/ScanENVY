package com.example.skode6.scanenvy;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.skode6.scanenvy.backend.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TJ on 6/25/2015.
 */
public class ProductCardActivity extends AppCompatActivity {
    Product product;
    protected View view;
    protected TextView name;
    protected TextView upc;
    protected TextView manuf;
    protected TextView rType;

    public ProductCardActivity(){}

    public ProductCardActivity(Context context, Intent intent, View view) {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        Intent intent = getIntent();
        product = (Product) intent.getExtras().getSerializable("product");
        setProduct(product);
    }
    public void setProduct(Product p) {
        LinearLayout v = (LinearLayout) findViewById(R.id.one_item);
        name =  (TextView) v.findViewById(R.id.product_field);
        upc =   (TextView) v.findViewById(R.id.upc_field);
        manuf = (TextView) v.findViewById(R.id.manufact_field);
        rType = (TextView) v.findViewById(R.id.recycle_field);
        name.setText(p.getName());
        upc.setText(p.getUpc());
        manuf.setText(p.getManufact());
        rType.setText(""+p.getRType());
    }
}
