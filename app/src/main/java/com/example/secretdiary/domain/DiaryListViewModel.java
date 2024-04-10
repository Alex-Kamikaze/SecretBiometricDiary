package com.example.secretdiary.domain;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.secretdiary.DiaryApplication;
import com.example.secretdiary.data.DiaryDao;
import com.example.secretdiary.data.DiaryRecordEntity;

import java.util.ArrayList;
import java.util.List;

public class DiaryListViewModel extends ViewModel {
    private final DiaryDao dao = DiaryApplication
            .getInstance()
            .getDatabase()
            .getDao();

    public LiveData<List<DiaryRecordEntity>> diaryRecords = dao.getAllRecords();

    public void AddNewRecord(String text) {
        DiaryRecordEntity newRecord = new DiaryRecordEntity(0, text, System.currentTimeMillis());
        dao.insertRecord(newRecord);
    }
}
