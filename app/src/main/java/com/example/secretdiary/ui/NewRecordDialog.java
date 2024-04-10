package com.example.secretdiary.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.secretdiary.databinding.NewRecordDialogBinding;
import com.example.secretdiary.domain.DiaryListViewModel;

public class NewRecordDialog extends DialogFragment {
    NewRecordDialogBinding binding;
    DiaryListViewModel viewModel;
    public static String TAG = "NewRecordDialog";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(DiaryListViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = NewRecordDialogBinding.inflate(getLayoutInflater());
        return new AlertDialog.Builder(getContext())
                .setTitle("Новая запись")
                .setView(binding.getRoot())
                .setPositiveButton("Добавить", (dialog, which) -> {
                    String text = binding.newRecordText.getText().toString();
                    viewModel.AddNewRecord(text);
                    dialog.dismiss();
                })
                .setNegativeButton("Отмена", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
