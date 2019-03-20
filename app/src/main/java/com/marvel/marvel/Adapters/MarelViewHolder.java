package com.marvel.marvel.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marvel.marvel.Models.ResultsModel;
import com.marvel.marvel.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

class MarelViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.hero_banner_img)
    ImageView hero_banner_img;
    @BindView(R.id.hero_name_text)
    TextView hero_name_text;
    public MarelViewHolder(View view) {
        super(view);
        ButterKnife.bind(this,view);
    }

    public void bind(ResultsModel model) {
        hero_name_text.setText(model.getName());
        if (model.getThumbnail() != null) {
            String img = model.getThumbnail().getPath();
            Picasso.get().load(img).error(R.drawable.image_not_available).into(hero_banner_img);
        }
    }
}
