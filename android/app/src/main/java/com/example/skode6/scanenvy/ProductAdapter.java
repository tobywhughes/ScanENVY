package com.example.skode6.scanenvy;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.skode6.scanenvy.backend.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    private Context context;
    private ArrayList<Product> itemsArrayList;

    public ProductAdapter(Context context, ArrayList<Product> itemsArrayList) {
        super(context, R.layout.product_layout, itemsArrayList);
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.product_layout, parent, false);
        // 3. Get the two text view from the rowView
        TextView name = (TextView) rowView.findViewById(R.id.product_field);
        TextView upc = (TextView) rowView.findViewById(R.id.upc_field);
        TextView manuf = (TextView) rowView.findViewById(R.id.manufact_field);
        TextView recy = (TextView) rowView.findViewById(R.id.recycle_field);
        // 4. Set the text for textView  //setText(itemsArrayList.get(position).getTitle());
        name.setText(itemsArrayList.get(position).getName());
        upc.setText(itemsArrayList.get(position).getUpc());
        manuf.setText(itemsArrayList.get(position).getManufact());
        recy.setText(""+itemsArrayList.get(position).getRType());

        // 5. retrn rowView
        return rowView;
    }
}
