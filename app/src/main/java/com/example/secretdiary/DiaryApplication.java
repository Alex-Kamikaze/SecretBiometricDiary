package com.example.secretdiary;

import android.app.Application;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.secretdiary.data.AppDatabase;

public class DiaryApplication extends Application {
    private static DiaryApplication INSTANCE;
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        database = Room.databaseBuilder(this, AppDatabase.class, "app_database.sqlite").allowMainThreadQueries().build();
    }

    public static DiaryApplication getInstance() { return INSTANCE; }

    public AppDatabase getDatabase() { return database; }
}
