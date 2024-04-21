package com.example.shoplist.data.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ShoplistItemEntity.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ShoplistDao getDao();
}

