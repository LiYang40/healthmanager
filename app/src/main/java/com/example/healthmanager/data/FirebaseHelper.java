package com.example.healthmanager.data;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// This class provides helper methods for Firebase integration
public class FirebaseHelper {

    // Get an instance of the Firebase Realtime Database
    public static FirebaseDatabase getDatabase() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true); // Enable offline persistence
        return database;
    }

    // Get a reference to a specific node in the database
    public static DatabaseReference getNodeReference(String nodeName) {
        return getDatabase().getReference(nodeName);
    }
}