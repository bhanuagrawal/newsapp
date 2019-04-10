package com.ultimatix.cashrich.network;

import com.ultimatix.cashrich.datamodels.Feed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface NewsService {
    @Headers({"Accept: application/json"})
    @GET("/v2/top-headlines?country=in")
    Call<Feed> getTopHeadlines();
}