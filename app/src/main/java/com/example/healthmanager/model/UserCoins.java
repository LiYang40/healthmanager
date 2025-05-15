package com.example.healthmanager.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_coins")
public class UserCoins {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int coins;

    public UserCoins(int coins) {
        this.coins = coins;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}
