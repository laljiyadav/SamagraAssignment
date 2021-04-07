package com.samagraassignment.db;



import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.samagraassignment.repository.TimeStamp;
import com.samagraassignment.repository.TimeStampDao;

@Database(entities = {TimeStamp.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TimeStampDao timeStampDao();
}