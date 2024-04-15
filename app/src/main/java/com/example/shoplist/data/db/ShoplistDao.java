package com.example.shoplist.data.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.Upsert;

import java.util.List;

@Dao
public interface ShoplistDao {
    @Insert
    void insertNewItems(ShoplistItemEntity... entities);

    @Update
    void updateItems(ShoplistItemEntity... entities);

    @Delete
    void deleteItems(ShoplistItemEntity... entities);

    @Query("SELECT * FROM shoplist")
    LiveData<List<ShoplistItemEntity>> getAllShopListItems();

    @Query("SELECT * FROM shoplist WHERE ID = :id")
    ShoplistItemEntity getShopListItemById(int id);
}
