package com.example.smartgarden.Models;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smartgarden.Networking.Repositories.ValveRepository;
import com.example.smartgarden.R;

import java.util.ArrayList;
import java.util.List;

public class ValveAdapter extends RecyclerView.Adapter<ValveAdapter.ViewHolder> {

    private List<Valve> valves;
    private OnClickListener onClickListener;
    ValveRepository repository;
    private View view;
    private LifecycleOwner lifecycle;

    public ValveAdapter(LifecycleOwner lifecycle) {
        valves = new ArrayList<>();
        repository = ValveRepository.getInstance();
        this.lifecycle = lifecycle;
    }

    public void setData(List<Valve> data){
        valves = data;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.valve_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(valves.get(position).getName());
        holder.description.setText(valves.get(position).getDescription());
        //holder.icon.setImageResource(valves.get(position).getIconId());
        holder.aSwitch.setChecked(valves.get(position).getState());
    }

    @Override
    public int getItemCount() {
        return valves.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView icon;
        Switch aSwitch;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.valve_name);
            description = itemView.findViewById(R.id.valve_description);
            icon = itemView.findViewById(R.id.valve_icon);
            aSwitch = itemView.findViewById(R.id.valve_switch);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(valves.get(getAdapterPosition()));
            });

            aSwitch.setOnClickListener(v -> {
                    if (aSwitch.isChecked()) {
                        repository.updateVale(valves.get(getAdapterPosition()).getId(),"on");
                        repository.getUpdateValveResponse().observe(lifecycle, valve -> {
                            Toast.makeText(itemView.getContext(), valve.getMessage() , Toast.LENGTH_SHORT).show();
                            if (!valve.getMessage().equals("Valve should be changed to on")){
                                aSwitch.setChecked(false);
                            }
                        });
                    } else {
                        repository.updateVale(valves.get(getAdapterPosition()).getId(),"off");
                        repository.getUpdateValveResponse().observe(lifecycle, valve -> {
                            Toast.makeText(itemView.getContext(), valve.getMessage() , Toast.LENGTH_SHORT).show();
                            if (!valve.getMessage().equals("Valve should be changed to off")){
                                aSwitch.setChecked(true);
                            }
                        });
                    }
                }
            );
        }
    }

    public interface OnClickListener {
        void onClick(Valve valve);
    }
}
