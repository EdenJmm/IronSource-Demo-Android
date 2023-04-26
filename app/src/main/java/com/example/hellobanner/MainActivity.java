package com.example.hellobanner;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ironsource.mediationsdk.IronSource;
import com.ironsource.mediationsdk.integration.IntegrationHelper;

public class MainActivity extends Activity   {
    private final String TAG = "-------MainActivity-------------";
    private final String APP_KEY="85460dcd";
    private final String FALLBACK_USER_ID = "userId";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntegrationHelper.validateIntegration(this);
        loadLayout();
        startIronSourceInitTask();
        IronSource.getAdvertiserId(this);
        IronSource.shouldTrackNetworkState(this, true);

    }
    private void startIronSourceInitTask() {
        String advertisingId = IronSource.getAdvertiserId(MainActivity.this);
        // we're using an advertisingId as the 'userId'
        initIronSource(APP_KEY, advertisingId);


    }
    private void initIronSource(String appKey, String userId) {
        IronSource.setUserId(FALLBACK_USER_ID);
        IronSource.init(this, appKey);
    }
    protected void onResume() {
        super.onResume();

    }
    protected void onPause() {
        super.onPause();

    }
    private void loadLayout(){
        Button btn_to_banner =(Button) findViewById(R.id.banner);

        btn_to_banner.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setClass( MainActivity.this,BannerActivity.class);
            startActivity(intent);
        });

        Button btn_to_large =(Button) findViewById(R.id.large);

        btn_to_large.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setClass( MainActivity.this,LargeActivity.class);
            startActivity(intent);
        });

        Button btn_to_merc =(Button) findViewById(R.id.mrec);

        btn_to_merc.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setClass( MainActivity.this,MercActivity.class);
            startActivity(intent);
        });

        Button btn_to_admob =(Button) findViewById(R.id.admoblarge);

        btn_to_admob.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setClass( MainActivity.this,AdmobLargeActivity.class);
            startActivity(intent);
        });

        Button btn_to_leader =(Button) findViewById(R.id.leaderboard);

        btn_to_leader.setOnClickListener(v -> {
            Intent intent= new Intent();
            intent.setClass( MainActivity.this,LeaderboardActivity.class);
            startActivity(intent);
        });

    }
}
