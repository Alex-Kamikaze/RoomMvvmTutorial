package com.example.shoplist.data.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoplist")
public class ShoplistItemEntity {
    @PrimaryKey(autoGenerate = true)
    private int ID;

    @ColumnInfo(name = "name")
    private String Name;

    @ColumnInfo(name = "completed")
    private boolean Completed = false;

    public ShoplistItemEntity(int ID, String name, boolean completed) {
        this.ID = ID;
        Name = name;
        Completed = completed;
    }

    public ShoplistItemEntity() {}
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public boolean isCompleted() {
        return Completed;
    }
    public void setCompleted(boolean completed) {
        Completed = completed;
    }
}
