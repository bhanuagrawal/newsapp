package com.ultimatix.cashrich.data;



import android.content.Context;

import com.ultimatix.cashrich.data.dao.ItemsDao;
import com.ultimatix.cashrich.data.entity.Article;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Article.class}
        , version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ItemsDao itemsDao();
}
