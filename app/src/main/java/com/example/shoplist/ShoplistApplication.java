package com.example.shoplist;

import android.app.Application;

import androidx.room.Room;

import com.example.shoplist.data.db.AppDatabase;

public class ShoplistApplication extends Application {
    private static ShoplistApplication INSTANCE;
    private AppDatabase db;
    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
        db = Room.databaseBuilder(this, AppDatabase.class, "app_database.sqlite")
                .allowMainThreadQueries()
                .build();
    }
    public static ShoplistApplication getINSTANCE() {
        return INSTANCE;
    }
    public AppDatabase getDb() {
        return db;
    }
}

