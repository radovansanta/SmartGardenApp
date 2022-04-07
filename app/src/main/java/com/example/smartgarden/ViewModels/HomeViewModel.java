package com.example.smartgarden.ViewModels;

import android.content.Context;
import android.os.Vibrator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.Repositories.ValveRepository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    ValveRepository repository;
    private final MutableLiveData<String> selected = new MutableLiveData<>();

    public HomeViewModel() {
        repository = ValveRepository.getInstance();
    }

    public void searchForPokemon() {
        repository.searchForValve();
    }

    public LiveData<List<Valve>> getSearchedPokemon() {
        return repository.getSearchedValves();
    }

    public void select(String item) {
        selected.setValue(item);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

}