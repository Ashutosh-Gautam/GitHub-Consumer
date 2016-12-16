package com.example.ashutoshgautam.githubconsumer;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by ashutoshgautam on 16/12/16.
 */
public class FeedItem implements Serializable{

    @Expose public String name;
    @Expose public String language;
    @Expose public String updated_at;
    @Expose public String html_url;
}
