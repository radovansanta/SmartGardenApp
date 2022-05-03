package com.example.smartgarden.ViewModels;

import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.smartgarden.Models.Command;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Networking.Repositories.ValveRepository;

import org.json.JSONObject;

import java.util.List;

public class SlideshowViewModel extends ViewModel {

    ValveRepository repository;

    public SlideshowViewModel() {
        repository = ValveRepository.getInstance();
    }

    public void searchForPokemon() {
        repository.searchForValve();
    }

    public LiveData<List<Valve>> getSearchedPokemon() {
        return repository.getSearchedValves();
    }

    public void searchForValveByName(String name){
        repository.searchForValveByName(name);
    }

    public LiveData<Valve> getSearchedValveByName(){
        return repository.getSearchedValveByName();
    }


    public void searchForValves() {
        repository.searchForValveNames();
    }

    public int findId(List<Valve> valveList, String name){
        for (Valve valve:valveList) {
            if (valve.getName().equals(name)){
                return valve.getId();
            }
        }
        return 0;
    }

    public LiveData<List<String>> getSearchedValves() {
        return repository.getSearchedValvesNames();
    }

    private String convertDateTime(DatePicker datePicker, TimePicker timePicker){
        return (datePicker.getYear()+ "-" +
                datePicker.getMonth()+ "-" +
                datePicker.getDayOfMonth()+
                timePicker.getHour()+":"+
                timePicker.getMinute()+":00.000+00:00");
    }

    private void addCommand(int valve_id, boolean state, String time){
        Command command = new Command(valve_id,state);
        repository.addCommand(command, time);
    }

    public void addCommands(int valve_id, DatePicker datePicker, TimePicker startTimePicker, TimePicker endTimePicker){
        addCommand(valve_id,true,convertDateTime(datePicker,startTimePicker));
        addCommand(valve_id,false,convertDateTime(datePicker,endTimePicker));
    }
}