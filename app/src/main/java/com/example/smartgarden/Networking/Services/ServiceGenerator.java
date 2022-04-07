package com.example.smartgarden.Networking.Services;

import com.example.smartgarden.Networking.Apis.ValveApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ValveApi valveApi;

    public static ValveApi getValveApi() {
        if (valveApi == null) {
            valveApi = new Retrofit.Builder()
                    .baseUrl("https://sheltered-forest-94333.herokuapp.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ValveApi.class);
        }
        return valveApi;
    }
}
