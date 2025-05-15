package com.example.healthmanager.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.data.ReminderEntity;

import java.util.List;

/**
 * ReminderAdapter is a RecyclerView adapter that handles displaying reminders
 * in two sections: Daily Reminders and Weekly Reminders.
 * It supports headers for each section and handles long-press to delete reminders.
 */
public class ReminderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Constants for view types
    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_ITEM = 1;

    private final List<ReminderEntity> dailyReminders; // List of daily reminders
    private final List<ReminderEntity> weeklyReminders; // List of weekly reminders
    private final ReminderDeleteCallback callback; // Callback for deleting reminders

    /**
     * Callback interface for deleting reminders.
     */
    public interface ReminderDeleteCallback {
        void onDelete(ReminderEntity reminder, boolean isDaily);
    }

    /**
     * Constructor for ReminderAdapter.
     *
     * @param dailyReminders  List of daily reminders
     * @param weeklyReminders List of weekly reminders
     * @param callback        Callback for handling deletion of reminders
     */
    public ReminderAdapter(List<ReminderEntity> dailyReminders, List<ReminderEntity> weeklyReminders, ReminderDeleteCallback callback) {
        this.dailyReminders = dailyReminders;
        this.weeklyReminders = weeklyReminders;
        this.callback = callback;
    }

    @Override
    public int getItemViewType(int position) {
        // Determine if the current position is a header or an item
        if (position == 0 || position == dailyReminders.size() + 1) {
            return VIEW_TYPE_HEADER;
        }
        return VIEW_TYPE_ITEM;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_HEADER) {
            // Inflate the layout for header views
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new HeaderViewHolder(view);
        } else {
            // Inflate the layout for item views
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ReminderViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof HeaderViewHolder) {
            // Handle header view binding
            HeaderViewHolder headerHolder = (HeaderViewHolder) holder;
            if (position == 0) {
                headerHolder.textView.setText("Daily Reminders");
            } else {
                headerHolder.textView.setText("Weekly Reminders");
            }
        } else {
            // Handle item view binding
            ReminderViewHolder reminderHolder = (ReminderViewHolder) holder;
            ReminderEntity reminder;
            boolean isDaily;

            // Determine if the reminder belongs to daily or weekly section
            if (position <= dailyReminders.size()) {
                reminder = dailyReminders.get(position - 1); // Adjust for header at position 0
                isDaily = true;
            } else {
                reminder = weeklyReminders.get(position - dailyReminders.size() - 2); // Adjust for headers
                isDaily = false;
            }

            // Bind the reminder data to the view
            reminderHolder.textView.setText(reminder.getDisplayText());
            reminderHolder.textView.setOnLongClickListener(v -> {
                // Trigger the delete callback on long-press
                callback.onDelete(reminder, isDaily);
                return true;
            });
        }
    }

    @Override
    public int getItemCount() {
        // Total items = number of daily reminders + number of weekly reminders + 2 headers
        return dailyReminders.size() + weeklyReminders.size() + 2;
    }

    /**
     * ViewHolder for header views.
     */
    static class HeaderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }

    static class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(android.R.id.text1);
        }
    }
}