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

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {

    private List<Log> logs;
    private OnClickListener onClickListener;
    ValveRepository repository;
    private View view;
    private LifecycleOwner lifecycle;

    public LogAdapter(LifecycleOwner lifecycle) {
        logs = new ArrayList<>();
        repository = ValveRepository.getInstance();
        this.lifecycle = lifecycle;
    }

    public void setData(List<Log> data){
        logs = data;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.onClickListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        view = inflater.inflate(R.layout.scheduler_log_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.valve_name.setText(logs.get(position).getValve().getName());
        //holder.log_date.setText(logs.get(position).getCommand().getDate_time().getTime().getDate());
        //holder.log_time.setText(
                //logs.get(position).getCommand().getDate_time().getTime().getHours()+ ":" +
                   //     logs.get(position).getCommand().getDate_time().getTime().getMinutes());

        if (logs.get(position).getCommand().getState())
            holder.valve_state.setText("ON");
        else holder.valve_state.setText("FALSE");

        if (logs.get(position).getValve().getIconId()==1)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.grass_icon));
        if (logs.get(position).getValve().getIconId()==2)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.tree_icon));
        if (logs.get(position).getValve().getIconId()==3)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.flower_icon));
        if (logs.get(position).getValve().getIconId()==4)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.bush_icon));

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView valve_name;
        TextView log_date;
        TextView log_time;
        TextView valve_state;
        ImageView valve_icon;

        ViewHolder(View itemView) {
            super(itemView);

            valve_name = itemView.findViewById(R.id.scheduler_valve_name);
            log_date = itemView.findViewById(R.id.scheduler_date);
            log_time = itemView.findViewById(R.id.scheduler_time);
            valve_state = itemView.findViewById(R.id.scheduler_valve_state);
            valve_icon = itemView.findViewById(R.id.scheduler_valve_icon);


            itemView.setOnClickListener(v -> {
                //onClickListener.onClick(logs.get(getAdapterPosition()));
            });

        }
    }

    public interface OnClickListener {
        void onClick(Valve valve);
    }
}
