package com.example.smartgarden.Models;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smartgarden.R;

import java.util.ArrayList;

public class ValveAdapter extends RecyclerView.Adapter<ValveAdapter.ViewHolder> {

    private ArrayList<Valve> valves;
    private OnClickListener onClickListener;

    public ValveAdapter(ArrayList<Valve> valves) {
        this.valves = valves;
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
    }

    @Override
    public int getItemCount() {
        return valves.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView name;
        TextView description;
        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.valve_name);
            description = itemView.findViewById(R.id.valve_description);
            icon = itemView.findViewById(R.id.valve_icon);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(valves.get(getAdapterPosition()));
            });
        }
    }

    public interface OnClickListener {
        void onClick(Valve valve);
    }
}
