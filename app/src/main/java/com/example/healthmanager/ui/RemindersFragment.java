package com.example.healthmanager.ui;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.data.ReminderDatabase;
import com.example.healthmanager.data.ReminderEntity;
import com.example.healthmanager.utils.ReminderReceiver;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RemindersFragment extends Fragment {

    private TimePicker timePicker;
    private RadioGroup repeatOptions;
    private View weeklyOptions;
    private Spinner categorySpinner;
    private RecyclerView remindersRecyclerView;
    private ReminderAdapter reminderAdapter;

    private final List<ReminderEntity> dailyReminders = new ArrayList<>();
    private final List<ReminderEntity> weeklyReminders = new ArrayList<>();
    private ReminderDatabase database;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reminders, container, false);

        // Initialize UI elements
        timePicker = view.findViewById(R.id.time_picker);
        timePicker.setIs24HourView(true);

        repeatOptions = view.findViewById(R.id.repeat_options);
        weeklyOptions = view.findViewById(R.id.weekly_options);
        categorySpinner = view.findViewById(R.id.category_spinner);
        remindersRecyclerView = view.findViewById(R.id.reminders_recycler_view);

        database = ReminderDatabase.getInstance(requireContext());

        // Setup RecyclerView
        reminderAdapter = new ReminderAdapter(dailyReminders, weeklyReminders, this::deleteReminder);
        remindersRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        remindersRecyclerView.setAdapter(reminderAdapter);

        // Populate category spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.reminder_categories, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        // Load reminders from the database
        loadReminders();

        view.findViewById(R.id.set_reminder_button).setOnClickListener(v -> setReminder());

        return view;
    }

    private void setReminder() {
        int hour = timePicker.getHour();
        int minute = timePicker.getMinute();
        String category = categorySpinner.getSelectedItem().toString();

        boolean isDaily = repeatOptions.getCheckedRadioButtonId() == R.id.radio_daily;

        List<Integer> selectedDays = new ArrayList<>();
        if (!isDaily) {
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_mon)).isChecked()) selectedDays.add(Calendar.MONDAY);
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_tue)).isChecked()) selectedDays.add(Calendar.TUESDAY);
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_wed)).isChecked()) selectedDays.add(Calendar.WEDNESDAY);
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_thu)).isChecked()) selectedDays.add(Calendar.THURSDAY);
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_fri)).isChecked()) selectedDays.add(Calendar.FRIDAY);
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_sat)).isChecked()) selectedDays.add(Calendar.SATURDAY);
            if (((CheckBox) weeklyOptions.findViewById(R.id.checkbox_sun)).isChecked()) selectedDays.add(Calendar.SUNDAY);

            if (selectedDays.isEmpty()) {
                Toast.makeText(getContext(), "Please select at least one day for weekly reminder", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        String days = "";
        if (!isDaily) {
            StringBuilder daysStringBuilder = new StringBuilder();
            for (int day : selectedDays) {
                daysStringBuilder.append(getDayName(day)).append(",");
            }
            days = daysStringBuilder.toString();
        }

        String displayText = isDaily
                ? String.format("Daily | %02d:%02d %s", hour, minute, category)
                : String.format("Weekly | %s %02d:%02d %s", days, hour, minute, category);

        ReminderEntity reminder = new ReminderEntity(displayText, isDaily, days, hour, minute, category);

        // Save reminder to the database
        new Thread(() -> {
            database.reminderDao().insertReminder(reminder);
            loadReminders(); // Reload reminders after insertion
        }).start();

        Toast.makeText(getContext(), "Reminder set!", Toast.LENGTH_SHORT).show();
    }

    private void loadReminders() {
        new Thread(() -> {
            List<ReminderEntity> reminders = database.reminderDao().getAllReminders();
            dailyReminders.clear();
            weeklyReminders.clear();

            for (ReminderEntity reminder : reminders) {
                if (reminder.isDaily()) {
                    dailyReminders.add(reminder);
                } else {
                    weeklyReminders.add(reminder);
                }
            }

            requireActivity().runOnUiThread(() -> reminderAdapter.notifyDataSetChanged());
        }).start();
    }

    private void deleteReminder(ReminderEntity reminder, boolean isDaily) {
        new Thread(() -> {
            database.reminderDao().deleteReminder(reminder);
            loadReminders(); // Reload reminders after deletion
        }).start();

        Toast.makeText(getContext(), "Reminder deleted!", Toast.LENGTH_SHORT).show();
    }

    private String getDayName(int dayOfWeek) {
        switch (dayOfWeek) {
            case Calendar.MONDAY:
                return "Mon";
            case Calendar.TUESDAY:
                return "Tue";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thu";
            case Calendar.FRIDAY:
                return "Fri";
            case Calendar.SATURDAY:
                return "Sat";
            case Calendar.SUNDAY:
                return "Sun";
            default:
                return "";
        }
    }
}