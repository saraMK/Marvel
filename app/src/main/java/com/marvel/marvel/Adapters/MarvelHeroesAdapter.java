package com.marvel.marvel.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.marvel.Models.ResultsModel;
import com.marvel.marvel.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MarvelHeroesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final int ITEM_VIEW_TYPE_BASIC = 2;
    private final int ITEM_VIEW_TYPE_PROGRESS = 1;
    public static final int SEARCH_TYPE = 4;
    public static final int VIEW_TYPE = 5;
    private int type;
    private List<ResultsModel> resultsModels;
    private int size;
    private ClickActions clickActions;
    public MarvelHeroesAdapter(ClickActions clickActions, int type) {
        this.resultsModels = new ArrayList<>();
        this.type = type;
        this.clickActions=clickActions;
        setSize(resultsModels.size() + 1);

    }

    public void setSize(int size) {
        if (type == VIEW_TYPE)
            this.size = size;
        else this.size = resultsModels.size();
    }

    public void setResultsModels(List<ResultsModel> resultsModels) {
        this.resultsModels = resultsModels;
        setSize(resultsModels.size() + 1);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        //create the view for each corresponding viewtype
        if (viewType == ITEM_VIEW_TYPE_BASIC) {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    type == VIEW_TYPE ? R.layout.marvel_heros_item : R.layout.search_item, parent, false);
            vh = new MarelViewHolder(v);
        } else {
            View v = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.prograss_bar, parent, false);
            vh = new ProgressViewHolder(v);
        }
        return vh;
    }

    @Override
    public int getItemViewType(int position) {
        return position < resultsModels.size() ? ITEM_VIEW_TYPE_BASIC : ITEM_VIEW_TYPE_PROGRESS;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mHolder, final int position) {

        if (mHolder instanceof MarelViewHolder) {
            MarelViewHolder holder = (MarelViewHolder) mHolder;
            holder.bind(resultsModels.get(position));
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    clickActions.clickItem(position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

}
