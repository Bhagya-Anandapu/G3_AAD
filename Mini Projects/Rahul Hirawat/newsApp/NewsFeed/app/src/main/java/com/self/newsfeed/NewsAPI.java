package com.self.newsfeed;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsAPI {
    @GET ("everything")
    Call<Response> getNewsList(
            @Query("q") String category,
            @Query("apiKey") String accessKey
    );
}