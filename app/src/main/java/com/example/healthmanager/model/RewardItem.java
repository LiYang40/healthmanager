package com.example.healthmanager.model;

public class RewardItem {

    private String rewardName;
    private int rewardCost;

    public RewardItem(String rewardName, int rewardCost) {
        this.rewardName = rewardName;
        this.rewardCost = rewardCost;
    }

    // Getters and setters
    public String getRewardName() {
        return rewardName;
    }

    public void setRewardName(String rewardName) {
        this.rewardName = rewardName;
    }

    public int getRewardCost() {
        return rewardCost;
    }

    public void setRewardCost(int rewardCost) {
        this.rewardCost = rewardCost;
    }
}
