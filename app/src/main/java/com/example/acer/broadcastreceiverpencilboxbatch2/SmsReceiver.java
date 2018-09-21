package com.example.acer.broadcastreceiverpencilboxbatch2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

public class SmsReceiver extends BroadcastReceiver {

    private static final String TAG = "incoming call";

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extras = intent.getExtras();

        if(extras != null){
            Object[]pduObj = (Object[]) extras.get("pdus");
            for(int i = 0; i < pduObj.length; i++){
                SmsMessage currentSms = SmsMessage.createFromPdu((byte[]) pduObj[i]);
                String number = currentSms.getDisplayOriginatingAddress();
                String msg = currentSms.getDisplayMessageBody();

                Log.e(TAG, "sms number: "+number);
                Log.e(TAG, "sms body: "+msg);
            }
        }
    }
}
