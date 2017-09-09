package com.github.andrejnazarov.itunesfinder;

import android.app.SearchManager;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.github.andrejnazarov.itunesfinder.bean.Track;
import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;
import com.github.andrejnazarov.itunesfinder.presenter.MainPresenter;
import com.github.andrejnazarov.itunesfinder.presenter.MainPresenterImpl;
import com.github.andrejnazarov.itunesfinder.view.MainView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main Activity contains search view and container for data
 *
 * @author Nazarov on 23.07.17
 */
public class MainActivity extends AppCompatActivity implements MainView,
        MainFragment.OnItemClickListener,
        SearchView.OnQueryTextListener {

    @BindView(R.id.search)
    SearchView mSearchView;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.progress_bar)
    ProgressBar mProgressBar;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mMainPresenter = new MainPresenterImpl(this);
        mMainPresenter.onCreate();
    }

    @Override
    public void onItemClick(Track track) {
        startActivity(TrackDetailActivity.createExplicitIntent(this, track));
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return mMainPresenter.onQueryTextSubmit(query);
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(mToolbar);
    }

    @Override
    public void showSearchView() {
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        mSearchView.setOnQueryTextListener(this);
    }

    @Override
    public void clearSearchViewFocus() {
        mSearchView.clearFocus();
    }

    @Override
    public void showNoConnectionError() {
        showToast(R.string.no_connection);
    }

    @Override
    public void showServiceUnavailableError() {
        showToast(R.string.error_message);
    }

    @Override
    public void showTracksFragment(TracksResponse response) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, MainFragment.newInstance(response))
                .addToBackStack(MainFragment.TAG)
                .commit();
    }

    @Override
    public void setProgressColor() {
        mProgressBar.getIndeterminateDrawable().setColorFilter(Color.BLUE, PorterDuff.Mode.MULTIPLY);
    }

    private void showToast(int messageResId) {
        Toast.makeText(this, getString(messageResId), Toast.LENGTH_LONG).show();
    }
}