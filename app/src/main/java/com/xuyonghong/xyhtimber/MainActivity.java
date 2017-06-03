package com.xuyonghong.xyhtimber;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.xuyonghong.xyhtimber.adapter.MainContentFragmentPagerAdapter;
import com.xuyonghong.xyhtimber.util.CommonUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.main_tabs)
    TabLayout mainTabs;
    @BindView(R.id.content_view_pager)
    ViewPager contentPager;

    @BindView(R.id.persistent_bottom_sheet)
    ConstraintLayout persistentBottomSheet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // tool bar
        setSupportActionBar(toolbar); // set the toolbar provided in xml as the activity tool bar

        // fab: fab has a default behavior to deal with the snackbar's intrusion. when the snackbar
        // is popped up, it will move up accordingly
        final BottomSheetBehavior<ConstraintLayout> bottomSheetBehavior
                = BottomSheetBehavior.from(persistentBottomSheet);
        bottomSheetBehavior.setPeekHeight(CommonUtils.dip2px(
                this, getResources().getDimension(R.dimen.bottom_sheet_peek_view_height)));
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        // navigation drawer
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle); // add a drawer toggle so the burger button can react as a drawer opener when clicked
        toggle.syncState();

        // if the navigation item selected listener is not implemented, the navi item will not response to click
        navigationView.setNavigationItemSelectedListener(this);

        // init content view pager
        contentPager.setAdapter(
                new MainContentFragmentPagerAdapter(getSupportFragmentManager()));
        // associate the tab layout with the view pager
        mainTabs.setupWithViewPager(contentPager);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /**
     * this is the place we add menu items on the action bar
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    /**
     * menu item on action bar selected action implemented here
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * navigation drawer item selected action implemented here
     * @param item
     * @return
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
