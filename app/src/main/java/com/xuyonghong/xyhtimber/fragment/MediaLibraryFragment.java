package com.xuyonghong.xyhtimber.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xuyonghong.xyhtimber.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuyonghong on 2017/6/3.
 */

public class MediaLibraryFragment extends Fragment {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.main_tabs)
    TabLayout mainTabs;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.media_library_fragment, container, false);
        ButterKnife.bind(this, view);

        /*
        the tool bar initialization
         */
        // set the tool bar as the fragment's action bar
        ((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        // get the set action bar above to assign a burger icon
        ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // TODO: manipulate navigation drawer open/close from fragment


        return view;
    }
}
