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
    ShoplistItemEntity entity;

    public static String TAG = "NewRecordDialog";

    public NewRecordDialog(ShoplistItemEntity entityToEdit) {
        entity = entityToEdit;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ShoplistViewModel.class);
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        binding = NewRecordDialogBinding.inflate(getLayoutInflater());
        String positiveButtonText = "Добавить";
        String dialogTitle = "Новая запись";
        if(entity != null) {
            binding.newRecordText.setText(entity.getName());
            dialogTitle = "Редактирование";
            positiveButtonText = "Изменить";
        }
        return new AlertDialog.Builder(requireContext())
                .setTitle(dialogTitle)
                .setView(binding.getRoot())
                .setPositiveButton(positiveButtonText, (dialog, which) -> {
                    if(entity != null) {
                        entity.setName(binding.newRecordText.getText().toString());
                        viewModel.updateItems(entity);
                    }
                    else {
                        ShoplistItemEntity entity = new ShoplistItemEntity(0, binding.newRecordText.getText().toString(), false);
                        viewModel.insertItems(entity);
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Отмена", (dialog, which) -> {
                    dialog.dismiss();
                })
                .show();
    }
}
