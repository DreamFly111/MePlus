package com.meplus.robot.BroadCastReceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.meplus.robot.activity.LogoActivity;

/**
 * Created by Dream on 2016/5/23.
 */
public class BroadCastReceiver extends BroadcastReceiver {
    static final String ACTION = "android.intent.action.BOOT_COMPLETED";
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(ACTION)){
            Intent robotActivity = new Intent(context, LogoActivity.class);
            robotActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(robotActivity);
        }
    }
}
