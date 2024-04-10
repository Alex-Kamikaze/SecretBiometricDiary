package com.example.secretdiary.domain;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.secretdiary.data.DiaryRecordEntity;
import com.example.secretdiary.databinding.DiaryListItemBinding;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DiaryListAdapter extends ArrayAdapter<DiaryRecordEntity> {
    private Context context;
    private ArrayList<DiaryRecordEntity> records;
    private DiaryListItemBinding binding;

    public DiaryListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<DiaryRecordEntity> objects) {
        super(context, resource, objects);
        this.context = context;
        this.records = objects;
    }

    @SuppressLint("ViewHolder")
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        binding = DiaryListItemBinding.inflate(LayoutInflater.from(context));
        binding.recordText.setText(records.get(position).recordText);
        binding.recordDate.setText(Date.from(Instant.ofEpochSecond(records.get(position).recordDate)).toString());
        return binding.getRoot();
    }

    @Override
    public int getCount() {
        return records.size();
    }

    public void updateData(ArrayList<DiaryRecordEntity> entities) {
        this.records = entities;
        notifyDataSetChanged();
    }
}
