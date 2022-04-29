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

    @SuppressLint("ResourceType")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        valvesList = getView().findViewById(R.id.rv);
        valvesList.hasFixedSize();
        valvesList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        /*valves.add(new Valve(1,"Grass","The hose for watering the grass behind house",R.drawable.grass_icon,true));
        valves.add(new Valve(2,"Trees","Fruit trees in garden. Apples, pears, apricots and plums",R.drawable.tree_icon,true));
        valves.add(new Valve(3,"Flowers","Valve for flowers on balcony",R.drawable.flower_icon,false));
        valves.add(new Valve(4,"Bushes","All bushes in the garden and in front of the house",R.drawable.bush_icon,false));
        */

        valveAdapter = new ValveAdapter();
        valvesList.setAdapter(valveAdapter);

        homeViewModel.searchForPokemon();
        homeViewModel.getSearchedPokemon().observe(getViewLifecycleOwner(), valve -> {
            valveAdapter.setData(valve);
        });



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