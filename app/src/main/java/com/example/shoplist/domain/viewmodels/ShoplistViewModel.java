package com.example.shoplist.domain.viewmodels;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.shoplist.ShoplistApplication;
import com.example.shoplist.data.db.ShoplistDao;
import com.example.shoplist.data.db.ShoplistItemEntity;

import java.util.List;

public class ShoplistViewModel extends ViewModel {
    private ShoplistDao dao = ShoplistApplication
            .getINSTANCE()
            .getDb()
            .getDao();

    public LiveData<List<ShoplistItemEntity>> shopListItems = dao.getAllShopListItems();

    public void updateItems(ShoplistItemEntity... items) {
        dao.updateItems(items);
    }

    public void deleteItems(ShoplistItemEntity... items) {
        dao.deleteItems(items);
    }

    public void insertItems(ShoplistItemEntity... items) {
        dao.insertNewItems(items);
    }

    public ShoplistItemEntity getItemById(int id) {
        return dao.getShopListItemById(id);
    }
}
