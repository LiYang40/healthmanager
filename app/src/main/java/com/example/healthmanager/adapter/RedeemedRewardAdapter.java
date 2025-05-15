package com.example.healthmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.model.Reward;

import java.util.List;

public class RedeemedRewardAdapter extends RecyclerView.Adapter<RedeemedRewardAdapter.RedeemedRewardViewHolder> {

    private List<Reward> redeemedRewards;

    @NonNull
    @Override
    public RedeemedRewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_redeemed_reward, parent, false);
        return new RedeemedRewardViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RedeemedRewardViewHolder holder, int position) {
        Reward reward = redeemedRewards.get(position);
        holder.rewardNameTextView.setText(reward.getName());
        holder.rewardCostTextView.setText(String.valueOf(reward.getCost()));
    }

    @Override
    public int getItemCount() {
        return redeemedRewards != null ? redeemedRewards.size() : 0;
    }

    // Method to update the list of redeemed rewards
    public void submitList(List<Reward> rewards) {
        redeemedRewards = rewards;
        notifyDataSetChanged();
    }

    // ViewHolder for the redeemed reward item
    public static class RedeemedRewardViewHolder extends RecyclerView.ViewHolder {
        TextView rewardNameTextView;
        TextView rewardCostTextView;

        public RedeemedRewardViewHolder(View itemView) {
            super(itemView);
            rewardNameTextView = itemView.findViewById(R.id.rewardNameTextView);
            rewardCostTextView = itemView.findViewById(R.id.rewardCostTextView);
        }
    }
}
