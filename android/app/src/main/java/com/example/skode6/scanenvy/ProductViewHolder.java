package com.example.skode6.scanenvy;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TJ on 6/25/2015.
 */
public class ProductViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected View view;
    protected TextView name;
    protected TextView upc;
    protected TextView manuf;
    protected TextView rType;

    public ProductViewHolder(View v) {
        super(v);
        view = v;
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
