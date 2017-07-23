package com.github.andrejnazarov.itunesfinder.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author Nazarov on 23.07.17.
 */

public class ApiClient {
    private static final String BASE_URL = "https://itunes.apple.com/";
    private static Retrofit mRetrofit = null;

    private ApiClient() {
        throw new IllegalStateException("Can't create object");
    }

    public static Retrofit getClient() {
        if (mRetrofit == null) {
            mRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}