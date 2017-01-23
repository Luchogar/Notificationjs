package mx.grupogarcia.notification;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by Luis García on 17/01/2017.
 */

public class NotificacionIDTokenService extends FirebaseInstanceIdService{
    public static final String TAG = "FIREBASE TOKEN";
    @Override
    public void onTokenRefresh() {
        //super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        //enviarTokenRegistro(token);

    }
    private void enviarTokenRegistro(String token){
        Log.d(TAG,token);

    }

}
