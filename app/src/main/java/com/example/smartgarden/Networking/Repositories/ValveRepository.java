package com.example.smartgarden.Networking.Repositories;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.ApiResponses.ValveResponse;
import com.example.smartgarden.Networking.Apis.ValveApi;
import com.example.smartgarden.Networking.Services.ServiceGenerator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class ValveRepository {
    private static ValveRepository instance;
    private final MutableLiveData<Valve> searchedValves;

    private ValveRepository() {
        searchedValves = new MutableLiveData<>();
    }

    public static synchronized ValveRepository getInstance() {
        if (instance == null) {
            instance = new ValveRepository();
        }
        return instance;
    }

    public LiveData<Valve> getSearchedValves() {
        return searchedValves;
    }

    public void searchForValve() {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<ValveResponse> call = valveApi.getValve();
        call.enqueue(new Callback<ValveResponse>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ValveResponse> call, Response<ValveResponse> response) {
                if (response.isSuccessful()) {
                    searchedValves.setValue(response.body().getValve());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ValveResponse> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}
