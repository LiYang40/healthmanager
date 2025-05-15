package com.example.healthmanager.ui;

import android.app.PendingIntent;

public class Reminder {
    private final String displayText;
    private final int requestCode;
    private final PendingIntent pendingIntent;

    public Reminder(String displayText, int requestCode, PendingIntent pendingIntent) {
        this.displayText = displayText;
        this.requestCode = requestCode;
        this.pendingIntent = pendingIntent;
    }

    public String getDisplayText() {
        return displayText;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public PendingIntent getPendingIntent() {
        return pendingIntent;
    }
}