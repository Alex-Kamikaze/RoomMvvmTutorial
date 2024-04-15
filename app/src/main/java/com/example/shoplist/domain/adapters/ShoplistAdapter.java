package com.example.shoplist.domain.adapters;

import android.app.appsearch.PackageIdentifier;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoplist.R;
import com.example.shoplist.data.db.ShoplistItemEntity;
import com.example.shoplist.databinding.ShoplistItemBinding;

import java.util.ArrayList;

public class ShoplistAdapter extends RecyclerView.Adapter<ShoplistAdapter.ViewHolder> {
    private ArrayList<ShoplistItemEntity> shoplistItems;
    private final OnShopListItemClick onShopListItemClick;
    private final Context context;
    private final OnMenuItemSelected onMenuItemSelected;

    public interface OnShopListItemClick {
        void onItemClicked(int position, boolean checked);
    }

    public interface OnMenuItemSelected {
        void onShopListMenuItemSelected(int position, MenuItem item);
    }



    public ShoplistAdapter(Context context, ArrayList<ShoplistItemEntity> shopListItems, OnShopListItemClick onItemClick, OnMenuItemSelected onMenuItemSelected) {
        this.context = context;
        this.shoplistItems = shopListItems;
        this.onShopListItemClick = onItemClick;
        this.onMenuItemSelected = onMenuItemSelected;
    }

    @NonNull
    @Override
    public ShoplistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ShoplistItemBinding binding = ShoplistItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoplistAdapter.ViewHolder holder, int position) {
        holder.binding.productName.setText(shoplistItems.get(position).getName());
        holder.binding.productChecked.setChecked(shoplistItems.get(position).isCompleted());
        holder.binding.getRoot().setOnLongClickListener(view -> {
            PopupMenu menu = new PopupMenu(context, view);
            menu.inflate(R.menu.list_menu);
            menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem menuItem) {
                    onMenuItemSelected.onShopListMenuItemSelected(position,  menuItem);
                    return true;
                }
            });
            menu.show();
            return true;
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

    public ShoplistItemEntity getElementByPosition(int position) {
        return shoplistItems.get(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ShoplistItemBinding binding;
        public ViewHolder(@NonNull ShoplistItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.productChecked.setOnClickListener(v -> {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    boolean isChecked = binding.productChecked.isChecked();
                    onShopListItemClick.onItemClicked(position, isChecked);
                }
            });
        }
    }
}
