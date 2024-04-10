package com.example.secretdiary.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.secretdiary.R;
import com.example.secretdiary.data.DiaryRecordEntity;
import com.example.secretdiary.databinding.FragmentDiaryBinding;
import com.example.secretdiary.domain.DiaryListAdapter;
import com.example.secretdiary.domain.DiaryListViewModel;

import java.util.ArrayList;
import java.util.Objects;

public class DiaryFragment extends Fragment {
    FragmentDiaryBinding binding;
    DiaryListViewModel viewModel;
    public static DiaryFragment newInstance() {
        return new DiaryFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DiaryListViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDiaryBinding.inflate(inflater);
        DiaryListAdapter adapter = new DiaryListAdapter(requireContext(), R.layout.diary_list_item, new ArrayList<>());
        viewModel.diaryRecords.observe((LifecycleOwner) requireContext(), newRecords -> {
            adapter.updateData((ArrayList<DiaryRecordEntity>) newRecords);
            Log.d("TEST", newRecords.toString());
        });
        binding.diaryRecords.setAdapter(adapter);
        binding.addRecord.setOnClickListener(v -> {
            new NewRecordDialog().show(getChildFragmentManager(), NewRecordDialog.TAG);
        });
        return binding.getRoot();
    }


}