package com.example.secretdiary.data;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class DiaryRecordEntity {
    @PrimaryKey(autoGenerate = true)
    public int recordId;
    public String recordText;
    public Long recordDate;

    public DiaryRecordEntity(int recordId, String recordText, Long recordDate) {
        this.recordId = recordId;
        this.recordText = recordText;
        this.recordDate = recordDate;
    }

    public DiaryRecordEntity() {}
}
