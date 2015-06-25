package com.example.skode6.scanenvy.inappbilling.util;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by TJ on 6/22/2015.
 */
public class InAppBillingActivity extends AppCompatActivity{
    private static final String TAG = "com.example.inappbilling";
    IabHelper mHelper;
    static final String ITEM_SKU = "android.test.purchased";
    IabHelper.OnIabPurchaseFinishedListener mPurchaseFinishedListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        finished();
    }
    public void buyClick(View view) {
        mHelper.launchPurchaseFlow(this, ITEM_SKU, 10001,
                mPurchaseFinishedListener, "mypurchasetoken");
    }
    private void finished(){
        mPurchaseFinishedListener = new IabHelper.OnIabPurchaseFinishedListener() {
            public void onIabPurchaseFinished(IabResult result, Purchase purchase) {
                if (result.isFailure()) {
                    // Handle error
                    return;
                }
                else if (purchase.getSku().equals(ITEM_SKU)) {
                    //buyButton.setEnabled(false);
                }

            }
        };
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHelper != null) mHelper.dispose();
        mHelper = null;
    }
}
