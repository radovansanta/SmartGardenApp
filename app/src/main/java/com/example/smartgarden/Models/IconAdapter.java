package com.example.smartgarden.Models;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.drawable.Drawable;
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

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    private List<Drawable> valves;
    private OnClickListener onClickListener;
    ValveRepository repository;
    private View view;
    private LifecycleOwner lifecycle;

    public IconAdapter(LifecycleOwner lifecycle) {
        valves = new ArrayList<Drawable>();
        repository = ValveRepository.getInstance();
        this.lifecycle = lifecycle;
    }

    public void setData(List<Drawable> data){
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
        view = inflater.inflate(R.layout.icon_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.icon.setImageDrawable(valves.get(position));
    }

    @Override
    public int getItemCount() {
        return valves.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView icon;

        ViewHolder(View itemView) {
            super(itemView);
            icon = itemView.findViewById(R.id.iconChange);
            itemView.setOnClickListener(v -> {
                onClickListener.onClick(getAdapterPosition());
            });
        }
    }

    public interface OnClickListener {
        void onClick(Integer iconPosition);
    }
}
