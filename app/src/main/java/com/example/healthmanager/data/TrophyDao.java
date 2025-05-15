package com.example.healthmanager.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.healthmanager.model.Trophy;

import java.util.List;

@Dao
public interface TrophyDao {
    @Insert
    void insert(Trophy trophy);

    @Query("SELECT * FROM trophies")
    List<Trophy> getAllTrophies();
}