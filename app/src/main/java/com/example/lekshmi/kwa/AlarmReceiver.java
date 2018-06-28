package com.example.lekshmi.kwa;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver
{
    private static final String TAG = "npkTest: AlarmReceiver";

    public void onReceive(Context context, Intent intent) {
        Bundle b = intent.getExtras();
        assert b != null;
        String No = b.getString("Number");

        Log.i(TAG, "onReceive: Making call");
        Toast.makeText(context, "ALARM", Toast.LENGTH_LONG).show();
        Toast.makeText(context, No, Toast.LENGTH_SHORT).show();

        AppUtilsToON.dial(No,context);

    }

}

