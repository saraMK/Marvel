package com.marvel.marvel.Ui;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.marvel.marvel.Adapters.ClickActions;
import com.marvel.marvel.Adapters.MarvelHeroesAdapter;
import com.marvel.marvel.Models.CharactersModel;
import com.marvel.marvel.Models.QueriesModel;
import com.marvel.marvel.Models.ResultsModel;
import com.marvel.marvel.R;
import com.marvel.marvel.ViewModels.MarvelHeroesCharacters;
import com.marvel.marvel.ViewModels.Views.BaseView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.marvel.marvel.Adapters.MarvelHeroesAdapter.VIEW_TYPE;

public class BaseMarvelHeroActivity extends FragmentActivity implements BaseView<QueriesModel>, ClickActions {

    @BindView(R.id.hero_marvel_recyclerView)
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    MarvelHeroesAdapter adapter;
    protected List<ResultsModel> resultsModels = new ArrayList<>();
    protected MarvelHeroesCharacters viewModel;
    protected QueriesModel queriesModel;
    protected int index = 0;
    protected boolean isLoading;
    protected Intent intent;
    protected int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        queriesModel = new QueriesModel();

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MarvelHeroesAdapter(this, type);
        recyclerView.setAdapter(adapter);
        viewModel = ViewModelProviders.of(this).get(MarvelHeroesCharacters.class);
        viewModel.setRepo(this);


    }

    protected void loadData() {
        viewModel.getCharacters().observe(this, new Observer<CharactersModel>() {
            @Override
            public void onChanged(@Nullable CharactersModel charactersModel) {
                isLoading = false;
                if (type == VIEW_TYPE) {
                    if (charactersModel.getData().getCount() != 0) {
                        resultsModels.addAll(charactersModel.getData().getResults());
                        }
                } else {
                    resultsModels = (charactersModel.getData().getResults());
                }
               notifyAdapter();
            }
        });
    }

    protected void notifyAdapter() {
        adapter.setResultsModels(resultsModels);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void noConnection() {
        errorMsg(getString(R.string.noConnection));
    }

    @Override
    public void networkError() {
        errorMsg(getString(R.string.connectionError));
    }

    private void errorMsg(String msg) {
        index--;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        isLoading = false;
        adapter.setSize(resultsModels.size());
        adapter.notifyDataSetChanged();
    }

    @Override
    public QueriesModel getQueries() {

        return queriesModel;
    }

    @Override
    public void clickItem(int position) {
        intent = new Intent(this, MarvelHeroDetailsActivity.class);
        intent.putExtra("model", resultsModels.get(position));
        startActivity(intent);
    }
}

