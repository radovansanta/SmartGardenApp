package com.example.smartgarden.Fragments;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgarden.Models.IconAdapter;
import com.example.smartgarden.R;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.ViewModels.HomeViewModel;
import com.example.smartgarden.databinding.FragmentEditValveIconBinding;
import com.example.smartgarden.databinding.FragmentHomeBinding;
import java.util.ArrayList;

public class EditValveIcon extends Fragment {

    RecyclerView iconsList;
    IconAdapter iconsAdapter;
    private FragmentEditValveIconBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding = FragmentEditValveIconBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        iconsList = root.findViewById(R.id.rv_icons);
        loadData();

        iconsList.hasFixedSize();
        int numberOfColumns = 3;
        iconsList.setLayoutManager(new GridLayoutManager(root.getContext(), numberOfColumns));


        iconsAdapter.setOnClickListener(iconPosition -> {
            homeViewModel.getSelected().observe(getViewLifecycleOwner(), valve -> {
                valve.setIcon(iconPosition+1);
            });
            getActivity().onBackPressed();
        });

        return root;
    }

    public void loadData(){
        iconsAdapter = new IconAdapter(getViewLifecycleOwner());
        iconsList.setAdapter(iconsAdapter);

        ArrayList<Drawable> dat = new ArrayList<>();
        dat.add(getResources().getDrawable( R.drawable.grass_icon ));
        dat.add(getResources().getDrawable( R.drawable.tree_icon ));
        dat.add(getResources().getDrawable( R.drawable.flower_icon ));
        dat.add(getResources().getDrawable( R.drawable.bush_icon ));

        iconsAdapter.setData(dat);
    }


    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


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