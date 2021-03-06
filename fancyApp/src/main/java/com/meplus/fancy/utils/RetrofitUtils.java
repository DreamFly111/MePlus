package com.meplus.fancy.utils;

import com.meplus.fancy.Constants;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by dandanba on 3/9/16.
 */
public class RetrofitUtils {

    public static Retrofit getClient() {
        // Define the interceptor, add authentication headers
        Interceptor interceptor = chain -> {
            Request newRequest = chain.request().newBuilder()
                    .addHeader("X-FANCY-APPNO", Constants.APPNO)
                    .addHeader("X-FANCY-APIVERSION", Constants.APIVERSION)
                    .build();
            return chain.proceed(newRequest);
        };

        // Add the interceptor to OkHttpClient
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
//                .followRedirects(true)
//                .followSslRedirects(true)
//                .retryOnConnectionFailure(true)
//                .connectTimeout(15, TimeUnit.SECONDS)
//                .writeTimeout(30, TimeUnit.SECONDS)
//                .readTimeout(30, TimeUnit.SECONDS)
//                .cache(null)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create(JsonUtils.getObjectMapper()))
                .client(client)
                .build();

        return retrofit;
    }

    public static void subscribe(Observable observable, Subscriber subscriber) {
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
