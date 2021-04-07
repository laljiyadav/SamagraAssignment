package com.samagraassignment.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TimeStampDao {
 
    @Query("SELECT * FROM timestamp")
    List<TimeStamp> getAll();
    @Query("SELECT * FROM TimeStamp WHERE start_time LIKE :startTime AND " +
            "api_name LIKE :apiName LIMIT 1")
    TimeStamp getTimeStamp(Long startTime,String apiName);
 
    @Insert
    void insert(TimeStamp task);
 
    @Delete
    void delete(TimeStamp task);
 
    @Update
    void update(TimeStamp task);
    
}