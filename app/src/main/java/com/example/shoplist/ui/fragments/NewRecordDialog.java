package com.example.shoplist.ui.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.shoplist.data.db.ShoplistItemEntity;
import com.example.shoplist.databinding.NewRecordDialogBinding;
import com.example.shoplist.domain.viewmodels.ShoplistViewModel;

import java.util.Objects;

public class NewRecordDialog extends DialogFragment {
    NewRecordDialogBinding binding;
    ShoplistViewModel viewModel;

    public static String TAG = "NewRecordDialog";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ShoplistViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = NewRecordDialogBinding.inflate(getLayoutInflater());
        return new AlertDialog.Builder(requireContext())
                .setTitle("Новая запись")
                .setView(binding.getRoot())
                .setPositiveButton("Добавить", (dialog, which) -> {
                    ShoplistItemEntity entity = new ShoplistItemEntity(0, binding.newRecordText.getText().toString(), false);
                    viewModel.insertItems(entity);
                    dialog.dismiss();
                })
                .setNegativeButton("Отмена", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
