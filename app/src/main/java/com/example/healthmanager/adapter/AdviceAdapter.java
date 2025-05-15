package com.example.healthmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;

import java.util.List;

public class AdviceAdapter extends RecyclerView.Adapter<AdviceAdapter.AdviceViewHolder> {

    private List<String> adviceList;

    public void setAdviceList(List<String> adviceList) {
        this.adviceList = adviceList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AdviceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_advice, parent, false);
        return new AdviceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdviceViewHolder holder, int position) {
        holder.adviceTextView.setText(adviceList.get(position));
    }

    @Override
    public int getItemCount() {
        return adviceList.size();
    }

    public static class AdviceViewHolder extends RecyclerView.ViewHolder {
        TextView adviceTextView;

        public AdviceViewHolder(View itemView) {
            super(itemView);
            adviceTextView = itemView.findViewById(R.id.advice_title);
        }
    }
}
