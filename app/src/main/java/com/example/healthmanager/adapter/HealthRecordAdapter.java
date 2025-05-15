package com.example.healthmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.model.HealthRecord;

import java.util.List;

public class HealthRecordAdapter extends RecyclerView.Adapter<HealthRecordAdapter.HealthRecordViewHolder> {

    private List<HealthRecord> healthRecords;

    // Constructor that accepts a List of HealthRecord
    public HealthRecordAdapter(List<HealthRecord> healthRecords) {
        this.healthRecords = healthRecords;
    }

    @NonNull
    @Override
    public HealthRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_health_record, parent, false);
        return new HealthRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HealthRecordViewHolder holder, int position) {
        HealthRecord record = healthRecords.get(position);
        holder.dateTextView.setText(record.getDate());
        holder.weightTextView.setText("Weight: " + record.getWeight());
        holder.waterTextView.setText("Water: " + record.getWater());
    }

    @Override
    public int getItemCount() {
        return healthRecords == null ? 0 : healthRecords.size();
    }

    // Method to set or update the list of health records dynamically
    public void setHealthRecords(List<HealthRecord> healthRecords) {
        this.healthRecords = healthRecords;
        notifyDataSetChanged(); // Notify the adapter that data has changed
    }

    // ViewHolder for each health record
    public static class HealthRecordViewHolder extends RecyclerView.ViewHolder {
        TextView dateTextView, weightTextView, waterTextView;

        public HealthRecordViewHolder(View itemView) {
            super(itemView);
            dateTextView = itemView.findViewById(R.id.record_date);
            weightTextView = itemView.findViewById(R.id.record_weight);
            waterTextView = itemView.findViewById(R.id.record_water);
        }
    }
}
