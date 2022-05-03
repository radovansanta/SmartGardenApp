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


    public void searchForValves() {
        repository.searchForValveNames();
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
        String startTime = convertDateTime(datePicker,startTimePicker);
        String endTime = convertDateTime(datePicker,endTimePicker);
        addCommand(valve_id,true,startTime);
        addCommand(valve_id,false,endTime);
    }
}