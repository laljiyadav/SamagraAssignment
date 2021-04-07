package com.samagraassignment.repository;


 
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
 
@Entity
public class TimeStamp implements Serializable {
 
    @PrimaryKey(autoGenerate = true)
    private int id;
 
    @ColumnInfo(name = "start_time")
    private Long startTime;
 
    @ColumnInfo(name = "end_time")
    private Long endTime;

    @ColumnInfo(name = "api_name")
    private String apiName;


    /*
    * Getters and Setters
    * */
    public int getId() {
        return id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }
}