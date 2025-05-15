package com.example.healthmanager.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthmanager.repository.CoinsRepository;
import com.example.healthmanager.repository.HealthRepository;
import com.example.healthmanager.repository.ReminderRepository;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final Application application;

    public ViewModelFactory(Application application) {
        this.application = application;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(HealthViewModel.class)) {
            return (T) new HealthViewModel(new HealthRepository(application));
        } else if (modelClass.isAssignableFrom(ReminderViewModel.class)) {
            return (T) new ReminderViewModel(new ReminderRepository(application));
        } else if (modelClass.isAssignableFrom(CoinsViewModel.class)) {
            return (T) new CoinsViewModel(new CoinsRepository(application));
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
