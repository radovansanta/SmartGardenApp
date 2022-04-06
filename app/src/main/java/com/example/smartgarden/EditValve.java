package com.example.smartgarden;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.smartgarden.ui.home.HomeViewModel;
import com.google.android.material.textfield.TextInputEditText;

public class EditValve extends Fragment {

    private HomeViewModel mViewModel;

    public static EditValve newInstance() {
        return new EditValve();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);


        return inflater.inflate(R.layout.valve_edit, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        TextInputEditText nameText = view.findViewById(R.id.name_textview);

        mViewModel.getSelected().observe(getViewLifecycleOwner(), users -> {
            nameText.setText(users);
        });

    }

}