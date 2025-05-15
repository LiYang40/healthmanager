package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.adapter.RedeemedRewardAdapter;
import com.example.healthmanager.viewmodel.CoinsViewModel;

public class RedeemedHistoryFragment extends Fragment {

    private RedeemedRewardAdapter adapter;
    private CoinsViewModel viewModel;

    public RedeemedHistoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_redeemed_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.redeemedRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new RedeemedRewardAdapter();
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(requireActivity()).get(CoinsViewModel.class);
        viewModel.getRedeemedRewards().observe(getViewLifecycleOwner(), adapter::submitList);
    }
}
