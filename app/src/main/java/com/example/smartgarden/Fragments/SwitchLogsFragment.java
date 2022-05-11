package com.example.smartgarden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.smartgarden.ViewModels.SchedulerLogsViewModel;
import com.example.smartgarden.databinding.SwitchLogsBinding;

public class SwitchLogsFragment extends Fragment {

    private SwitchLogsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SchedulerLogsViewModel galleryViewModel =
                new ViewModelProvider(this).get(SchedulerLogsViewModel.class);

        binding = SwitchLogsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}