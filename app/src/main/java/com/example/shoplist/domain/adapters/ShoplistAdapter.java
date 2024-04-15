package com.example.shoplist.domain.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoplist.data.db.ShoplistItemEntity;
import com.example.shoplist.databinding.ShoplistItemBinding;

import java.util.ArrayList;

public class ShoplistAdapter extends RecyclerView.Adapter<ShoplistAdapter.ViewHolder> {
    private ShoplistItemBinding binding;
    private ArrayList<ShoplistItemEntity> shoplistItems;
    private final OnShopListItemClick onShopListItemClick;

    public interface OnShopListItemClick {
        void onItemClicked(ShoplistItemEntity item, int position, boolean checked);
    }

    public ShoplistAdapter(ArrayList<ShoplistItemEntity> shopListItems, OnShopListItemClick onItemClick) {
        this.shoplistItems = shopListItems;
        this.onShopListItemClick = onItemClick;
    }

    @NonNull
    @Override
    public ShoplistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ShoplistItemBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new ViewHolder(binding.getRoot());
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistAdapter.ViewHolder holder, int position) {
        holder.setItemName(shoplistItems.get(position).getName());
        holder.setItemCompleted(shoplistItems.get(position).isCompleted());
        binding.productName.setText(holder.getItemName());
        binding.productChecked.setChecked(holder.isItemCompleted());
        binding.productChecked.setOnCheckedChangeListener((buttonView, isChecked) -> {
            onShopListItemClick.onItemClicked(shoplistItems.get(position), position, isChecked);
        });
    }

    @Override
    public int getItemCount() {
        return shoplistItems.size();
    }

    public void updateData(ArrayList<ShoplistItemEntity> newItems) {
        this.shoplistItems = newItems;
        this.notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private String itemName;
        private boolean itemCompleted;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public String getItemName() {
            return itemName;
        }

        public void setItemName(String itemName) {
            this.itemName = itemName;
        }

        public boolean isItemCompleted() {
            return itemCompleted;
        }

        public void setItemCompleted(boolean itemCompleted) {
            this.itemCompleted = itemCompleted;
        }
    }
}
