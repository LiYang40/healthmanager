package com.example.healthmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.model.Reward;

import java.util.List;

public class RewardAdapter extends RecyclerView.Adapter<RewardAdapter.RewardViewHolder> {

    private List<Reward> rewardList;
    private OnRedeemClickListener onRedeemClickListener;

    public void setRewardList(List<Reward> rewardList) {
        this.rewardList = rewardList;
        notifyDataSetChanged();
    }

    public void setOnRedeemClickListener(OnRedeemClickListener listener) {
        this.onRedeemClickListener = listener;
    }

    @NonNull
    @Override
    public RewardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reward, parent, false);
        return new RewardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RewardViewHolder holder, int position) {
        Reward reward = rewardList.get(position);
        holder.rewardName.setText(reward.getName());
        holder.rewardCost.setText("Cost: " + reward.getCost() + " coins");
        holder.redeemButton.setOnClickListener(v -> {
            if (onRedeemClickListener != null) {
                onRedeemClickListener.onRedeemClick(reward);
            }
        });
    }

    @Override
    public int getItemCount() {
        return rewardList != null ? rewardList.size() : 0;
    }

    public interface OnRedeemClickListener {
        void onRedeemClick(Reward reward);
    }

    static class RewardViewHolder extends RecyclerView.ViewHolder {
        TextView rewardName;
        TextView rewardCost;
        Button redeemButton;

        public RewardViewHolder(View itemView) {
            super(itemView);
            rewardName = itemView.findViewById(R.id.rewardName);
            rewardCost = itemView.findViewById(R.id.rewardCost);
            redeemButton = itemView.findViewById(R.id.redeemButton);
        }
    }
}
