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

    TaskStackBuilder taskStackBuilder;
    Intent resultIntent;
    PendingIntent pendingIntent;
    NotificationCompat.Builder notificationBuilder;
    NotificationManager notificationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editTextModelo = (EditText) findViewById(R.id.edittext_modelo);
        final EditText editTextPlaca = (EditText) findViewById(R.id.edittext_placa);
        final Button buttonSalvar = (Button) findViewById(R.id.button_salvar);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String modelo = editTextModelo.getText().toString();
                String placa = editTextPlaca.getText().toString();

                if (modelo.isEmpty() || placa.isEmpty()) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Preencha todos os campos", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Log.d(LOG_TAG, "Criando notification.");

                //Creating Notification Builder
                notificationBuilder = new NotificationCompat.Builder(MainActivity.this);
                notificationBuilder.setContentTitle("Aula7");
                notificationBuilder.setContentText("Cadastro realizado com sucesso");
                notificationBuilder.setTicker(modelo + " - " + placa);
                notificationBuilder.setSmallIcon(android.R.drawable.alert_dark_frame);

                //Creating new Stack Builder
                taskStackBuilder = TaskStackBuilder.create(MainActivity.this);
                taskStackBuilder.addParentStack(MainActivity.class);

                //Intent which is opened when notification is clicked
                resultIntent = new Intent(MainActivity.this, MainActivity.class);

                taskStackBuilder.addNextIntent(resultIntent);
                pendingIntent = taskStackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
                notificationBuilder.setContentIntent(pendingIntent);

                notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                notificationManager.notify(0, notificationBuilder.build());

                Log.d(LOG_TAG, "Notification criada.");
            }
        });
    }
}
