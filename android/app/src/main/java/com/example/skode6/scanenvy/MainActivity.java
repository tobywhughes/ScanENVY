package com.example.skode6.scanenvy;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.DigitsKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.skode6.scanenvy.backend.*;
import com.example.skode6.scanenvy.inappbilling.util.IabHelper;
import com.example.skode6.scanenvy.inappbilling.util.IabResult;
import com.example.skode6.scanenvy.inappbilling.util.Purchase;
import com.github.alexkolpa.fabtoolbar.FabToolbar;
//import com.google.zxing.client.android.SCAN;
//import me.drakeet.materialdialog;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public final static String EXTRA_MESSAGE = "com.example.edwardsitj.myapplication.MESSAGE";
    protected FabToolbar fabToolbar;
    protected Toolbar toolbar;
    protected RecyclerView lv;
    // Class
    protected Run run = new Run();
    //Class
    protected EditText edit;
    protected AlertDialog dialog;
    //Class
    protected IabHelper mHelper;
    private static final String TAG = "com.example.inappbilling";
    private IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;
    protected ProductAdapter adapter;
    private static List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
        if (savedInstanceState != null) {
            try{
                ArrayList myArrayList = (ArrayList)getLastNonConfigurationInstance();
            } catch(NullPointerException e) {
                List<Product> values = new ArrayList<Product>();
                for (int i = 0; i < productList.size(); i++) {
                    values.add((Product) savedInstanceState.getSerializable("product" + i));
                }
                if (values != null) {
                    lv = (RecyclerView) findViewById(R.id.list);
                    lv.setHasFixedSize(false);
                    LinearLayoutManager llm = new LinearLayoutManager(this);
                    lv.setLayoutManager(llm);
                    adapter = new ProductAdapter(this, values);
                    lv.setAdapter(adapter);
                }
            }
        }
        else {
            productList = run.getProductList();
            setListView();
        }
        setToolBar();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog = enterDialog(edit = new EditText(this));
        edit = new EditText(this);
        fabToolbar = ((FabToolbar) findViewById(R.id.fab_toolbar));
        findViewById(R.id.scan_code).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                fabToolbar.hide();
            }
        });
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    private void setDonate(){
        String base64EncodedPublicKey = "<your license key here>";
        mHelper = new IabHelper(this, base64EncodedPublicKey);

        mHelper.startSetup(new IabHelper.OnIabSetupFinishedListener() {
            public void onIabSetupFinished(IabResult result) {
                if (!result.isSuccess()) {
                    //Log.d(TAG, "In-app Billing setup failed: " +result);
                } else {
                    //Log.d(TAG, "In-app Billing is set up OK");
                }
            }
        });
    }
    public void donateClick(View view) {
        mHelper.launchPurchaseFlow(this, "donate01", 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
    }
    private void finished(){
        mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                if (result.isFailure()) {
                    // Handle error
                    return;
                }
                else if (purchase.getSku().equals("donate01")) {
                    //buyButton.setEnabled(false);
                }

            }
        };
    }
    private void setToolBar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.menu_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }
    private void setListView(){
        lv = (RecyclerView) findViewById(R.id.list);
        lv.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(this);
       // llm.setOrientation(LinearLayoutManager.);
        lv.setLayoutManager(llm);
        adapter = new ProductAdapter(this, productList);
        lv.setAdapter(adapter);
    }
    public void onSaveInstanceState(Bundle savedState) {

        super.onSaveInstanceState(savedState);

        // Note: getValues() is a method in your ArrayAdaptor subclass
        List<Product> values = adapter.getValues();
        for (int i = 0; i < values.size(); i++){
            savedState.putSerializable("product"+i, values.get(i));
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.search));
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                //loadHistory(query);
                return false;
            }
        });
        if(searchView != null) {
            searchView.setIconifiedByDefault(true);
            searchView.setFocusable(true);
            searchView.setIconified(true);
            searchView.setOnSearchClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.showSoftInput(searchView, InputMethodManager.HIDE_IMPLICIT_ONLY);
                    fabToolbar.hide();
                }
            });
            searchView.setFocusableInTouchMode(false);
            searchView.setOnClickListener(clickText());
            searchView.setOnFocusChangeListener(focusText());
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "Not Null", Toast.LENGTH_SHORT);
            toast.show();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Product> generateData(){
        List<Product> list = run.loadData();

        return list;
    }

    public void faderClicked(View v){
        fabToolbar.hide();
    }
    public void scan(View v){
        fabToolbar.hide();
        Toast toast = Toast.makeText(getApplicationContext(), "Scan UPC", Toast.LENGTH_SHORT);
        toast.show();
        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
        startActivityForResult(intent, 0);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        Product product = null;
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {

                String contents = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                Toast toast = Toast.makeText(getApplicationContext(), contents, Toast.LENGTH_LONG);
                toast.show();
                try {
                    product = run.lookUp(contents);
                }
                catch (IOException e){
                    Toast error = Toast.makeText(getApplicationContext(), "Couldn't find" + contents, Toast.LENGTH_LONG);
                    error.show();
                }
                try {
                    adapter.add(product);
                    //run.addProduct(product);
                }
                catch (Exception e2){
                    Toast error = Toast.makeText(getApplicationContext(), "Couldn't add" + contents, Toast.LENGTH_LONG);
                    error.show();
                }

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Toast toast = Toast.makeText(getApplicationContext(), "Scan Canceled", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
    public void enter(View v){
        fabToolbar.hide();
        Toast toast = Toast.makeText(getApplicationContext(), "Enter UPC", Toast.LENGTH_SHORT);
        toast.show();
        dialog.show();
    }

    private AlertDialog enterDialog(final EditText edit){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);

        edit.setKeyListener(new DigitsKeyListener());
        final InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(edit, InputMethodManager.SHOW_IMPLICIT);
        //edit.setFocusableInTouchMode(true);
        edit.setFocusable(true);
        //edit.setOnClickListener(clickText());
        //edit.requestFocusFromTouch();
        edit.requestFocus();

        dialog.setView(edit);
        dialog.setCancelable(true);
        dialog.setTitle("Enter UPC");
        dialog.setPositiveButton("Submit", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String upc = edit.getText().toString();
                        try {
                            Product p = run.lookUp(upc);
                            adapter.add(p);
                            //run.addProduct(p);
                        }
                        catch (IOException e) {
                            Toast error = Toast.makeText(getApplicationContext(), "Couldn't find" + upc, Toast.LENGTH_LONG);
                            error.show();
                        }
                    }
                });
        dialog.setNegativeButton("Cancel", new
                DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        return dialog.create();
    }
    private View.OnClickListener clickText() {
        View.OnClickListener click = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.setFocusableInTouchMode(true);
                v.requestFocusFromTouch();
            }
        };
        return click;
    }
    private View.OnFocusChangeListener focusText() {
        View.OnFocusChangeListener focus = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    v.setFocusableInTouchMode(false);
                }
            }
        };
        return focus;
    }
    public boolean onSearchRequested() {
        fabToolbar.hide();
        Toast toast = Toast.makeText(getApplicationContext(), "Search Requested", Toast.LENGTH_SHORT);
        toast.show();
        return super.onSearchRequested();
    }
    @Override
    public void onClick(View v) {
        fabToolbar.hide();
    }
}
