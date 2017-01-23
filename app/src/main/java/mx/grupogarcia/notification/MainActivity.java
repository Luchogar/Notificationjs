package mx.grupogarcia.notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
//import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;

import com.google.firebase.iid.FirebaseInstanceId;

import mx.grupogarcia.notification.RestAPI.Endpoints;
import mx.grupogarcia.notification.RestAPI.adapter.RestAPIAdapter;
import mx.grupogarcia.notification.RestAPI.model.UsuarioResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void enviarToken(View v){


        //Este metodo funciono cuando unicamente hicimos un boton con una notificacion simple
        /*Intent i = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent =  PendingIntent.getActivity(this, 0, i,PendingIntent.FLAG_ONE_SHOT);
        Uri sonido = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);


        NotificationCompat.Builder notificacion = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.notification)
                .setContentTitle("Notificacion")
                .setContentText("Hola mundo")
                .setSound(sonido)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        NotificationManager notificationManager= (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificacion.build());
        */
        String token = FirebaseInstanceId.getInstance().getToken();
        enviarTokenRegistro(token);

    }
    private void enviarTokenRegistro(String token){
        Log.d("TOKEN",token);
        RestAPIAdapter restAPIAdapter = new RestAPIAdapter();
        Endpoints endpoints = restAPIAdapter.establecerConexcionRestAPI();
        final Call<UsuarioResponse> usuarioResponseCall =endpoints.registrarTokenID(token);
        usuarioResponseCall.enqueue(new Callback<UsuarioResponse>() {
            @Override
            public void onResponse(Call<UsuarioResponse> call, Response<UsuarioResponse> response) {
              UsuarioResponse usuarioResponse = response.body();
                Log.d("ID_FIREBASE",usuarioResponse.getId());
                Log.d("USUARIO_FIREBASE",usuarioResponse.getToken());
            }

            @Override
            public void onFailure(Call<UsuarioResponse> call, Throwable t) {

            }
        });

    }

}
