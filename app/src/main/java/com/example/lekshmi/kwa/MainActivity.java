package com.example.lekshmi.kwa;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "npkTest: MainActivity";

    private int PHONE_REQUEST = 101;

    private Button add;
    private Button cancel;
    private TextView tw;
    int alarmID0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final Context context = getApplicationContext();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        add = findViewById(R.id.add_new_shift);
        cancel=findViewById(R.id.cancel_new_shift);
        tw = (TextView)findViewById(R.id.data);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent=new Intent(context,Main2Activity.class);
                // cancelAlarm(alarmID0);
                tw.setText("Alarm Set");

                startActivity(intent);

            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,Cancel.class);
                startActivity(intent);
            }
        });
        Log.i(TAG, "onCreate: Android OS: " + android.os.Build.VERSION.SDK_INT);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "onCreate: No permission.");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, PHONE_REQUEST);
        } else {
            Log.i(TAG, "onCreate: Phone permitted");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == PHONE_REQUEST) {
            if (grantResults.length == 0 || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                Log.i(TAG, "onRequestPermissionsResult: Permission granted");
                Toast.makeText(this, "No permission to use Phone. App won't work as expected", Toast.LENGTH_SHORT).show();
            } else {
                Log.i(TAG, "onRequestPermissionsResult: Permission denied");
            }
        }

    }


    private void cancelAlarm( int ID){

        tw.setText("");
        cancel.setEnabled(false);
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);




    }
}
