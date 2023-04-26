package com.example.hellobanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.adunit.adapter.utility.AdInfo;
import com.ironsource.mediationsdk.logger.IronSourceError;
import com.ironsource.mediationsdk.sdk.LevelPlayBannerListener;

public class LargeActivity extends Activity implements LevelPlayBannerListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.largebanner);
        loadLayout();
    }

    protected void onResume() {
        super.onResume();
        IronSource.onResume(this);
    }
    protected void onPause() {
        super.onPause();
        IronSource.onPause(this);
    }


    @Override
    public void onAdLoaded(AdInfo adInfo) {

    }

    @Override
    public void onAdLoadFailed(IronSourceError ironSourceError) {

    }

    @Override
    public void onAdClicked(AdInfo adInfo) {

    }

    @Override
    public void onAdLeftApplication(AdInfo adInfo) {

    }

    @Override
    public void onAdScreenPresented(AdInfo adInfo) {

    }

    @Override
    public void onAdScreenDismissed(AdInfo adInfo) {

    }
    private void loadLayout(){
        Button btn_to_banner =(Button) findViewById(R.id.banner);

        btn_to_banner.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setClass( LargeActivity.this,MainActivity.class);
            startActivity(intent);
        });
    }
}
