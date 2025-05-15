package com.example.healthmanager.ui;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthmanager.R;
import com.example.healthmanager.data.HealthManagerDatabase;
import com.example.healthmanager.model.SleepRecord;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.concurrent.Executors;

public class AddSleepFragment extends Fragment {

    private EditText targetSleepInput;
    private TextView selectedSleepStartTime;
    private TextView selectedWakeUpTime;
    private TextView selectedDate;
    private TextView totalSleepHours;
    private Button sleepStartTimeButton;
    private Button wakeUpTimeButton;
    private Button datePickerButton;
    private Button saveButton;

    private int sleepStartHour = -1;
    private int sleepStartMinute = -1;
    private int wakeUpHour = -1;
    private int wakeUpMinute = -1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_sleep, container, false);

        // Initialize views
        targetSleepInput = view.findViewById(R.id.target_sleep_input);
        selectedSleepStartTime = view.findViewById(R.id.selected_sleep_start_time);
        selectedWakeUpTime = view.findViewById(R.id.selected_wake_up_time);
        selectedDate = view.findViewById(R.id.selected_date);
        totalSleepHours = view.findViewById(R.id.total_sleep_hours);
        sleepStartTimeButton = view.findViewById(R.id.sleep_start_time_button);
        wakeUpTimeButton = view.findViewById(R.id.wake_up_time_button);
        datePickerButton = view.findViewById(R.id.date_picker_button);
        saveButton = view.findViewById(R.id.save_button);

        // Set up time pickers
        sleepStartTimeButton.setOnClickListener(v -> openTimePicker(true));
        wakeUpTimeButton.setOnClickListener(v -> openTimePicker(false));

        // Set up date picker
        datePickerButton.setOnClickListener(v -> openDatePicker());

        // Save button logic
        saveButton.setOnClickListener(v -> saveRecord());

        return view;
    }

    private void openTimePicker(boolean isSleepStartTime) {
        Calendar calendar = Calendar.getInstance();
        TimePickerDialog timePickerDialog = new TimePickerDialog(
                getContext(),
                (view, hourOfDay, minute) -> {
                    if (isSleepStartTime) {
                        sleepStartHour = hourOfDay;
                        sleepStartMinute = minute;
                        selectedSleepStartTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
                    } else {
                        wakeUpHour = hourOfDay;
                        wakeUpMinute = minute;
                        selectedWakeUpTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
                    }
                    calculateTotalSleepHours();
                },
                calendar.get(Calendar.HOUR_OF_DAY),
                calendar.get(Calendar.MINUTE),
                true
        );
        timePickerDialog.show();
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                (view, year, month, dayOfMonth) -> {
                    calendar.set(year, month, dayOfMonth);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
                    selectedDate.setText(sdf.format(calendar.getTime()));
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void calculateTotalSleepHours() {
        if (sleepStartHour >= 0 && wakeUpHour >= 0) {
            int startMinutes = sleepStartHour * 60 + sleepStartMinute;
            int endMinutes = wakeUpHour * 60 + wakeUpMinute;

            int totalMinutes = endMinutes - startMinutes;
            if (totalMinutes < 0) {
                totalMinutes += 24 * 60; // Account for crossing midnight
            }

            float totalHours = totalMinutes / 60f;
            totalSleepHours.setText(String.format(Locale.getDefault(), "Total Sleep Hours: %.1f", totalHours));
        }
    }

    private void saveRecord() {
        String targetSleep = targetSleepInput.getText().toString();
        String date = selectedDate.getText().toString();
        String sleepHoursText = totalSleepHours.getText().toString();

        if (targetSleep.isEmpty() || sleepStartHour < 0 || wakeUpHour < 0 || date.isEmpty() || sleepHoursText.equals("Total Sleep Hours: --")) {
            Toast.makeText(getContext(), "Please complete all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        float targetSleepHours = Float.parseFloat(targetSleep);
        String[] splitText = sleepHoursText.split(": ");
        float actualSleepHours = Float.parseFloat(splitText[1]);

        SleepRecord record = new SleepRecord(targetSleepHours, actualSleepHours, date);

        Executors.newSingleThreadExecutor().execute(() -> {
            HealthManagerDatabase.getInstance(getContext()).sleepDao().insert(record);
            getActivity().runOnUiThread(() -> {
                Toast.makeText(getContext(), "Sleep record saved!", Toast.LENGTH_SHORT).show();
            });
        });
    }
}