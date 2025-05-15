package com.example.healthmanager.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthmanager.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;

public class ReportsFragment extends Fragment {

    private LineChart lineChart;
    private TextView summaryText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reports, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view,
                              @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lineChart = view.findViewById(R.id.lineChart);
        summaryText = view.findViewById(R.id.summaryText);

        setupChart();
        loadSampleSummary();
    }

    private void setupChart() {
        List<Entry> entries = new ArrayList<>();
        // Simulated weekly weight data
        entries.add(new Entry(1, 70));
        entries.add(new Entry(2, 69.5f));
        entries.add(new Entry(3, 69));
        entries.add(new Entry(4, 68.7f));
        entries.add(new Entry(5, 68.5f));

        LineDataSet dataSet = new LineDataSet(entries, "Weight (kg)");
        dataSet.setColor(getResources().getColor(R.color.teal_700));
        dataSet.setCircleColor(getResources().getColor(R.color.teal_200));
        dataSet.setLineWidth(2f);
        dataSet.setCircleRadius(5f);
        dataSet.setValueTextSize(10f);

        LineData lineData = new LineData(dataSet);
        lineChart.setData(lineData);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        lineChart.getDescription().setEnabled(false);
        lineChart.invalidate(); // Refresh chart
    }

    private void loadSampleSummary() {
        // This could be dynamic in future
        String summary = "You’ve made great progress this week!\n" +
                "- Weight dropped from 70kg to 68.5kg\n" +
                "- Water intake and sleep improved\n" +
                "- Keep it up!";
        summaryText.setText(summary);
    }
}
