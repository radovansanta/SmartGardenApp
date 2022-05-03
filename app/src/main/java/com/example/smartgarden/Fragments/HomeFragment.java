package com.example.smartgarden.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smartgarden.R;
import com.example.smartgarden.Models.Valve;
import com.example.smartgarden.Models.ValveAdapter;
import com.example.smartgarden.ViewModels.HomeViewModel;
import com.example.smartgarden.databinding.FragmentHomeBinding;
import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView valvesList;
    ValveAdapter valveAdapter;
    private FragmentHomeBinding binding;
    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel = new ViewModelProvider(requireActivity()).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    public void loadData(){
        valveAdapter = new ValveAdapter();
        valvesList.setAdapter(valveAdapter);

        homeViewModel.searchForPokemon();
        homeViewModel.getSearchedPokemon().observe(getViewLifecycleOwner(), valve -> {
            valveAdapter.setData(valve);
        });
    }

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        valvesList = getView().findViewById(R.id.rv);
        valvesList.hasFixedSize();
        valvesList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        loadData();

        valveAdapter.setOnClickListener(valve -> {
            //Toast.makeText(view.getContext(), , Toast.LENGTH_SHORT).show();
            homeViewModel.select(valve.getName());
            NavHostFragment.findNavController(this).navigate(R.id.editValve);


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