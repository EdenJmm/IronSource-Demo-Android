package com.example.hellobanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.ironsource.mediationsdk.ISBannerSize;
import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.IronSourceBannerLayout;
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener;

public class BannerActivity  extends Activity  {
    private final String TAG = "Hello BannerActivity";
    private FrameLayout mBannerParentLayout;
    private IronSourceBannerLayout mIronSourceBannerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.banner);
        initUIElements();
        createAndloadBanner();
    }

    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }

    private void initUIElements(){
        Button btn_to_banner =(Button) findViewById(R.id.banner);

        btn_to_banner.setOnClickListener(v -> {
            this.finish();
        });

        mBannerParentLayout = (FrameLayout) findViewById(R.id.banner_footer);
    }
    private void createAndloadBanner() {
        // choose banner size
        /*ISBannerSize.class
         * public static final ISBannerSize BANNER = m.a("BANNER", 320, 50);
         * public static final ISBannerSize LARGE = m.a("LARGE", 320, 90);
         * public static final ISBannerSize RECTANGLE = m.a("RECTANGLE", 300, 250);
         * protected static final ISBannerSize a = m.a();
         * public static final ISBannerSize SMART = m.a("SMART", 0, 0);
         * */

        // choose banner size
        ISBannerSize size = ISBannerSize.LARGE;
        // instantiate IronSourceBanner object, using the IronSource.createBanner API
        mIronSourceBannerLayout = IronSource.createBanner(this, size);

        // add IronSourceBanner to your container
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.MATCH_PARENT);
        mBannerParentLayout.addView(mIronSourceBannerLayout, 0, layoutParams);
        Log.d(TAG, "--------layoutParams-------"+layoutParams);

        if (mIronSourceBannerLayout != null) {
            Log.d(TAG, "-------createAndloadBanner-------mIronSourceBannerLayout  not null--------");
            mIronSourceBannerLayout.setLevelPlayBannerListener(new LevelPlayBannerListener() {

                // Invoked each time a banner was loaded. Either on refresh, or manual load.
                //  AdInfo parameter includes information about the loaded ad
                @Override
                public void onAdLoaded(AdInfo adInfo) {

                    mBannerParentLayout.setVisibility(View.VISIBLE);
                    Log.d(TAG, "-------onAdLoaded---------------");
                }

                // Invoked when the banner loading process has failed.
                //  This callback will be sent both for manual load and refreshed banner failures.
                @Override
                public void onAdLoadFailed(IronSourceError error) {
                    Log.d(TAG, "onBannerAdLoadFailed" + " " + error);
                }

                // Invoked when end user clicks on the banner ad
                @Override
                public void onAdClicked(AdInfo adInfo) {
                    Log.d(TAG, "onAdClicked");
                }

                // Notifies the presentation of a full screen content following user click
                @Override
                public void onAdScreenPresented(AdInfo adInfo) {
                    Log.d(TAG, "onAdScreenPresented");
                }

                // Notifies the presented screen has been dismissed
                @Override
                public void onAdScreenDismissed(AdInfo adInfo) {
                    Log.d(TAG, "onAdScreenDismissed");
                    //destroyAndDetachBanner();
                }

                //Invoked when the user left the app
                @Override
                public void onAdLeftApplication(AdInfo adInfo) {
                    Log.d(TAG, "onAdLeftApplication");
                    //destroyAndDetachBanner();
                }

            });

            IronSource.loadBanner(mIronSourceBannerLayout);

        } else {
            Log.d(TAG, "-------onAdLoaded-------mIronSourceBannerLayout   null--------");
            Toast.makeText(BannerActivity.this, "IronSource.createBanner returned null", Toast.LENGTH_LONG).show();
        }
    }
    /**
     * Destroys IronSource Banner and removes it from the container
     *
     */
    private void destroyAndDetachBanner() {
        IronSource.destroyBanner(mIronSourceBannerLayout);
        if (mBannerParentLayout != null) {
            mBannerParentLayout.removeView(mIronSourceBannerLayout);
        }
    }

}
