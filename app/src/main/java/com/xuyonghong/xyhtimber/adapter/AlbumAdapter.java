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
import com.xuyonghong.xyhtimber.model.Album;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private Context context;

    private List<Album> albums;

    public static final String ALBUM_PATH = "content://media/external/audio/albumart";

    public AlbumAdapter(Context context) {
        this.context = context;

        // initialize data source
        albums = MediaManager.getInstance(context).getAlbumList();
    }

    @Override
    public AlbumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_album_artist, parent, false);
        AlbumViewHolder viewHolder = new AlbumViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AlbumViewHolder holder, int position) {

        // set the album art image
        holder.albumImage.setImageBitmap(BitmapFactory.decodeFile(albums.get(position).getAlnumArt()));

        holder.artistName.setText(albums.get(position).getArtist());
        holder.albumName.setText(albums.get(position).getAlbum());

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    static class AlbumViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_artist_image)
        ImageView albumImage;
        @BindView(R.id.album_name_artist_name)
        TextView albumName;
        @BindView(R.id.artist_name_count_desc)
        TextView artistName;

        public AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
