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

import com.example.healthmanager.databinding.FragmentHomeBinding;
import com.example.healthmanager.viewmodel.HealthViewModel;
import com.example.healthmanager.viewmodel.ViewModelFactory;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private HealthViewModel healthViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Application application = requireActivity().getApplication();
        ViewModelFactory factory = new ViewModelFactory(application);
        healthViewModel = new ViewModelProvider(this, factory).get(HealthViewModel.class);

        // TODO: Use healthViewModel to update UI
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
