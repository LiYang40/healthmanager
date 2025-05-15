package com.example.healthmanager.repository;

import android.content.Context;

import com.example.healthmanager.data.local.AppDatabase;
import com.example.healthmanager.data.local.RewardDao;
import com.example.healthmanager.data.local.UserCoinsDao;
import com.example.healthmanager.model.Reward;
import com.example.healthmanager.model.UserCoins;

import java.util.List;

public class CoinsRepository {

    private final RewardDao rewardDao;
    private final UserCoinsDao userCoinsDao;

    public CoinsRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        rewardDao = db.rewardDao();
        userCoinsDao = db.userCoinsDao();
    }

    public List<Reward> getRewards() {
        return rewardDao.getAllRewards(); // Get all rewards from the database
    }

    public List<Reward> getRedeemedRewards() {
        return rewardDao.getRedeemedRewards(); // Get all redeemed rewards
    }


    public void redeemReward(int cost, String rewardName) {
        // Update the user's coin balance in the database
        UserCoins userCoins = userCoinsDao.getUserCoins(); // Fetch current coins
        int currentCoins = userCoins.getCoins();

        if (currentCoins >= cost) {
            // Deduct coins and update in the database
            userCoins.setCoins(currentCoins - cost);
            userCoinsDao.update(userCoins);
            // Update reward as redeemed
            rewardDao.updateRewardAsRedeemed(rewardName);
        }
    }

    public void updateUserCoins(int newCoinBalance) {
        UserCoins userCoins = userCoinsDao.getUserCoins();
        userCoins.setCoins(newCoinBalance);
        userCoinsDao.update(userCoins);
    }

    public int getUserCoins() {
        UserCoins userCoins = userCoinsDao.getUserCoins();
        if (userCoins == null) {
            // Initialize with 0 coins if no record exists
            userCoins = new UserCoins(0);
            userCoinsDao.insert(userCoins);
            return 0;
        } else {
            return userCoins.getCoins();
        }
    }

    public void addRedeemedReward(Reward reward) {
        reward.setRedeemed(true); // Make sure the reward object is marked as redeemed
        rewardDao.updateRewardAsRedeemed(reward.getName()); // Update in DB
    }

}
