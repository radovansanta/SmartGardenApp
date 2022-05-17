package com.example.smartgarden.ViewModels;

import android.content.Context;
import android.os.Vibrator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.Repositories.ValveRepository;
import com.example.smartgarden.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    ValveRepository repository;
    private final MutableLiveData<Valve> selected = new MutableLiveData<>();

    public HomeViewModel() {
        repository = ValveRepository.getInstance();
    }

    public void searchForValves() {
        repository.searchForValve();
    }

    public LiveData<List<Valve>> getSearchedValves() {
        return repository.getSearchedValves();
    }

    public void select(Valve item) {
        selected.setValue(item);
    }

    public void updateValve(Valve valve){
        repository.updateValve(valve.getId(),valve);
    }

    public LiveData<Integer> getNumberOfSwitches() {
        return repository.getNumberOfSwitches();
    }

    public void searchForSwitches(int valveId) {
        repository.searchNumberOfSwitches(valveId);
    }

    public LiveData<Valve> getSelected() {
        return selected;
    }

}