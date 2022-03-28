package com.example.smartgarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView valvesList;
    ValveAdapter valveAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        valvesList = findViewById(R.id.rv);
        valvesList.hasFixedSize();
        valvesList.setLayoutManager(new LinearLayoutManager(this));

        ArrayList<Valve> valves = new ArrayList<>();

        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));
        valves.add(new Valve("Grass","The hose for watering the grass behind house",R.drawable.grass_icon));

        valveAdapter = new ValveAdapter(valves);

        valveAdapter.setOnClickListener(valve -> {
            Toast.makeText(this, valve.getName(), Toast.LENGTH_SHORT).show();
        });

        valvesList.setAdapter(valveAdapter);
    }
}