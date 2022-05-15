package com.example.smartgarden.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartgarden.Models.Log;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.Repositories.CommandRepository;
import com.example.smartgarden.Networking.Repositories.ValveRepository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SchedulerLogsViewModel extends ViewModel {
    CommandRepository repository;

    public SchedulerLogsViewModel() {
        repository = CommandRepository.getInstance();
    }

    public void searchForSchedulerLogs() {
        repository.searchForSchedulerLogs();
    }
    public void searchForSchedulerLogsUpcoming() {
        repository.searchForSchedulerLogsUpcoming();
    }
    public void searchForSchedulerLogsPrevious() {
        repository.searchForSchedulerLogsPrevious();
    }
    public void deleteLog(int commandId) {
        repository.deleteLog(commandId);
    }

    public LiveData<List<Log>> getSchedulerLogs() {
        return repository.getSearchedSchedulerLogs();
    }
    public LiveData<List<Log>> getSchedulerLogsUpcoming() {
        return repository.getSearchedSchedulerLogsUpcoming();
    }
    public LiveData<List<Log>> getSchedulerLogsPrevious() {
        return repository.getSearchedSchedulerLogsPrevious();
    }

}