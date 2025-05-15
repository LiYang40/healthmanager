package com.example.healthmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.repository.HealthRepository;

import java.util.ArrayList;
import java.util.List;

public class HealthViewModel extends ViewModel {

    private final HealthRepository repository;
    private final LiveData<Integer> totalCoins;
    private final LiveData<List<HealthRecord>> allRecords;
    private final MutableLiveData<List<String>> adviceList;

    private final Observer<List<HealthRecord>> recordObserver = new Observer<List<HealthRecord>>() {
        @Override
        public void onChanged(List<HealthRecord> healthRecords) {
            generateHealthAdvice(); // Generate new advice when data changes
        }
    };

    public HealthViewModel(HealthRepository repository) {
        this.repository = repository;
        this.totalCoins = repository.getTotalCoins();
        this.allRecords = repository.getAllRecords();
        this.adviceList = new MutableLiveData<>();

        this.allRecords.observeForever(recordObserver);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        this.allRecords.removeObserver(recordObserver); // Prevent memory leak
    }

    public LiveData<Integer> getTotalCoins() {
        return totalCoins;
    }

    public LiveData<List<HealthRecord>> getAllRecords() {
        return allRecords;
    }

    public LiveData<HealthRecord> getLatestRecord() {
        return repository.getLatestRecord();
    }

    public LiveData<List<String>> getAdviceList() {
        return adviceList;
    }

    private void setAdviceList(List<String> advice) {
        adviceList.setValue(advice);
    }

    public void generateHealthAdvice() {
        List<String> advice = new ArrayList<>();

        List<HealthRecord> records = allRecords.getValue();
        if (records == null || records.isEmpty()) {
            adviceList.setValue(advice);
            return;
        }

        HealthRecord latest = records.get(records.size() - 1);  // Get latest record

        double weight = latest.getWeight();
        double height = latest.getHeight(); // in meters
        double bmi = height > 0 ? weight / (height * height) : 0;

        if (bmi < 18.5) {
            advice.add("Your BMI is low. Consider gaining weight for better health.");
        } else if (bmi > 25) {
            advice.add("Your BMI is high. Consider managing your weight.");
        }

        if (latest.getWater() < 2.0) {
            advice.add("Drink more water to stay hydrated.");
        }

        if (latest.getCalories() < 1500) {
            advice.add("Your calorie intake is low. Consider eating more balanced meals.");
        } else if (latest.getCalories() > 3000) {
            advice.add("You're consuming high calories. Watch your diet.");
        }

        String activity = latest.getActivityLevel();
        if (activity != null && activity.equalsIgnoreCase("Low")) {
            advice.add("Increase your physical activity to stay fit.");
        }

        double sleep = latest.getSleep();
        if (sleep < 6) {
            advice.add("Try to get more sleep for better recovery.");
        } else if (sleep > 9) {
            advice.add("Too much sleep may affect your energy. Aim for 7–9 hours.");
        }

        // Update LiveData
        adviceList.setValue(advice);
    }
}
