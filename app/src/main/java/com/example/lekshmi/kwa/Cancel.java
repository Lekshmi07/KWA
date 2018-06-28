package com.example.lekshmi.kwa;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Cancel extends AppCompatActivity
{

    Button cancel,cancel_all;
    EditText Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel);

        cancel=findViewById(R.id.Cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Phone=findViewById(R.id.Phon);
                int ID=Integer.parseInt(Phone.getText().toString());

                for(int i=1;i<=2;i++) {
                    Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), ID+i, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                    AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    alarmManager.cancel(pendingIntent);
                }
            }
        });

    }
}
