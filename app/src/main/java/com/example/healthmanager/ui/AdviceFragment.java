package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.adapter.AdviceAdapter;
import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.utils.HealthAnalyzer;
import com.example.healthmanager.viewmodel.HealthViewModel;
import com.example.healthmanager.viewmodel.ViewModelFactory;

import java.util.List;

public class AdviceFragment extends Fragment {

    private RecyclerView recyclerView;
    private AdviceAdapter adviceAdapter;
    private HealthViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_advice, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_advice);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adviceAdapter = new AdviceAdapter();
        recyclerView.setAdapter(adviceAdapter);

        // ✅ Use custom ViewModelFactory to inject HealthRepository into HealthViewModel
        ViewModelFactory factory = new ViewModelFactory(requireActivity().getApplication());
        viewModel = new ViewModelProvider(this, factory).get(HealthViewModel.class);

        // ✅ Observe the latest health record and show advice
        viewModel.getLatestRecord().observe(getViewLifecycleOwner(), new Observer<HealthRecord>() {
            @Override
            public void onChanged(HealthRecord record) {
                if (record != null) {
                    List<String> adviceList = HealthAnalyzer.generateAdvice(record);
                    adviceAdapter.setAdviceList(adviceList);
                }
            }
        });
    }
}
