package com.marvel.marvel.Ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.marvel.marvel.R;

import butterknife.OnClick;
import butterknife.OnTextChanged;

import static com.marvel.marvel.Adapters.MarvelHeroesAdapter.SEARCH_TYPE;
import static com.marvel.marvel.Adapters.MarvelHeroesAdapter.VIEW_TYPE;

public class SearchActivity extends BaseMarvelHeroActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_search);
        type = SEARCH_TYPE;
        super.onCreate(savedInstanceState);
    }

    @OnTextChanged(R.id.search_field_text)
    void search_text(CharSequence charSequence) {
        Log.d("search_text",charSequence.toString());
        queriesModel.setSearchByName(charSequence.toString());
        if (!charSequence.toString().isEmpty())
            loadData();
        else {
            resultsModels.clear();
            notifyAdapter();
        }
    }

    @Override
    @OnClick(R.id.cancel_btn)
    public void onBackPressed() {
        super.onBackPressed();
    }
}
