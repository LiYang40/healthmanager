package com.example.healthmanager.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthmanager.model.RedeemedReward;

import java.util.List;

@Dao
public interface RedeemedRewardDao {

    @Insert
    void insert(RedeemedReward reward);

    @Query("SELECT * FROM redeemed_reward ORDER BY date DESC")
    LiveData<List<RedeemedReward>> getAllRedeemed();
}
