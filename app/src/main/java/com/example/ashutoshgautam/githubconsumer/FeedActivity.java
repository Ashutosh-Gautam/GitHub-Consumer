package com.example.ashutoshgautam.githubconsumer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by ashutoshgautam on 16/12/16.
 */

public class FeedActivity extends AppCompatActivity{

    private ArrayList<FeedItem> feedList = null;
    private ProgressBar progressbar = null;
    private ListView feedListView = null;
    private SwipeRefreshLayout mSwipeRefreshLayout = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts_list);

        loadNewsFeed();

        progressbar = (ProgressBar) findViewById(R.id.progressBar);

        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Refreshing data on server
                loadNewsFeed();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    private void loadNewsFeed() {
        String url = getIntent().getStringExtra("feed_url");
        LoginService loginService =
                ServiceGenerator.createService(LoginService.class);

        loginService.fetchFeed(url, new Callback<List<FeedItem>>() {
            @Override
            public void success(List<FeedItem> feedItems, Response response) {
                // user object available
                updateList(feedItems);

            }

            @Override
            public void failure(RetrofitError error) {
                // handle errors, too
                Log.d("Feeds", error.getMessage());
            }
        });

    }


    public void updateList(List<FeedItem> feedList) {

        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }

        feedListView= (ListView) findViewById(R.id.custom_list);
        feedListView.setVisibility(View.VISIBLE);
        progressbar.setVisibility(View.GONE);

        feedListView.setAdapter(new CustomListAdapter(this, feedList));
        feedListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = feedListView.getItemAtPosition(position);
                FeedItem newsData = (FeedItem) o;

                Intent intent = new Intent(FeedActivity.this, FeedDetailsActivity.class);
                intent.putExtra("feed", newsData);
                startActivity(intent);
            }
        });
    }
}
