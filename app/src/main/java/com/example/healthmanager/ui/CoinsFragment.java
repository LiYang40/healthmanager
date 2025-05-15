package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.Observer;

import com.example.healthmanager.R;
import com.example.healthmanager.viewmodel.CoinsViewModel;

public class CoinsFragment extends Fragment {

    private CoinsViewModel coinsViewModel;

    public CoinsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_coins, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize the ViewModel
        coinsViewModel = new ViewModelProvider(requireActivity()).get(CoinsViewModel.class);

        // Observe the user's coin balance
        coinsViewModel.getTotalCoins().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer userCoins) {
                if (userCoins != null) {
                    // Update the UI with the current coin balance
                    TextView coinsText = view.findViewById(R.id.coinsText);
                    coinsText.setText("Coins: " + userCoins);
                }
            }
        });

        // Handle the redeem button click
        Button redeemButton = view.findViewById(R.id.redeemButton);
        redeemButton.setOnClickListener(v -> {
            // Here you should call redeemReward with the cost and reward name
            String result = coinsViewModel.redeemReward(20, "Sample Reward"); // Example reward name
            Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
        });
    }
}
