package com.example.smartgarden.Networking.Repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.smartgarden.Models.Log;
import com.example.smartgarden.Networking.Apis.ValveApi;
import com.example.smartgarden.Networking.Services.ServiceGenerator;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.internal.EverythingIsNonNull;

public class CommandRepository {
    private static CommandRepository instance;
    private final MutableLiveData<List<Log>> searchedSchedulerLogs;
    private final MutableLiveData<List<Log>> searchedSchedulerLogsUpcoming;
    private final MutableLiveData<List<Log>> searchedSchedulerLogsPrevious;

    public CommandRepository() {
        this.searchedSchedulerLogs = new MutableLiveData<>();
        this.searchedSchedulerLogsUpcoming = new MutableLiveData<>();
        this.searchedSchedulerLogsPrevious = new MutableLiveData<>();
    }

    public static synchronized CommandRepository getInstance() {
        if (instance == null) {
            instance = new CommandRepository();
        }
        return instance;
    }

    public LiveData<List<Log>> getSearchedSchedulerLogs() {
        return searchedSchedulerLogs;
    }
    public LiveData<List<Log>> getSearchedSchedulerLogsUpcoming() {
        return searchedSchedulerLogsUpcoming;
    }
    public LiveData<List<Log>> getSearchedSchedulerLogsPrevious() {
        return searchedSchedulerLogsPrevious;
    }

    public void deleteLog(int commandId) {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<ResponseBody> call = valveApi.deleteLog(commandId);
        call.enqueue(new Callback<ResponseBody>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {

                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void searchForSchedulerLogs() {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<List<Log>> call = valveApi.getSchedulerLogs();
        call.enqueue(new Callback<List<Log>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                if (response.isSuccessful()) {
                    searchedSchedulerLogs.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void searchForSchedulerLogsUpcoming() {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<List<Log>> call = valveApi.getSchedulerUpcomingLogs();
        call.enqueue(new Callback<List<Log>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                if (response.isSuccessful()) {
                    searchedSchedulerLogsUpcoming.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }

    public void searchForSchedulerLogsPrevious() {
        ValveApi valveApi = ServiceGenerator.getValveApi();
        Call<List<Log>> call = valveApi.getSchedulerPreviousLogs();
        call.enqueue(new Callback<List<Log>>() {
            @EverythingIsNonNull
            @Override
            public void onResponse(Call<List<Log>> call, Response<List<Log>> response) {
                if (response.isSuccessful()) {
                    searchedSchedulerLogsPrevious.setValue(response.body());
                }
            }
            @EverythingIsNonNull
            @Override
            public void onFailure(Call<List<Log>> call, Throwable t) {
                System.out.println(t);
            }
        });
    }


}
