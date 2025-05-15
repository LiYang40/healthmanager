package com.example.healthmanager.repository;

<<<<<<< HEAD
import android.content.Context;  // Import Context
import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.healthmanager.data.local.AppDatabase;
import com.example.healthmanager.data.local.HealthRecordDao;
import com.example.healthmanager.data.local.UserCoinsDao;
import com.example.healthmanager.model.HealthRecord;
import com.example.healthmanager.model.UserCoins;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HealthRepository {

    private final HealthRecordDao healthRecordDao;
    private final LiveData<List<HealthRecord>> allRecords;
    private final LiveData<Integer> totalCoins;
    private final ExecutorService executorService;
    private final UserCoinsDao userCoinsDao;

    // Constructor that accepts Application for AppDatabase initialization
    public HealthRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        healthRecordDao = db.healthRecordDao();
        allRecords = healthRecordDao.getAllRecords();
        totalCoins = healthRecordDao.getTotalCoins();
        executorService = Executors.newSingleThreadExecutor();
        userCoinsDao = db.userCoinsDao();
    }

    // Overloaded constructor that accepts Context, for use in non-Application contexts
    public HealthRepository(Context context) {
        AppDatabase db = AppDatabase.getDatabase(context);
        healthRecordDao = db.healthRecordDao();
        allRecords = healthRecordDao.getAllRecords();
        totalCoins = healthRecordDao.getTotalCoins();
        executorService = Executors.newSingleThreadExecutor();
        userCoinsDao = db.userCoinsDao();
    }

    public LiveData<List<HealthRecord>> getAllRecords() {
        return allRecords;
    }

    public LiveData<Integer> getTotalCoins() {
        return totalCoins;
    }

    // Insert a record and update the coin balance
    public void insertRecordWithCoins(HealthRecord record) {
        record.setCoinsEarned(20); // Set 20 coins for each health record
        executorService.execute(() -> {
            healthRecordDao.insert(record);  // Insert health record

            // Update coin balance for the user
            UserCoins userCoins = userCoinsDao.getUserCoins();
            if (userCoins != null) {
                int currentCoins = userCoins.getCoins();
                userCoins.setCoins(currentCoins + 20); // Add 20 coins
                userCoinsDao.update(userCoins); // Update the user's coin balance
            } else {
                userCoinsDao.insert(new UserCoins(20)); // Start with 20 coins if it's a new user
            }
        });
    }

    // Fetch the latest record
    public LiveData<HealthRecord> getLatestRecord() {
        return healthRecordDao.getLatestRecord(); // Assuming this method exists in your DAO
    }

    // Fetch records by category, e.g., "Sleep"
    public LiveData<List<HealthRecord>> getRecordsByCategory(String category) {
        return healthRecordDao.getRecordsByCategory(category);
    }

    public LiveData<List<String>> getHealthAdvice() {
        List<String> adviceList = new ArrayList<>();

        // Add some example advice
        adviceList.add("Drink more water.");
        adviceList.add("Get enough sleep.");
        adviceList.add("Maintain a balanced diet.");

        // Return advice as LiveData
        return new LiveData<List<String>>() {
            @Override
            public List<String> getValue() {
                return adviceList;
            }
        };
    }
}
=======
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.healthmanager.model.HealthRecord;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HealthRepository {

    private final DatabaseReference databaseReference;
    private final MutableLiveData<List<HealthRecord>> healthRecordsLiveData = new MutableLiveData<>();

    public HealthRepository() {
        databaseReference = FirebaseDatabase.getInstance().getReference("health_records");
    }

    // Add this method to fix the issue
    public void addHealthRecord(HealthRecord healthRecord) {
        String recordId = databaseReference.push().getKey(); // Generate a unique key
        if (recordId != null) {
            databaseReference.child(recordId).setValue(healthRecord);
        }
    }

    public LiveData<List<HealthRecord>> getHealthRecords() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<HealthRecord> records = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    HealthRecord record = snapshot.getValue(HealthRecord.class);
                    records.add(record);
                }
                healthRecordsLiveData.setValue(records);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle errors here
            }
        });
        return healthRecordsLiveData;
    }
}
>>>>>>> 98b3c21 (Update project with latest changes)
