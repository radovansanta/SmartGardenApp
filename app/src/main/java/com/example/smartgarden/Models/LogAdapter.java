package com.example.smartgarden.Models;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
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
import com.example.smartgarden.ViewModels.SchedulerLogsViewModel;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        System.out.println(logs.get(position).getCommand().getDate_time());
        Map<String, String> DateTimeMap = convertStringToStringDate(logs.get(position).getCommand().getDate_time());
        holder.log_date.setText(DateTimeMap.get("date"));
        holder.log_day_name.setText(DateTimeMap.get("name"));
        holder.log_time.setText(DateTimeMap.get("time"));

        if (logs.get(position).getCommand().getState()){
            holder.valve_state.setText("ON");
            holder.valve_state_img.setImageDrawable(holder.valve_state_img.getResources().getDrawable(R.drawable.opened));
        }
        else {
            holder.valve_state.setText("OFF");
            holder.valve_state_img.setImageDrawable(holder.valve_state_img.getResources().getDrawable(R.drawable.closed));
        }

        //TODO: Use Switch statement instead
        if (logs.get(position).getValve().getIconId()==1)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.grass_icon));
        if (logs.get(position).getValve().getIconId()==2)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.tree_icon));
        if (logs.get(position).getValve().getIconId()==3)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.flower_icon));
        if (logs.get(position).getValve().getIconId()==4)holder.valve_icon.setImageDrawable(holder.itemView.getResources().getDrawable(R.drawable.bush_icon));

    }

    @Override
    public int getItemCount() {
        return logs.size();
    }

    //2022-10-18T10:57:00.000+00:00

    private Map<String, String> convertStringToStringDate(String timestamp) {
        Map<String, String> result = new HashMap<String, String>();
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'.000+00:00'").parse(timestamp);
        } catch (ParseException e) {
            result.put("date","Unknown date");
            result.put("time","Unknown time");
            return result;
        }

        SimpleDateFormat dayNameFormat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
        System.out.println(dayNameFormat.format(date));

        DateFormat timeFormat = new SimpleDateFormat("HH:mm");
        DateFormat yearFormat = new SimpleDateFormat("yyyy");
        //TODO: Deal with time zone +2:00
        result.put("name",dayNameFormat.format(date));
        result.put("date", date.getDate()+"."+(date.getMonth()+1) +"."+yearFormat.format(date));
        result.put("time",timeFormat.format(date));

        return result;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        TextView valve_name;
        TextView log_day_name;
        TextView log_date;
        TextView log_time;
        TextView valve_state;
        ImageView valve_icon;
        ImageView valve_state_img;

        ViewHolder(View itemView) {
            super(itemView);

            valve_name = itemView.findViewById(R.id.scheduler_valve_name);
            log_date = itemView.findViewById(R.id.scheduler_date);
            log_day_name = itemView.findViewById(R.id.scheduler_day_name);
            log_time = itemView.findViewById(R.id.scheduler_time);
            valve_state = itemView.findViewById(R.id.scheduler_valve_state);
            valve_icon = itemView.findViewById(R.id.scheduler_valve_icon);
            valve_state_img = itemView.findViewById(R.id.currentState);

            itemView.setOnClickListener(v -> {
                onClickListener.onClick(logs.get(getAdapterPosition()));
            });

        }
    }

    public interface OnClickListener {
        void onClick(Log valve);
    }
}
