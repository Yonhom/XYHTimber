package com.xuyonghong.xyhtimber.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by xuyonghong on 2017/6/5.
 */

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private Context context;

    public ArtistAdapter(Context context) {
        this.context = context;
    }

    @Override
    public ArtistViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ArtistViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ArtistViewHolder extends RecyclerView.ViewHolder {

        public ArtistViewHolder(View itemView) {
            super(itemView);
        }
    }
}
