package com.example.ashutoshgautam.githubconsumer;

import com.google.gson.annotations.Expose;

/**
 * Created by ashutoshgautam on 16/12/16.
 */
public class Github {

    @Expose public String login;
    @Expose public long id;
    @Expose public String repos_url;

}
