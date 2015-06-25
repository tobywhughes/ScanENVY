package com.example.skode6.scanenvy;

import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by TJ on 6/20/2015.
 */
public class SearchActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.example.edwardsitj.myapplication.MESSAGE";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.search);

        // Get the intent, verify the action and get the query
        handleIntent(getIntent());
    }
    public boolean onSearchRequested() {
        Bundle appData = new Bundle();
        appData.putBoolean(SearchActivity.EXTRA_MESSAGE, true);
        startSearch(null, false, appData, false);
        return true;
    }
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }
    private void doMySearch(String query) {

    }
}
