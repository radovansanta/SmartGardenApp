package com.example.smartgarden.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartgarden.Models.Log;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.Repositories.ValveRepository;

import java.util.List;

public class SchedulerLogsViewModel extends ViewModel {
    ValveRepository repository;

    public SchedulerLogsViewModel() {
        repository = ValveRepository.getInstance();
    }

    public void searchForSchedulerLogs() {
        repository.searchForSchedulerLogs();
    }

    public LiveData<List<Log>> getSchedulerLogs() {
        return repository.getSearchedSchedulerLogs();
    }

}