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

import com.example.healthmanager.databinding.FragmentRewardBinding;
import com.example.healthmanager.viewmodel.CoinsViewModel;
import com.example.healthmanager.viewmodel.ViewModelFactory;

public class RewardFragment extends Fragment {

    private FragmentRewardBinding binding;
    private CoinsViewModel coinViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentRewardBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Application application = requireActivity().getApplication();
        ViewModelFactory factory = new ViewModelFactory(application);
        coinViewModel = new ViewModelProvider(this, factory).get(CoinsViewModel.class);

        coinViewModel.getTotalCoins().observe(getViewLifecycleOwner(), coins -> {
            binding.textCoinBalance.setText(String.valueOf(coins));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
