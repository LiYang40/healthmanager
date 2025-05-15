package com.example.healthmanager.data.remote;

import com.example.healthmanager.model.HealthRecord;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseHelper {

    private static final String RECORDS_NODE = "health_records";
    private final DatabaseReference databaseReference;
    private final FirebaseAuth mAuth;

    // Constructor to initialize Firebase reference and FirebaseAuth
    public FirebaseHelper() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference(RECORDS_NODE);
        mAuth = FirebaseAuth.getInstance(); // Initialize Firebase Authentication
    }

    // Save a health record to Firebase
    public void saveRecord(HealthRecord record) {
        String recordId = databaseReference.push().getKey();
        if (recordId != null) {
            // Store the health record under a unique key
            databaseReference.child(recordId).setValue(record);
        }
    }

    // Retrieve all health records from Firebase
    public void getAllRecords(FirebaseCallback callback) {
        databaseReference.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot dataSnapshot = task.getResult();
                if (dataSnapshot.exists()) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                        HealthRecord record = snapshot.getValue(HealthRecord.class);
                        if (record != null) {
                            callback.onDataLoaded(record); // Send the record to the callback
                        }
                    }
                } else {
                    callback.onDataError("No data found");
                }
            } else {
                callback.onDataError("Failed to retrieve data");
            }
        });
    }

    // Sign up a new user
    public void signUp(String email, String password, FirebaseCallback callback) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onAuthSuccess();
                    } else {
                        callback.onAuthError(task.getException().getMessage());
                    }
                });
    }

    // Sign in an existing user
    public void signIn(String email, String password, FirebaseCallback callback) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        callback.onAuthSuccess();
                    } else {
                        callback.onAuthError(task.getException().getMessage());
                    }
                });
    }

    // Check if the user is already signed in
    public FirebaseUser getCurrentUser() {
        return mAuth.getCurrentUser();
    }

    // Sign out the current user
    public void signOut() {
        mAuth.signOut();
    }

    // Callback interface for authentication results and Firebase data
    public interface FirebaseCallback {
        void onAuthSuccess();
        void onAuthError(String errorMessage);
        void onDataLoaded(HealthRecord record);
        void onDataError(String errorMessage);
    }
}
