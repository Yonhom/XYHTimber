package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuyonghong.xyhtimber.R;

/**
 * Created by xuyonghong on 2017/6/7.
 */

public class AlbumDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_album_detail, container, false);
        return view;
    }
}
