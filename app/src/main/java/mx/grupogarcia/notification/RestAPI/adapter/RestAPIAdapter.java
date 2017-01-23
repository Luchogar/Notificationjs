package mx.grupogarcia.notification.RestAPI.adapter;

import mx.grupogarcia.notification.RestAPI.ConstantesRestAPI;
import mx.grupogarcia.notification.RestAPI.Endpoints;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Luis García on 21/01/2017.
 */

public class RestAPIAdapter {
    public Endpoints establecerConexcionRestAPI(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantesRestAPI.ROOT_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                ;
        return retrofit.create(Endpoints.class);
    }

}
