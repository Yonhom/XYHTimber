package com.xuyonghong.xyhtimber.adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.media.MediaManager;
import com.xuyonghong.xyhtimber.model.Artist;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private Context context;

    private List<Artist> artists;

    public ArtistAdapter(Context context) {
        this.context = context;
        artists = MediaManager.getInstance(context).getArtistList();
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_album_artist, parent, false);
        ArtistViewHolder holder = new ArtistViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {
        holder.albumArtistImage.setImageBitmap(
                BitmapFactory.decodeFile(artists.get(position).getArtistKey()));
        holder.albumArtistName.setText(artists.get(position).getArtistName());
        holder.albumTrackCount.setText(
                artists.get(position).getAlbumCount() + "张专辑|"
                        + artists.get(position).getTrackCount() + "首歌");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: do something when the artist item is clicked
            }
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    static class ArtistViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_artist_image)
        ImageView albumArtistImage;

        @BindView(R.id.album_name_artist_name)
        TextView albumArtistName;

        @BindView(R.id.artist_name_count_desc)
        TextView albumTrackCount;

        private View view;

        public ArtistViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            view = itemView;
        }

        public View getView() {
            return view;
        }
    }
}
