package com.example.smartgarden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.smartgarden.Models.Command;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.R;
import com.example.smartgarden.ViewModels.SlideshowViewModel;
import com.example.smartgarden.databinding.FragmentSlideshowBinding;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private FragmentSlideshowBinding binding;
    private Button addCommandsButton;
    private DatePicker datePicker;
    private TimePicker startTimePicker;
    private TimePicker endTimePicker;
    private SlideshowViewModel slideshowViewModel;
    private Spinner spinner;
    private List<Valve> valvesList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel = new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        spinner = (Spinner) root.findViewById(R.id.planets_spinner);
        addCommandsButton = root.findViewById(R.id.buttonCreateCommands);
        datePicker = root.findViewById(R.id.datePicker);
        startTimePicker = root.findViewById(R.id.startTimePicker);
        endTimePicker = root.findViewById(R.id.endTimePicker);


        List<String> data = new ArrayList<>();

        slideshowViewModel.searchForPokemon();
        slideshowViewModel.getSearchedPokemon().observe(getViewLifecycleOwner(), valve -> {
            valvesList.clear();
            valvesList.addAll(valve);
        });


        slideshowViewModel.searchForValves();
        slideshowViewModel.getSearchedValves().observe(getViewLifecycleOwner(), valve -> {
            data.clear();
            data.addAll(valve);
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(dataAdapter);
        });

        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        addCommandsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                slideshowViewModel.addCommands(
                        slideshowViewModel.findId(valvesList,spinner.getSelectedItem().toString()),
                        datePicker,
                        startTimePicker,
                        endTimePicker);

                NavHostFragment.findNavController(getParentFragment()).navigate(R.id.nav_home);

                /*
                slideshowViewModel.getSearchedValveByName().observe(getViewLifecycleOwner(), valve -> {
                    System.out.println("Something");
                    slideshowViewModel.addCommands(valve.getId(), datePicker,startTimePicker,endTimePicker);
                });

                 */

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}