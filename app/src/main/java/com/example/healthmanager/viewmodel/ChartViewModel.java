package com.example.healthmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.repository.HealthRepository;

import java.util.ArrayList;
import java.util.List;

public class ChartViewModel extends ViewModel {

    private final HealthRepository repository;

    public ChartViewModel(HealthRepository repository) {
        this.repository = repository;
    }

    public LiveData<List<Float>> getSleepData() {
        return Transformations.map(repository.getRecordsByCategory("Sleep"), records -> {
            List<Float> sleepData = new ArrayList<>();
            for (HealthRecord record : records) {
                sleepData.add((float) record.getSleep());
            }
            return sleepData;
        });
    }
}
