package com.example.jugandoconnotificaciones2021;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String ID_CANAL = "El nombre de mi canal";
    private static final int CODIGO_RESPUESTA = 1;
    Button buttonNotificacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras()!=null){
            String mensaje = getIntent().getExtras().get("IDENTIFICADOR").toString();
            Toast.makeText(this, "El mensaje de la notificación: "+mensaje, Toast.LENGTH_SHORT).show();
        }

        buttonNotificacion = findViewById(R.id.buttonNotificacion);

        buttonNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarNotificacion();
            }
        });
    }

    private void lanzarNotificacion() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);

        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Ejemplo de notificación")
                .setAutoCancel(false).setContentText("Aquí va el texto de mi notificación");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String idChannel= "Canal 1";
            String nombreCanal = "Mi canal favorito";
            NotificationChannel notificationChannel = new NotificationChannel(idChannel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannel);
            notificationManager.createNotificationChannel(notificationChannel);

        }else {

            //Menor que oreo
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }

        //Acción tras pulsar la notificación
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplicationContext());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("IDENTIFICADOR", "Notificación simple");
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(CODIGO_RESPUESTA, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify(1, builder.build() );
    }
}