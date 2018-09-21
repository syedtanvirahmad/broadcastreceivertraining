package com.example.acer.broadcastreceiverpencilboxbatch2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.util.Log;

public class
PhoneNumberReceiver extends BroadcastReceiver {

    private static final String TAG = "incoming call";


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        if(extras != null){
            String state = extras.getString(TelephonyManager.EXTRA_STATE);
            Log.e(TAG, "state: "+state);
            if(state.equals(TelephonyManager.EXTRA_STATE_RINGING)){
                String number = extras.getString(TelephonyManager.EXTRA_INCOMING_NUMBER);
                
                Log.e(TAG, "number: "+number);
            }
        }
    }
}
