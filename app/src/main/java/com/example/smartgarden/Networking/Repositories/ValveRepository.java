package com.example.smartgarden.Networking.Repositories;

import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartgarden.Models.Command;
import com.example.smartgarden.Models.ResponseM;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.ApiResponses.ValveResponse;
import com.example.smartgarden.Networking.Apis.ValveApi;
import com.example.smartgarden.Networking.Services.ServiceGenerator;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.internal.EverythingIsNonNull;

public class ValveRepository {
    private static ValveRepository instance;
    private final MutableLiveData<Valve> searchedValve;
    private final MutableLiveData<List<Valve>> searchedValves;
    private final MutableLiveData<List<String>> searchedValvesNames;
    private final MutableLiveData<ResponseM> updateValveResponse;

    private ValveRepository() {
        searchedValves = new MutableLiveData<>();
        searchedValvesNames = new MutableLiveData<>();
        searchedValve = new MutableLiveData<>();
        updateValveResponse = new MutableLiveData<>();
    }

    public static synchronized ValveRepository getInstance() {
        if (instance == null) {
            instance = new ValveRepository();
        }
        return instance;
    }

    public LiveData<List<Valve>> getSearchedValves() {
        return searchedValves;
    }
    public LiveData<Valve> getSearchedValveByName() {
        return searchedValve;
    }
    public LiveData<List<String>> getSearchedValvesNames() {
        return searchedValvesNames;
    }
    public LiveData<ResponseM> getUpdateValveResponse() {
        return updateValveResponse;
    }

    public void searchForValveByName(String name) {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<Valve> call = valveApi.getValveByName(name);
        call.enqueue(new Callback<Valve>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<Valve> call, Response<Valve> response) {
                if (response.isSuccessful()) {
                    searchedValve.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<Valve> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void searchForValve() {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<List<Valve>> call = valveApi.getValve();
        call.enqueue(new Callback<List<Valve>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Valve>> call, Response<List<Valve>> response) {
                if (response.isSuccessful()) {
                    searchedValves.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Valve>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void searchForValveNames() {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<List<String>> call = valveApi.getValveNames();
        call.enqueue(new Callback<List<String>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (response.isSuccessful()) {
                    searchedValvesNames.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }

    public void updateVale(int id,String state) {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<ResponseM> call = valveApi.updateValve(id,state);
        ResponseM responseM = new ResponseM("");

        call.enqueue(new Callback<ResponseM>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ResponseM> call, Response<ResponseM> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit", response.body().getMessage());
                    updateValveResponse.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ResponseM> call, Throwable t) {
                System.out.println(call);
                System.out.println(call);
                System.out.println(t);
                Log.i("Error",call.toString());
                updateValveResponse.setValue(new ResponseM("Error"));

            }


        });
    }

    public void addCommand(Command command, String time){
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<ResponseBody> call = valveApi.addCommand(command, time);

        call.enqueue(new Callback<ResponseBody>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.i("Retrofit",response.message());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.i("Retrofit", "Something went wrong :(");
            }
        });
    }
}

