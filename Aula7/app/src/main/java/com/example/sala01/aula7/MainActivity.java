package com.example.sala01.aula7;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "LOG_TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextModel = (EditText) findViewById(R.id.edittext_model);
        final EditText editTextLicensePlate = (EditText) findViewById(R.id.edittext_license_plate);
        final Button buttonSave = (Button) findViewById(R.id.button_save);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String model = editTextModel.getText().toString();
                String licensePlate = editTextLicensePlate.getText().toString();

                if (model.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getText(R.string.edittext_model_alert_message), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (licensePlate.isEmpty()) {
                    Toast.makeText(getApplicationContext(), getText(R.string.edittext_license_plate_alert_message), Toast.LENGTH_SHORT).show();
                    return;
                }

                Log.d(LOG_TAG, "Creating notification.");

                //Creating Notification Builder
                NotificationCompat.Builder notificationCompatBuilder = new NotificationCompat.Builder(MainActivity.this);
                notificationCompatBuilder.setContentTitle("Aula7");
                notificationCompatBuilder.setContentText("Car successfully registered.");
                notificationCompatBuilder.setTicker(model + " - " + licensePlate);
                notificationCompatBuilder.setSmallIcon(android.R.drawable.alert_dark_frame);

                //Creating new Stack Builder
                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(MainActivity.this);
                taskStackBuilder.addParentStack(MainActivity.class);

                //Intent which is opened when notification is clicked
                Intent intent = new Intent(MainActivity.this, MainActivity.class);

                taskStackBuilder.addNextIntent(intent);
                PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                notificationCompatBuilder.setContentIntent(pendingIntent);

                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificationCompatBuilder.build());

                Log.d(LOG_TAG, "Notification created.");
            }
        });
    }
}
