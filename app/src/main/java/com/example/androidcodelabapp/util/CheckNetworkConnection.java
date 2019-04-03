package com.example.androidcodelabapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class CheckNetworkConnection {
    private Context context;
    public CheckNetworkConnection(Context context){
        this.context = context;

    }
    public boolean isConnected(){
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            android.net.NetworkInfo wifi = cm.getActiveNetworkInfo();
            android.net.NetworkInfo mobile = cm.getActiveNetworkInfo();
            if(mobile != null && mobile.isConnectedOrConnecting() || (wifi != null && wifi.isConnectedOrConnecting()))
                return true;
            else return false;
        }
        else  return false;
    }

}
