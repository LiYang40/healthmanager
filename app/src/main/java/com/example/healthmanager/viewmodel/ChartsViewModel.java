package com.example.healthmanager.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.healthmanager.data.HealthManagerDatabase;
import com.example.healthmanager.model.BMIRecord;
import com.example.healthmanager.model.SleepRecord;

import android.app.Application;
import java.util.List;

public class ChartsViewModel extends ViewModel {

    private final HealthManagerDatabase database;

    public ChartsViewModel(Application application) {
        database = HealthManagerDatabase.getInstance(application);
    }

    // BMI Records
    public LiveData<List<BMIRecord>> getBmiRecords() {
        return database.bmiDao().getAllRecords();
    }

    // Sleep Records
    public LiveData<List<SleepRecord>> getSleepRecords() {
        return database.sleepDao().getAllRecords();
    }
}