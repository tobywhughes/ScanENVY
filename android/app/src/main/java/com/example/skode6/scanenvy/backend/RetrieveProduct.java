package com.example.skode6.scanenvy.backend;

import android.os.AsyncTask;
import android.support.v4.os.AsyncTaskCompat;

import java.io.IOException;

/**
 * Created by TJ on 6/25/2015.
 */
public class RetrieveProduct extends AsyncTask<String, Void, ScrapeObject> {

    protected ScrapeObject doInBackground(String... upc) {
        try {
            WebScrape scraper = new WebScrape();
            return scraper.scrape(upc[0]);
        }
        catch (IOException e) {

        }
        return null;
    }
    protected void onPostExecute(ScrapeObject result) {
        //showDialog("Downloaded " + result + " bytes");
    }

}
