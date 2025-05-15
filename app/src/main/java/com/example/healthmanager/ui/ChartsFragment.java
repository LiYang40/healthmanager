package com.example.healthmanager.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthmanager.R;
import com.example.healthmanager.model.BMIRecord;
import com.example.healthmanager.model.SleepRecord;
import com.example.healthmanager.viewmodel.ChartsViewModel;

import java.util.List;

public class ChartsFragment extends Fragment {

    private ChartsViewModel chartsViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_charts, container, false);

        // Initialize ViewModel
        chartsViewModel = new ViewModelProvider(this).get(ChartsViewModel.class);

        // Observe BMI Records
        chartsViewModel.getBmiRecords().observe(getViewLifecycleOwner(), this::displayBmiRecords);

        // Observe Sleep Records
        chartsViewModel.getSleepRecords().observe(getViewLifecycleOwner(), this::displaySleepRecords);

        return view;
    }

    private void displayBmiRecords(List<BMIRecord> bmiRecords) {
        for (BMIRecord record : bmiRecords) {
            Log.d("BMIRecord", "Date: " + record.getDate() +
                    ", Weight: " + record.getWeight() +
                    ", Height: " + record.getHeight() +
                    ", BMI: " + record.getBmi());
        }
    }

    private void displaySleepRecords(List<SleepRecord> sleepRecords) {
        for (SleepRecord record : sleepRecords) {
            Log.d("SleepRecord", "Date: " + record.getDate() +
                    ", Target Sleep Hours: " + record.getTargetSleepHours() +
                    ", Actual Sleep Hours: " + record.getActualSleepHours());
        }
    }
}