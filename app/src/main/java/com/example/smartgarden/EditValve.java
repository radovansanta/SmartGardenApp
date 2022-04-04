package com.example.smartgarden;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.textfield.TextInputEditText;

public class EditValve extends Fragment {

    private EditValveViewModel mViewModel;

    public static EditValve newInstance() {
        return new EditValve();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.valve_edit, container, false);
        TextInputEditText nameTextInput = view.findViewById(R.id.name_textview);



        return inflater.inflate(R.layout.valve_edit, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(EditValveViewModel.class);
        // TODO: Use the ViewModel
    }

}