package com.ultimatix.cashrich;

import android.app.Application;
import android.os.Handler;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.ultimatix.cashrich.data.NewsRepo;
import com.ultimatix.cashrich.data.entity.Article;
import com.ultimatix.cashrich.datamodels.Feed;

import java.util.List;
import java.util.Map;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainViewModel extends AndroidViewModel {


    LiveData<List<Article>> articlesLiveData;
    NewsRepo newsRepo;
    public MainViewModel(@NonNull Application application) {
        super(application);
        newsRepo = new NewsRepo(application);
    }

    public LiveData<List<Article>> getArticlesLiveData() {
        return newsRepo.getHeadlinesFromCache();
    }

    public void fetchHeadlines(){
        newsRepo.fetchHeadlines(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                if(response.code() == 200){


                    Completable.fromAction(new Action() {
                        @Override
                        public void run() throws Exception {
                            newsRepo.deleteCache();
                            newsRepo.addToCache(((Feed)response.body()).getArticles());
                        }
                    }).observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io()).subscribe(new CompletableObserver() {
                        @Override
                        public void onSubscribe(Disposable d) {
                        }

                        @Override
                        public void onComplete() {
                        }

                        @Override
                        public void onError(Throwable e) {
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {

            }
        });
    }
}
