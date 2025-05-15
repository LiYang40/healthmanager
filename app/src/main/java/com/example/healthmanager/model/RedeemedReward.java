package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "redeemed_reward")
public class RedeemedReward {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int cost;
    private String date;

    public RedeemedReward(String name, int cost, String date) {
        this.name = name;
        this.cost = cost;
        this.date = date;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public int getCost() { return cost; }
    public String getDate() { return date; }
}
