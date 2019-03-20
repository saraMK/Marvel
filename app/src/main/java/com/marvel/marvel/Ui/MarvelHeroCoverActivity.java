package com.marvel.marvel.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.marvel.Adapters.ComicStoriesAdapter;
import com.marvel.marvel.Models.ResultsModel;
import com.marvel.marvel.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MarvelHeroCoverActivity extends AppCompatActivity {

    @BindView(R.id.hero_cover_recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.close_img)
    ImageView close_img;
    @BindView(R.id.page_index_text)
    TextView page_index_text;
    LinearLayoutManager linearLayoutManager;
    ComicStoriesAdapter adapter;
    int position;
    private ArrayList<ResultsModel> resultsModels = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_hero_cover);
        ButterKnife.bind(this);
        close_img.setVisibility(View.VISIBLE);
        resultsModels = (ArrayList<ResultsModel>) getIntent().getSerializableExtra("images");
        position = getIntent().getExtras().getInt("position");
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new ComicStoriesAdapter(this, null);
        adapter.setResultsModels(resultsModels);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(position);

        SnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        setPageIndex(position);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (newState == 0)
                    setPageIndex(linearLayoutManager.findFirstCompletelyVisibleItemPosition());
            }
        });



    }
    private void setPageIndex(int pos){
        page_index_text.setText((pos+ 1) + "/" + resultsModels.size());

    }

    @Override
    @OnClick(R.id.close_img)
    public void onBackPressed() {
        super.onBackPressed();
    }
}
