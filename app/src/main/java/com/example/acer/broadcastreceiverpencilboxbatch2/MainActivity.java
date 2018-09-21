package com.example.acer.broadcastreceiverpencilboxbatch2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.BatteryManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class
MainActivity extends AppCompatActivity {

    private TextView connectionStatusTV,batteryStatusTV;
    private ConnectivityReceiver connectivityReceiver;
    private CustomBroadcastReceiver customBroadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        connectionStatusTV = (TextView) findViewById(R.id.connectionStatus);
        batteryStatusTV = (TextView) findViewById(R.id.batteryStatus);

        Intent intent = registerReceiver(null,new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
        int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL,0);
        batteryStatusTV.setText(String.valueOf(level));

    }

    @Override
    protected void onResume() {
        super.onResume();
        connectivityReceiver = new ConnectivityReceiver();
        customBroadcastReceiver = new CustomBroadcastReceiver();
        registerReceiver(connectivityReceiver,new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        //LocalBroadcastManager.getInstance(this).registerReceiver(customBroadcastReceiver, new IntentFilter(CustomBroadcastReceiver.ACTION));
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(connectivityReceiver);
    }

    public void sendBroadcast(View view) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(CustomBroadcastReceiver.ACTION).putExtra("course","android"));
    }

    public class ConnectivityReceiver extends BroadcastReceiver{
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo info = manager.getActiveNetworkInfo();
            if(info != null && info.isConnected()){
                
                connectionStatusTV.setText("connected");
            }else{
                connectionStatusTV.setText("disconnected");
            }
        }
    }

    public class CustomBroadcastReceiver extends BroadcastReceiver{
        public static final String ACTION = "com.example.acer.broadcastreceiverpencilboxbatch2.CUSTOM_ACTION";
        @Override
        public void onReceive(Context context, Intent intent) {
            String msg = intent.getStringExtra("course");
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
        }
    }

}
