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
    Button buttonNotificacion, buttonNotificacion2, buttonNotificacion3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (getIntent().getExtras() != null) {
            String mensaje = getIntent().getExtras().get("IDENTIFICADOR").toString();
            Toast.makeText(this, "El mensaje de la notificación: " + mensaje, Toast.LENGTH_SHORT).show();
        }

        buttonNotificacion = findViewById(R.id.buttonNotificacion);
        buttonNotificacion2 = findViewById(R.id.buttonNotificacion2);
        buttonNotificacion3 = findViewById(R.id.button3);

        buttonNotificacion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarNotificacionMuchoTexto();
            }
        });

        buttonNotificacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarNotificacion();
            }
        });

        buttonNotificacion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lanzarNotificacionTextoLargo();
            }
        });
    }

    private void lanzarNotificacionMuchoTexto() {
        String idChannel = "Canal 3";
        String nombreCanal = "Mi canal muchas líneas";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);

        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Ejemplo de notificación con texto largo")
                .setAutoCancel(false).setContentText("Aquí va el texto de mi notificación")
                .setContentText("Primero poquito texto");

       NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
       inboxStyle.setBigContentTitle("Este es una notificación con muchos datos");
       inboxStyle.addLine("Esta es la primera línea de muchas más");
       inboxStyle.addLine("Aquí va otra línea va bien");
       inboxStyle.addLine("Cristian ponte bueno ya");
       inboxStyle.addLine("Mario hay que andar un poquito más");
       inboxStyle.addLine("El profesor se aburre");


        builder.setStyle(inboxStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(idChannel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannel);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {
            //Menor que oreo
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }

        notificationManager.notify(3, builder.build());

    }

    private void lanzarNotificacionTextoLargo() {
        String idChannel = "Canal 2";
        String nombreCanal = "Mi canal texto largo";
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);

        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Ejemplo de notificación con texto largo")
                .setAutoCancel(false).setContentText("Aquí va el texto de mi notificación")
                .setContentText("Primero poquito texto");

        NotificationCompat.BigTextStyle bigTextStyle = new NotificationCompat.BigTextStyle();
        bigTextStyle.setBigContentTitle("Esta es la cabecera del megatexto");
        bigTextStyle.bigText("Benjamin Géza Affleck-Boldt (Berkeley, Estados Unidos; 15 de agosto de 1972), conocido simplemente como Ben Affleck, es un actor, director, productor y guionista estadounidense. Apoyado por su madre, inició su carrera como actor infantil de documentales educativos y después apareciendo en varias películas dirigidas por Kevin Smith, entre estas Mallrats (1995) y Chasing Amy (1997). Comenzó a ganar notoriedad dentro de la industria del cine tras protagonizar y escribir el guion de Good Will Hunting (1997), película que obtuvo la aclamación crítica y le valió, entre otros premios, el Óscar al mejor guion original.\n" +
                "\n" +
                "Affleck comenzó a ser uno de los actores más prominentes del cine hacia 1998 al protagonizar exitosas películas en taquilla como Armageddon (1998), Shakespeare in Love (1998), Pearl Harbor (2001) y The Sum of All Fears (2002). Sin embargo, su carrera comenzó a sufrir un declive a partir de 2003 debido a sus problemas de adicción al alcohol y el interés de la prensa en su vida personal, agravado además por películas que fracasaron en crítica y taquilla, entre estas Daredevil (2003), Gigli (2003), Jersey Girl (2004) y Surviving Christmas (2004). Por ello, aunque aún actuaba en varias producciones, decidió emprender una carrera como director y de esta forma dirigió cintas como Gone Baby Gone (2007) y The Town (2010), que le valieron elogios de la crítica. Poco después, protagonizó, dirigió y produjo Argo (2012), que se convirtió en un éxito tanto en crítica como taquilla y ganó el Óscar a la mejor película, además de otorgarle múltiples BAFTA y Globos de Oro. ");
        bigTextStyle.setSummaryText("Un buen tío");

        builder.setStyle(bigTextStyle);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel(idChannel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannel);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {
            //Menor que oreo
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }

        notificationManager.notify(2, builder.build());
    }

    private void lanzarNotificacion() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, ID_CANAL);

        builder.setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Ejemplo de notificación")
                .setAutoCancel(false).setContentText("Aquí va el texto de mi notificación");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String idChannel = "Canal 1";
            String nombreCanal = "Mi canal favorito";
            NotificationChannel notificationChannel = new NotificationChannel(idChannel, nombreCanal, NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.GREEN);
            notificationChannel.enableVibration(true);
            notificationChannel.setShowBadge(true);
            builder.setChannelId(idChannel);
            notificationManager.createNotificationChannel(notificationChannel);

        } else {

            //Menor que oreo
            builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE | Notification.DEFAULT_LIGHTS);
        }

        //Acción tras pulsar la notificación
        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(getApplicationContext());
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("IDENTIFICADOR", "Notificación simple"); //Le paso mensaje
        taskStackBuilder.addNextIntent(intent);

        PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(CODIGO_RESPUESTA, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);

        notificationManager.notify(1, builder.build());
    }
}