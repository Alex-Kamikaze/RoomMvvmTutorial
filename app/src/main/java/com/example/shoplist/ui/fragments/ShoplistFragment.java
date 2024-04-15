package com.example.shoplist.ui.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoplist.data.db.ShoplistItemEntity;
import com.example.shoplist.databinding.FragmentShoplistBinding;
import com.example.shoplist.domain.adapters.ShoplistAdapter;
import com.example.shoplist.domain.viewmodels.ShoplistViewModel;

import java.util.ArrayList;
import java.util.List;

public class ShoplistFragment extends Fragment {
    FragmentShoplistBinding binding;
    ShoplistAdapter adapter;
    public static ShoplistFragment newInstance() {
        return new ShoplistFragment();
    }

    ShoplistViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentShoplistBinding.inflate(inflater);
        viewModel = new ViewModelProvider(this).get(ShoplistViewModel.class);
        ShoplistAdapter.OnShopListItemClick onItemClick = (item, position, checked) -> viewModel.updateItems(item);
        ArrayList<ShoplistItemEntity> items = (ArrayList<ShoplistItemEntity>) viewModel.shopListItems.getValue();
        if(items == null) {
            items = new ArrayList<>();
        }
        adapter = new ShoplistAdapter(items, onItemClick);
        viewModel.shopListItems.observe(getViewLifecycleOwner(), shoplistItemEntities -> adapter.updateData((ArrayList<ShoplistItemEntity>) shoplistItemEntities));
        binding.shoplistView.setAdapter(adapter);
        binding.addItemButton.setOnClickListener(v -> {
            new NewRecordDialog().show(getChildFragmentManager(), NewRecordDialog.TAG);
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}