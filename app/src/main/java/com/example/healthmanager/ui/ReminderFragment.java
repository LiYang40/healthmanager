package com.example.healthmanager.ui;

import android.app.Application;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthmanager.databinding.FragmentReminderBinding;
import com.example.healthmanager.viewmodel.ReminderViewModel;
import com.example.healthmanager.viewmodel.ViewModelFactory;

public class ReminderFragment extends Fragment {

    private FragmentReminderBinding binding;
    private ReminderViewModel reminderViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentReminderBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Application application = requireActivity().getApplication();
        ViewModelFactory factory = new ViewModelFactory(application);
        reminderViewModel = new ViewModelProvider(this, factory).get(ReminderViewModel.class);

        // TODO: Use reminderViewModel to show reminders
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
