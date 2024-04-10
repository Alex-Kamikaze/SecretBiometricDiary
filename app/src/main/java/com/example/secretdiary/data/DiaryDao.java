package com.example.secretdiary.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface DiaryDao {
    @Insert
    void insertRecord(DiaryRecordEntity entity);

    @Delete
    void deleteRecord(DiaryRecordEntity entity);

    @Update
    void updateRecord(DiaryRecordEntity entity);

    @Query("SELECT * FROM DiaryRecordEntity")
    LiveData<List<DiaryRecordEntity>> getAllRecords();
}
