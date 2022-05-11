package com.example.smartgarden.Fragments;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartgarden.Models.IconAdapter;
import com.example.smartgarden.R;
import com.example.smartgarden.ViewModels.HomeViewModel;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

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
        EditText nameText = view.findViewById(R.id.editValveName);
        EditText descriptionText = view.findViewById(R.id.editValveDescription);
        TextView nameTopText = view.findViewById(R.id.valve_name_top);
        TextView stateText = view.findViewById(R.id.state);
        ImageView iconButton = view.findViewById(R.id.editIcon);





        mViewModel.getSelected().observe(getViewLifecycleOwner(), valve -> {
            nameText.setText(valve.getName());
            descriptionText.setText(valve.getDescription());
            nameTopText.setText("EDIT "+valve.getName().toUpperCase());

            if (valve.getState()){
                stateText.setText("Valve is opened");
            } else stateText.setText("Valve is closed");

            if (valve.getIconId()==1)iconButton.setImageDrawable(getResources().getDrawable(R.drawable.grass_icon));
            if (valve.getIconId()==2)iconButton.setImageDrawable(getResources().getDrawable(R.drawable.tree_icon));
            if (valve.getIconId()==3)iconButton.setImageDrawable(getResources().getDrawable(R.drawable.flower_icon));
            if (valve.getIconId()==4)iconButton.setImageDrawable(getResources().getDrawable(R.drawable.bush_icon));

        });

        Button btnEditValve = view.findViewById(R.id.buttonEditValve);
        btnEditValve.setVisibility(view.INVISIBLE);

        iconButton.setOnClickListener(onIcon -> {
            NavHostFragment.findNavController(this).navigate(R.id.editValveIcon);
        });

        nameText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mViewModel.getSelected().observe(getViewLifecycleOwner(), valve -> {
                    if (!editable.toString().equals(valve.getName()))
                        valve.setName(editable.toString());
                        btnEditValve.setVisibility(View.VISIBLE);
                });

            }
        });
        descriptionText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mViewModel.getSelected().observe(getViewLifecycleOwner(), valve -> {
                    if (!editable.toString().equals(valve.getDescription()))
                        valve.setDescription(editable.toString());
                        btnEditValve.setVisibility(View.VISIBLE);
                });

            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Resume");
        mViewModel.getSelected().observe(getViewLifecycleOwner(), valve -> {
            System.out.println(valve.getIconId());

        });
    }
}