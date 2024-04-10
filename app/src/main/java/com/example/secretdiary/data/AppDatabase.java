package com.example.secretdiary.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {DiaryRecordEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract DiaryDao getDao();
}
