package com.ultimatix.cashrich.data.dao;

import com.ultimatix.cashrich.data.entity.Article;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface ItemsDao {

    @Query("SELECT * from Article")
    LiveData<List<Article>> getAllArticles();

    @Query("Delete from Article")
    void deleteAll();

    @Insert
    void add(List<Article> items);
}
