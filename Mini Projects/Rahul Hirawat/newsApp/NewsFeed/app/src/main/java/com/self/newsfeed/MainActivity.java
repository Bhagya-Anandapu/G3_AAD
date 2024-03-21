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

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private RecyclerView data;
    private AdapterForNewsApp newsadapter;

    private List<NewsModel> List;

    private List<NewsModel> filteredList;
    private ProgressBar load;
    private EditText searchkey;
    String TAG = "Hello";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: view created");

        data = findViewById(R.id.data_r_v);
        load = findViewById(R.id.loader);
        searchkey = findViewById(R.id.search_news);

        List = new ArrayList<>();
        filteredList = new ArrayList<>();
        newsadapter = new AdapterForNewsApp(List);
        LinearLayoutManager manager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        data.setLayoutManager(manager);
        data.setAdapter(newsadapter);

        searchkey.addTextChangedListener(new TextWatcher() {
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
                    for (NewsModel data : List) {
                        if (data.getAuthor().toLowerCase().contains((inputOfTheUser.toLowerCase()))) {
                            filteredList.add(data);
                        }
                    }
                    newsadapter = new AdapterForNewsApp((new ArrayList<>(filteredList)));
                    newsadapter.notifyDataSetChanged();
                } else {
                    newsadapter = new AdapterForNewsApp(new ArrayList<>(List));
                    newsadapter.notifyDataSetChanged();
                }


            }
        });

        NewsAPI service = NewsService.getInstance();
        Call<Response> call = service.getNewsList("Health","d3f5576652df44e6b2282feaabccf4d2");

        call.enqueue(new Callback<Response>() {

            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {

                if (response.isSuccessful() && response.body() != null) {
                    List<DetailedNewsModel> resp = (List<DetailedNewsModel>) response.body().getNews();
                    Log.d(TAG, "onResponse: resp "+resp);
                    for(DetailedNewsModel news:resp){
                        List.add(new NewsModel(news.getTitle(), news.getAuthor(), news.getImageUrl(), news.getDescription(), news.getPublishedAt()));
                    }
                    load.setVisibility(View.GONE);
                    newsadapter.notifyDataSetChanged();
                } else {
                    load.setVisibility(View.GONE);
                    Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

                load.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();


            }
        });



    }


}




