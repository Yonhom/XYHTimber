package com.xuyonghong.xyhtimber.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xuyonghong.xyhtimber.R;
import com.xuyonghong.xyhtimber.fragment.AlbumDetailFragment;
import com.xuyonghong.xyhtimber.media.MediaManager;
import com.xuyonghong.xyhtimber.model.Album;
import com.xuyonghong.xyhtimber.transition.DetailTransition;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.AlbumViewHolder> {
    private Context context;

    private List<Album> albums;

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
    public void onBindViewHolder(AlbumViewHolder holder, final int position) {

        // set the album art image
        holder.albumImage.setImageBitmap(BitmapFactory.decodeFile(albums.get(position).getAlnumArt()));
        holder.artistName.setText(albums.get(position).getArtist());
        holder.albumName.setText(albums.get(position).getAlbum());

        // set transition name for for the shared element
        ViewCompat.setTransitionName(holder.albumImage, "image_" + position);

    }

    @Override
    public int getItemCount() {
        return albums.size();
    }

    class AlbumViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.album_artist_image)
        ImageView albumImage;
        @BindView(R.id.album_name_artist_name)
        TextView albumName;
        @BindView(R.id.artist_name_count_desc)
        TextView artistName;

        /**
         *
         * @param itemView the itemView is the view that should handle a click event to open the
         *                 album detail page
         */
        public AlbumViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // translate to the detail fragment with a album image view transition animation
                    AlbumDetailFragment adFragment = AlbumDetailFragment.newInstance(0);

                    // the transition to use for shared view
                    adFragment.setSharedElementEnterTransition(new DetailTransition());
                    adFragment.setEnterTransition(new Fade());
                    ((Activity)context).getWindow().setExitTransition(new Fade());
                    adFragment.setSharedElementReturnTransition(new DetailTransition());

                    ((AppCompatActivity) context).getSupportFragmentManager()
                            .beginTransaction()
                            .addSharedElement(albumImage, "detailImage")
                            .add(R.id.fragment_container, adFragment)
                            .addToBackStack(null)
                            .commit();

                }
            });
        }

    }
}
