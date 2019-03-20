package com.marvel.marvel.Ui;

import android.app.Fragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

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

public class MarvelHerosActivity extends BaseMarvelHeroActivity
{

    private int totalItemCount,lastVisibleItem;
    @BindView(R.id.logo_img)
    ImageView logo_img;
    @BindView(R.id.search_img)
    ImageView search_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_marvel_heros);
        type=VIEW_TYPE;
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        search_img.setVisibility(View.VISIBLE);
        logo_img.setVisibility(View.VISIBLE);
        queriesModel.setIndex(index);
        loadData();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                super.onScrolled(recyclerView, dx, dy);
                totalItemCount =linearLayoutManager.getItemCount();
                lastVisibleItem =linearLayoutManager.findLastCompletelyVisibleItemPosition();
                Log.d("lastVisibleItem",totalItemCount+" "+lastVisibleItem+" "+isLoading+" "+(totalItemCount <= (lastVisibleItem+1)));
                if (!isLoading && totalItemCount <= (lastVisibleItem+1)) {
                    isLoading = true;
                    index++;
                    queriesModel.setIndex(index);
                    loadData();

                }
            }
        });
    }

     protected void loadData(){
        super.loadData();
    }


    @OnClick(R.id.search_img)
    void search() {
        intent=new Intent(this,SearchActivity.class);
        startActivity(intent);
    }





}
