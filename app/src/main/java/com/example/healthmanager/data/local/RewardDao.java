package com.example.healthmanager.data.local;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.healthmanager.model.Reward;

import java.util.List;

@Dao
public interface RewardDao {

    @Insert
    void insert(Reward reward);

    @Query("SELECT * FROM rewards")
    List<Reward> getAllRewards();

    @Query("SELECT * FROM rewards WHERE redeemed = 1")
    List<Reward> getRedeemedRewards();

    @Update
    void updateRewardAsRedeemed(Reward reward);

    @Query("UPDATE rewards SET redeemed = 1 WHERE name = :rewardName")
    void updateRewardAsRedeemed(String rewardName);
}
