package com.example.healthmanager.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthmanager.R;
import com.example.healthmanager.model.Reminder;

import java.util.List;

public class ReminderAdapter extends ListAdapter<Reminder, ReminderAdapter.ReminderViewHolder> {

    public ReminderAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Reminder> DIFF_CALLBACK = new DiffUtil.ItemCallback<Reminder>() {
        @Override
        public boolean areItemsTheSame(@NonNull Reminder oldItem, @NonNull Reminder newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Reminder oldItem, @NonNull Reminder newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_reminder, parent, false);
        return new ReminderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ReminderViewHolder holder, int position) {
        Reminder reminder = getItem(position);
        holder.title.setText(reminder.getTitle());
        holder.time.setText("Time: " + reminder.getTime());
        holder.repeat.setText("Repeat: " + reminder.getRepeat());
    }

    static class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView title, time, repeat;

        ReminderViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.reminderTitleText);
            time = itemView.findViewById(R.id.reminderTimeText);
            repeat = itemView.findViewById(R.id.reminderRepeatText);
        }
    }

}
