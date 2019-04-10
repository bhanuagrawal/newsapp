package com.ultimatix.cashrich.data;

import android.app.Application;

import com.ultimatix.cashrich.Myapp;
import com.ultimatix.cashrich.data.entity.Article;
import com.ultimatix.cashrich.network.Clients;
import com.ultimatix.cashrich.network.NewsService;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import retrofit2.Callback;


public class NewsRepo {

    Application application;
    NewsService newsService;

    @Inject
    AppDatabase appDatabase;

    public NewsRepo(Application application) {
        this.application = application;
        newsService = new Clients(application).getNewsClient().create(NewsService.class);
        ((Myapp)application).getMainAppComponent().inject(this);
    }

    public void fetchHeadlines(Callback callback) {
        newsService.getTopHeadlines().enqueue(callback);;
    }

    public LiveData<List<Article>> getHeadlinesFromCache() {
        return appDatabase.itemsDao().getAllArticles();
    }

    public void deleteCache() {
        appDatabase.itemsDao().deleteAll();
    }

    public void addToCache(List<Article> articles) {
        appDatabase.itemsDao().add(articles);
    }
}
