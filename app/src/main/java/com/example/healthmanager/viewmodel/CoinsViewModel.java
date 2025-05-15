package com.example.healthmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthmanager.model.Reward;
import com.example.healthmanager.repository.CoinsRepository;

import java.util.List;

public class CoinsViewModel extends ViewModel {

    private final CoinsRepository coinsRepository;
    private final MutableLiveData<List<Reward>> availableRewards = new MutableLiveData<>();
    private final MutableLiveData<Integer> totalCoins = new MutableLiveData<>();
    private final MutableLiveData<List<Reward>> redeemedRewards = new MutableLiveData<>();

    public CoinsViewModel(CoinsRepository repository) {
        this.coinsRepository = repository;
        loadAvailableRewards();
        loadUserCoins();
        loadRedeemedRewards();
    }

    public LiveData<List<Reward>> getAvailableRewards() {
        return availableRewards;
    }

    public LiveData<Integer> getTotalCoins() {
        return totalCoins;
    }

    public LiveData<List<Reward>> getRedeemedRewards() {
        return redeemedRewards;
    }

    private void loadAvailableRewards() {
        List<Reward> rewards = coinsRepository.getRewards();
        availableRewards.setValue(rewards);
    }

    private void loadUserCoins() {
        Integer userCoins = coinsRepository.getUserCoins();
        totalCoins.setValue(userCoins);  // Ensure this is an Integer
    }

    private void loadRedeemedRewards() {
        List<Reward> redeemed = coinsRepository.getRedeemedRewards();
        redeemedRewards.setValue(redeemed);
    }

    public String redeemReward(int cost, String rewardName) {
        Integer currentCoins = totalCoins.getValue();
        if (currentCoins != null && currentCoins >= cost) {
            coinsRepository.updateUserCoins(currentCoins - cost);
            coinsRepository.addRedeemedReward(new Reward(rewardName, cost, true));  // Ensure the reward is marked as redeemed
            loadUserCoins();
            loadRedeemedRewards();
            return "You redeemed: " + rewardName;
        } else {
            return "Not enough coins to redeem this reward.";
        }
    }
}
