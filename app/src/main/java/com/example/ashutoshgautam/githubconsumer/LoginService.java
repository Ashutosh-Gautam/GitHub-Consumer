package com.example.ashutoshgautam.githubconsumer;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by ashutoshgautam on 16/12/16.
 */

public interface LoginService {
    @GET("/user")
    void basicLogin(Callback<Github> callback);

    @GET("/users/{user_name}/repos")
    void fetchFeed(@Path("user_name") String user_name , Callback<List<FeedItem>> callback);
}
