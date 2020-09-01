package com.example.as.Module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class RetrofitModule {

    private String baseURL;

    public RetrofitModule(String baseURL) {
        this.baseURL = baseURL;
    }

    @Provides
    @Singleton
    public Retrofit retrofitBuilder() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit;
    }

}
