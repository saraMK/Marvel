package com.marvel.marvel.Ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.marvel.Adapters.ComicStoriesAdapter;
import com.marvel.marvel.Models.CharactersModel;
import com.marvel.marvel.Models.ComicStoriesModel;
import com.marvel.marvel.Models.QueriesModel;
import com.marvel.marvel.Models.ResultsModel;
import com.marvel.marvel.R;
import com.marvel.marvel.ViewModels.MarvelHeroesCharacters;
import com.marvel.marvel.ViewModels.Views.BaseView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.marvel.marvel.Adapters.MarvelHeroesAdapter.VIEW_TYPE;

public class MarvelHeroDetailsActivity extends AppCompatActivity implements BaseView<ComicStoriesModel>,ComicStoriesAdapter.ClickActions {

    protected ComicStoriesModel queriesModel;
    @BindView(R.id.comic_recycler)
    RecyclerView comic_recycler;
    @BindView(R.id.events_recycler)
    RecyclerView events_recycler;
    @BindView(R.id.serise_recycler)
    RecyclerView serise_recycler;
    @BindView(R.id.stories_recycler)
    RecyclerView stories_recycler;
    @BindView(R.id.hero_name_text)
    TextView hero_name_text;
    @BindView(R.id.hero_description_text)
    TextView hero_description_text;
    @BindView(R.id.toolbarImage)
    ImageView banner_img;
    @BindView(R.id.comic_text)
    TextView comic_text;
    @BindView(R.id.series_text)
    TextView series_text;
    @BindView(R.id.stories_text)
    TextView stories_text;
    @BindView(R.id.events_text)
    TextView events_text;


    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    protected MarvelHeroesCharacters viewModel;
    LinearLayoutManager[] linearLayoutManager=new LinearLayoutManager[4];
    RecyclerView[] recyclerViews;
    ComicStoriesAdapter[] adapter=new ComicStoriesAdapter[4];
    TextView[] headers;
    private ArrayList<ResultsModel> resultsModels=new ArrayList<>();
    private ResultsModel model;
    private HashMap<String,ArrayList<ResultsModel>> hashMap=new HashMap<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marvel_hero_details);
        ButterKnife.bind(this);
        model=(ResultsModel)getIntent().getSerializableExtra("model");

        setViews();

        viewModel = ViewModelProviders.of(this).get(MarvelHeroesCharacters.class);
        viewModel.setRepo(this);
        comic_recycler.setTag("comics");
        serise_recycler.setTag("series");
        stories_recycler.setTag("stories");
        events_recycler.setTag("events");
        recyclerViews=new RecyclerView[]{comic_recycler,serise_recycler,stories_recycler,events_recycler};
        headers=new TextView[]{comic_text,series_text,stories_text,events_text};
        setRecyclerViews();


    }

    private void setViews() {
        collapsingToolbarLayout.setTitle(model.getName());
        hero_name_text.setText(model.getName());
        hero_description_text.setText(model.getDescription());
        Picasso.get().load(model.getThumbnail().getPath()).into(banner_img);
    }


    void setRecyclerViews(){
        for (int i=0;i<4;i++) {
            linearLayoutManager[i] = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
            adapter[i]=new ComicStoriesAdapter(this,this);
            recyclerViews[i].setLayoutManager(linearLayoutManager[i]);
            recyclerViews[i].setAdapter(adapter[i]);
            setDataList(i);
        }
    }

    private void setDataList(final int i) {
         final String tag=recyclerViews[i].getTag().toString();
         queriesModel=new ComicStoriesModel(tag,model.getId());
        viewModel.getComicEtc().observe(this, new Observer<CharactersModel>() {
            @Override
            public void onChanged(@Nullable CharactersModel charactersModel) {

                if (charactersModel.getData().getCount() != 0) {
                     resultsModels= (ArrayList<ResultsModel>) charactersModel.getData().getResults();
                     adapter[i].setResultsModels(resultsModels);
                    adapter[i].notifyDataSetChanged();
                    headers[i].setVisibility(View.VISIBLE);
                }

            }
        });
    }

    @Override
    @OnClick(R.id.back)
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public void noConnection() {

    }

    @Override
    public void networkError() {

    }

    @Override
    public ComicStoriesModel getQueries() {
        return queriesModel;
    }

    @Override
    public void clickItem(int position, ArrayList<ResultsModel> arrayList) {
        Log.d("clickItem"," "+arrayList.get(0).getName()+" "+hashMap);
        Intent intent=new Intent(this,MarvelHeroCoverActivity.class);
        intent.putExtra("images",  arrayList);
        intent.putExtra("position",  position);
        startActivity(intent);
    }
}
