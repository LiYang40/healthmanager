package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "rewards")
public class Reward {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int cost;
    private boolean redeemed;  // Add this field to mark as redeemed

    public Reward(String name, int cost, boolean redeemed) {
        this.name = name;
        this.cost = cost;
        this.redeemed = redeemed;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public boolean isRedeemed() {
        return redeemed;
    }

    public void setRedeemed(boolean redeemed) {
        this.redeemed = redeemed;
    }
}
