package com.example.healthmanager.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.healthmanager.model.UserCoins;

@Dao
public interface UserCoinsDao {

    // Get the user's coins (assuming only one record exists for the user)
    @Query("SELECT * FROM user_coins LIMIT 1")
    UserCoins getUserCoins();

    // Insert a new UserCoins record
    @Insert
    void insert(UserCoins userCoins);

    // Update the UserCoins record
    @Update
    void update(UserCoins userCoins);
}
