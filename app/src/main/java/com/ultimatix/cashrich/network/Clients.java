package com.ultimatix.cashrich.network;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ultimatix.cashrich.Myapp;

import java.io.IOException;

import butterknife.internal.Utils;
import okhttp3.Cache;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Clients {


    private static final String BASE_URL = "https://newsapi.org/" ;
    private static final String API_KEY = "7d5ca6a09b6a4dc4b5e508d747e9f1f0" ;

    Application application;

    private Retrofit retrofit = null;


    public Retrofit getNewsClient() {
        if (retrofit==null) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            Cache cache = new Cache(application.getCacheDir(), 5 * 1024 * 1024);
            OkHttpClient.Builder defaultHttpClient= new OkHttpClient.Builder();
            defaultHttpClient.addInterceptor(logging);
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();


            defaultHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request original = chain.request();


                    HttpUrl originalHttpUrl = original.url();

                    HttpUrl url = originalHttpUrl.newBuilder()
                            .addQueryParameter("apiKey", API_KEY)
                            .build();

                    // Request customization: add request headers
                    Request.Builder requestBuilder = original.newBuilder()
                            .url(url);


                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });


            defaultHttpClient.cache(cache);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(defaultHttpClient.build())
                    .build();
        }
        return retrofit;
    }

    public Clients(Application application) {
        this.application = application;
    }

}
