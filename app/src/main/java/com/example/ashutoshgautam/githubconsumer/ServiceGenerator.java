package com.example.ashutoshgautam.githubconsumer;

import android.util.Base64;

import com.jakewharton.retrofit.Ok3Client;

import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.client.OkClient;

/**
 * Created by ashutoshgautam on 16/12/16.
 */

public class ServiceGenerator {
    public static final String API_BASE_URL = "https://api.github.com/";
    final static String LOG_TAG = "LOG RESTApi";

    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(API_BASE_URL)
            .setClient(new Ok3Client(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            String credentials = username + ":" + password;
            // create Base64 encodet string
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestFacade request) {
                    request.addHeader("Authorization", basic);
                    request.addHeader("Accept", "application/json");
                }
            });
        }

        RestAdapter adapter = builder.setLogLevel(RestAdapter.LogLevel.FULL).setLog(new AndroidLog(LOG_TAG)).build();
        return adapter.create(serviceClass);
    }

}
