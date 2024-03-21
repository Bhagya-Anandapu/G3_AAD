package com.self.newsfeed;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private RecyclerView datarv;
    private NewsAdapter adapter;

    private List<NewsModel> dataList;

    private List<NewsModel> filteredList;
    private ProgressBar loader;
    private EditText searchBar;
    String TAG = "Hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: view created");

        datarv = findViewById(R.id.data_r_v);
        loader = findViewById(R.id.loader);
        searchBar = findViewById(R.id.search_news);

        dataList = new ArrayList<>();
        filteredList = new ArrayList<>();
        adapter = new NewsAdapter(dataList);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        datarv.setLayoutManager(manager);
        datarv.setAdapter(adapter);

        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.d(TAG, editable.toString());
                String inputOfTheUser = editable.toString();
                if (!inputOfTheUser.isEmpty()) {
                    for (NewsModel data : dataList) {
                        if (data.getAuthor().toLowerCase().contains((inputOfTheUser.toLowerCase()))) {
                            filteredList.add(data);
                        }
                    }
                    adapter = new NewsAdapter((new ArrayList<>(filteredList)));
                    adapter.notifyDataSetChanged();
                } else {
                    adapter = new NewsAdapter(new ArrayList<>(dataList));
                    adapter.notifyDataSetChanged();
                }


            }
        });

        NewsAPI service = NewsService.getInstance();
        Call<NewsResponseModel> call = service.getNewsList("cricket","c00bf2ba7fa54075be945575596e051c");

        call.enqueue(new Callback<NewsResponseModel>() {

            @Override
            public void onResponse(@NonNull Call<NewsResponseModel> call, @NonNull Response<NewsResponseModel> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<NewsDetailedModel> resp = (List<NewsDetailedModel>) response.body().getNews();
                    Log.d(TAG, "onResponse: resp "+resp);
                    for(NewsDetailedModel news:resp){
                        dataList.add(new NewsModel(news.getTitle(), news.getAuthor(), news.getImageUrl(), news.getDescription(), news.getPublishedAt()));
                    }
                    loader.setVisibility(View.GONE);
                    adapter.notifyDataSetChanged();
                } else {
                    loader.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {

                loader.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();


            }
        });



    }

//    void getNews() {
//
//        NewsApiClient newsApiClient = new NewsApiClient("c00bf2ba7fa54075be945575596e051c");
//
//        newsApiClient.getTopHeadlines(
//
//                new TopHeadlinesRequest.Builder()
//                        .q("")
//                        .language("en")
//                        .build(),
//                new NewsApiClient.ArticlesResponseCallback() {
//                    @Override
//                    public void onSuccess(ArticleResponse response) {
//                        Log.d(TAG, "previous"+response.toString());
//                        List<Article> articles = response.getArticles();
//                        Log.d(TAG, "previous"+articles.toString());
//                        for (Article article : articles) {
//                            Log.d(TAG, article.getAuthor());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Throwable throwable) {
//                        Log.d(TAG, "previous"+"onFailure: failed");
//                        System.out.println(throwable.getMessage());
//                    }
//                }
//        );
//
//    }
}




