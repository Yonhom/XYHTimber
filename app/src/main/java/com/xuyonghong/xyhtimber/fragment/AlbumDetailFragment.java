package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.xuyonghong.xyhtimber.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/6/7.
 */

public class AlbumDetailFragment extends Fragment {
    @BindView(R.id.toolbar)
    Toolbar toobar;

    @BindView(R.id.app_bar_image)
    ImageView appBarImage;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_detail, container, false);
        ButterKnife.bind(this, view);

        // init the toolbar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toobar);

        return view;
    }
}
