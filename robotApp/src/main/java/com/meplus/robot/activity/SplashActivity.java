package com.meplus.robot.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.meplus.activity.BaseActivity;
import com.meplus.activity.IntentUtils;
import com.meplus.robot.R;

import butterknife.ButterKnife;
import cn.trinea.android.common.util.ToastUtils;

public class SplashActivity extends BaseActivity implements Handler.Callback{

    private static final String TAG = LogoActivity.class.getSimpleName();

    @butterknife.Bind(R.id.shimmer_view_container)
    ShimmerFrameLayout mShimmerViewContainer;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);
        mShimmerViewContainer.startShimmerAnimation();

        handler = new Handler(this);
        handler.sendEmptyMessageDelayed(1,1000);
    }

    @Override
    public boolean handleMessage(Message msg) {
        if(msg.what == 1){
            if(isWifiConnected(this)){
                startActivity(com.meplus.utils.IntentUtils.generateIntent(this, LogoActivity.class));
            }else{
                ToastUtils.show(this,"请链接网络！");
                startActivity(com.meplus.utils.IntentUtils.generateIntent(this,OffLineActivity.class));
            }
            finish();
        }
        return false;
    }

    //判断是否已经连接WiFi
    public static boolean isWifiConnected(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetworkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if(wifiNetworkInfo.isConnected())
        {
            return true ;
        }

        return false ;
    }

}
