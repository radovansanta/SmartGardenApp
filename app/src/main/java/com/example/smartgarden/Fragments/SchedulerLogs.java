package com.example.smartgarden.Fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    LinearLayout upcomingButton;
    LinearLayout previousButton;
    private FragmentSchedulerLogsBinding binding;
    private SchedulerLogsViewModel schedulerLogsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        schedulerLogsViewModel = new ViewModelProvider(requireActivity()).get(SchedulerLogsViewModel.class);

        binding = FragmentSchedulerLogsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void loadUpcomingData(View view){
        logAdapter = new LogAdapter(getViewLifecycleOwner());
        logsList.setAdapter(logAdapter);

        schedulerLogsViewModel.searchForSchedulerLogsUpcoming();
        schedulerLogsViewModel.getSchedulerLogsUpcoming().observe(getViewLifecycleOwner(), logs -> {
            logAdapter.setData(logs);
        });

        logAdapter.setOnClickListener(log -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("Are you sure you want to delete:");
            builder.setMessage("Upcoming schedule to turn " + log.getCommand().getState() + " the " + log.getValve().getName());
            builder.setPositiveButton("Delete",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            schedulerLogsViewModel.deleteLog(log.getCommand().getCommand_id());
                            Toast.makeText(view.getContext(), "Schedule deleted successfully", Toast.LENGTH_SHORT).show();
                            loadUpcomingData(view);
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    public void loadPreviousData(View view){
        logAdapter = new LogAdapter(getViewLifecycleOwner());
        logsList.setAdapter(logAdapter);

        schedulerLogsViewModel.searchForSchedulerLogsPrevious();
        schedulerLogsViewModel.getSchedulerLogsPrevious().observe(getViewLifecycleOwner(), logs -> {
            logAdapter.setData(logs);
        });

        logAdapter.setOnClickListener(log -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setCancelable(true);
            builder.setTitle("Are you sure you want to delete:");
            builder.setMessage("Previous command that turns " + log.getCommand().getState() + " the " + log.getValve().getName());
            builder.setPositiveButton("Delete",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            schedulerLogsViewModel.deleteLog(log.getCommand().getCommand_id());
                            Toast.makeText(view.getContext(), "Command deleted successfully", Toast.LENGTH_SHORT).show();
                            loadPreviousData(view);
                        }
                    });
            builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });

            AlertDialog dialog = builder.create();
            dialog.show();
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

        loadUpcomingData(view);

        upcomingButton.setOnClickListener(click -> {
            loadUpcomingData(view);
        });

        previousButton.setOnClickListener(click -> {
            loadPreviousData(view);
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