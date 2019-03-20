package com.marvel.marvel.Adapters;

import android.app.Activity;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marvel.marvel.Models.ResultsModel;
import com.marvel.marvel.R;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ComicStoriesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private ArrayList<ResultsModel> resultsModels;

    private ClickActions clickActions;

    public ComicStoriesAdapter(Activity activity, ClickActions clickActions) {
        this.resultsModels = new ArrayList<>();
        this.clickActions = clickActions;

    }


    public void setResultsModels(ArrayList<ResultsModel> resultsModels) {
        this.resultsModels = resultsModels;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh;
        //create the view for each corresponding viewtype
        View v = LayoutInflater.from(parent.getContext()).inflate(clickActions != null ? R.layout.related_stories_item : R.layout.marvel_hero_cover_item, parent, false);
        vh = new MarelViewHolder(v);

        return vh;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder mHolder, final int position) {

        MarelViewHolder holder = (MarelViewHolder) mHolder;
        holder.bind(resultsModels.get(position));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickActions != null)
                    clickActions.clickItem(position, resultsModels);
            }
        });
    }

    @Override
    public int getItemCount() {
        return resultsModels.size();
    }

    public interface ClickActions {
        void clickItem(int position, ArrayList<ResultsModel>  arrayList);
    }

}

