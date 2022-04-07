package com.example.smartgarden.Models;

import androidx.annotation.NonNull;
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

public class ValveAdapter extends RecyclerView.Adapter<ValveAdapter.ViewHolder> {

    private ArrayList<Valve> valves;
    private OnClickListener onClickListener;
    ValveRepository repository;

    public ValveAdapter(ArrayList<Valve> valves) {
        this.valves = valves;
        repository = ValveRepository.getInstance();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.valve_item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(valves.get(position).getName());
        holder.description.setText(valves.get(position).getDescription());
        holder.icon.setImageResource(valves.get(position).getIconId());
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
                    } else {
                        repository.updateVale(valves.get(getAdapterPosition()).getId(),"off");
                    }
                }
            );
        }
    }

    public interface OnClickListener {
        void onClick(Valve valve);
    }
}
