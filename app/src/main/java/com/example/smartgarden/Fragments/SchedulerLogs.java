package com.example.smartgarden.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgarden.Models.LogAdapter;
import com.example.smartgarden.R;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.ViewModels.HomeViewModel;
import com.example.smartgarden.ViewModels.SchedulerLogsViewModel;
import com.example.smartgarden.databinding.FragmentHomeBinding;
import com.example.smartgarden.databinding.FragmentSchedulerLogsBinding;

import java.util.ArrayList;

public class SchedulerLogs extends Fragment {

    RecyclerView logsList;
    LogAdapter logAdapter;
    Button upcomingButton;
    Button previousButton;
    private FragmentSchedulerLogsBinding binding;
    private SchedulerLogsViewModel schedulerLogsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        schedulerLogsViewModel = new ViewModelProvider(requireActivity()).get(SchedulerLogsViewModel.class);

        binding = FragmentSchedulerLogsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void loadUpcomingData(){
        logAdapter = new LogAdapter(getViewLifecycleOwner());
        logsList.setAdapter(logAdapter);

        System.out.println("Getting Logs");
        schedulerLogsViewModel.searchForSchedulerLogsUpcoming();
        schedulerLogsViewModel.getSchedulerLogsUpcoming().observe(getViewLifecycleOwner(), logs -> {
            logAdapter.setData(logs);
        });
    }

    public void loadPreviousData(){
        logAdapter = new LogAdapter(getViewLifecycleOwner());
        logsList.setAdapter(logAdapter);

        System.out.println("Getting Logs");
        schedulerLogsViewModel.searchForSchedulerLogsPrevious();
        schedulerLogsViewModel.getSchedulerLogsPrevious().observe(getViewLifecycleOwner(), logs -> {
            logAdapter.setData(logs);
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        logsList = view.findViewById(R.id.rvSchedulerSwitchLog);
        logsList.hasFixedSize();
        logsList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        upcomingButton = view.findViewById(R.id.buttonUpcoming);
        previousButton = view.findViewById(R.id.buttonPrevious);

        loadUpcomingData();

        logAdapter.setOnClickListener(valve -> {
            //Toast.makeText(view.getContext(), , Toast.LENGTH_SHORT).show();w
        });

        upcomingButton.setOnClickListener(click -> {
            loadUpcomingData();
        });

        previousButton.setOnClickListener(click -> {
            loadPreviousData();
        });

    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}