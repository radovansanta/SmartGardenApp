package com.example.smartgarden.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smartgarden.EditValve;
import com.example.smartgarden.R;
import com.example.smartgarden.Valve;
import com.example.smartgarden.ValveAdapter;
import com.example.smartgarden.ValveInfo;
import com.example.smartgarden.databinding.FragmentHomeBinding;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    RecyclerView valvesList;
    ValveAdapter valveAdapter;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        valvesList = getView().findViewById(R.id.rv);

        valvesList.hasFixedSize();
        valvesList.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ArrayList<Valve> valves = new ArrayList<>();

        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Trees","Fruit trees in garden. Apples, pears, apricots and plums",R.drawable.tree_icon));
        valves.add(new Valve("Flowers","Valve for flowers on balcony",R.drawable.flower_icon));
        valves.add(new Valve("Bushes","All bushes in the garden and in front of the house",R.drawable.bush_icon));

        valveAdapter = new ValveAdapter(valves);


        valveAdapter.setOnClickListener(valve -> {
            Toast.makeText(view.getContext(), valve.getName(), Toast.LENGTH_SHORT).show();

            Bundle bundle = new Bundle();
            bundle.putString("name",valve.getName());
            bundle.putString("description",valve.getDescription());
            bundle.putInt("iconId",valve.getIconId());
            this.setArguments(bundle);

            NavHostFragment.findNavController(this).navigate(R.id.editValve);


        });

        valvesList.setAdapter(valveAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}