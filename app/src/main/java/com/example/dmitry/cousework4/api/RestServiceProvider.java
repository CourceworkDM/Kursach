package com.example.dmitry.cousework4.api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by dmitry on 11.11.17.
 */

public class RestServiceProvider {

    private static final RestServiceProvider INSTANCE = new RestServiceProvider();

    private IRestService iRestService;

    private RestServiceProvider() {    }

    public static RestServiceProvider newInstance() { return  INSTANCE; }

    public synchronized final IRestService getiRestService() {
        if (iRestService == null) {
            iRestService = createIRestService();
        }
        return  iRestService;
    }

    private IRestService createIRestService() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IRestService.BASE_URL)
                .client(provideClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(IRestService.class);
    }

    private OkHttpClient provideClient () {
        HttpLoggingInterceptor logging  = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }
}
