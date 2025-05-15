package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.healthmanager.databinding.FragmentChartBinding;
import com.example.healthmanager.viewmodel.ChartViewModel;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ChartFragment extends Fragment {

    private FragmentChartBinding binding;
    private ChartViewModel chartViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentChartBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chartViewModel = new ViewModelProvider(this).get(ChartViewModel.class);

        chartViewModel.getSleepData().observe(getViewLifecycleOwner(), records -> {
            List<Entry> entries = new ArrayList<>();
            int index = 0;
            for (Float sleepHours : records) {
                entries.add(new Entry(index++, sleepHours)); // Populate the chart with sleep data
            }

            LineDataSet dataSet = new LineDataSet(entries, "Sleep Hours");
            dataSet.setColor(android.graphics.Color.BLUE);
            dataSet.setValueTextColor(android.graphics.Color.BLACK);
            LineData lineData = new LineData(dataSet);

            LineChart chart = binding.lineChart;
            chart.setData(lineData);
            chart.getDescription().setText("Sleep Trends");
            chart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
            chart.invalidate(); // Refresh the chart
        });
    }
}
