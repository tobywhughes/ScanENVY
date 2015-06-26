package com.example.skode6.scanenvy;

import android.app.ListActivity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListView;

import com.example.skode6.scanenvy.backend.Product;
import com.example.skode6.scanenvy.backend.Run;
import com.github.alexkolpa.fabtoolbar.FabToolbar;

import java.util.ArrayList;

/**
 * Created by TJ on 6/25/2015.
 */
public class ProductListActivity extends ListActivity {
    protected Run run = new Run();
    protected ListView lv;

    public ProductListActivity(){
        setListView();
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListView();
       // setContentView(findViewById(R.id.list));
    }
    //@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setListView();
        //return inflater.inflate(container, false);
        return null;
    }
    private void setListView(){
        RecyclerView recList = (RecyclerView) findViewById(R.id.list);
        recList.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recList.setLayoutManager(llm);
    }
    private ArrayList<Product> generateData(){
        run.loadData();
        ArrayList<Product> items = run.getProductList();
        String productName = "Coca-Cola";
        String manufacturer = "The Coca-Cola Company";
        int type = 8;
        String upc = "0049000006346";
        Product product = new Product(upc, productName, type, manufacturer);
        items.add(product);


        //object2
        productName = "Dr. Pepper";
        manufacturer = "Dr. Pepper / Seven-Up Inc";
        type= 8;
        upc = "0078000003154";
        Product product2 = new Product(upc, productName, type, manufacturer);
        items.add(product2);


        //object3
        productName = "Kirkland Signature Purified Drinking Water";
        manufacturer = "Kirkland";
        type= 1;
        upc = "0096619756803";
        Product product3 = new Product(upc, productName, type, manufacturer);
        items.add(product3);

        //Object4
        productName = "7up Diversion Safe";
        manufacturer = "Dr. Pepper / Seven-Up Inc.";
        type= 8;
        upc = "0078000000382";
        Product product4 = new Product(upc, productName, type, manufacturer);
        items.add(product4);

        return items;
    }
}
