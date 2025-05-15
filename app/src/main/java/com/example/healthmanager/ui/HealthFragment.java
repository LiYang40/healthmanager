package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.TextView;

import com.example.healthmanager.R;
import com.example.healthmanager.adapter.HealthRecordAdapter;
import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.viewmodel.HealthViewModel;
import com.example.healthmanager.viewmodel.ViewModelFactory;

import java.util.List;

public class HealthFragment extends Fragment {

    private HealthViewModel healthViewModel;
    private HealthRecordAdapter healthRecordAdapter;
    private RecyclerView recyclerView;
    private TextView coinBalanceTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_health, container, false);

        recyclerView = root.findViewById(R.id.recyclerView_health_records);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        healthRecordAdapter = new HealthRecordAdapter(null);
        recyclerView.setAdapter(healthRecordAdapter);

        coinBalanceTextView = root.findViewById(R.id.textView_coin_balance);

        // Pass Application instead of Context
        ViewModelFactory factory = new ViewModelFactory(requireActivity().getApplication());
        healthViewModel = new ViewModelProvider(this, factory).get(HealthViewModel.class);

        healthViewModel.getAllRecords().observe(getViewLifecycleOwner(), new Observer<List<HealthRecord>>() {
            @Override
            public void onChanged(List<HealthRecord> healthRecords) {
                healthRecordAdapter.setHealthRecords(healthRecords);
            }
        });

        healthViewModel.getTotalCoins().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(Integer totalCoins) {
                if (totalCoins != null) {
                    coinBalanceTextView.setText("Coins: " + totalCoins);
                }
            }
        });

        return root;
    }
}
