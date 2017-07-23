package com.github.andrejnazarov.itunesfinder.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.andrejnazarov.itunesfinder.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Nazarov on 23.07.17.
 */

public class TrackViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TrackClickListener mClickListener;

    @BindView(R.id.cover_image_view)
    ImageView mCoverImageView;

    @BindView(R.id.artist_name_text_view)
    TextView mArtistNameTextView;

    @BindView(R.id.track_name_text_view)
    TextView mTrackNameTextView;

    public TrackViewHolder(View itemView, TrackClickListener listener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        itemView.setOnClickListener(this);
        mClickListener = listener;
    }

    @Override
    public void onClick(View v) {
        mClickListener.onClick(getLayoutPosition());
    }
}
