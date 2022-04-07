package com.example.smartgarden.Networking.Services;

import com.example.smartgarden.Networking.Apis.ValveApi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {
    private static ValveApi valveApi;

    public static ValveApi getValveApi() {
        if (valveApi == null) {
            valveApi = new Retrofit.Builder()
                    .baseUrl("https://run.mocky.io")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ValveApi.class);
        }
        return valveApi;
    }
}
