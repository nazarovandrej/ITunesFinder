package com.github.andrejnazarov.itunesfinder.presenter;

import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;
import com.github.andrejnazarov.itunesfinder.model.MainModel;
import com.github.andrejnazarov.itunesfinder.view.MainView;

/**
 * @author Nazarov on 09.09.17.
 */

public class MainPresenterImpl implements MainPresenter {

    private final MainView mMainView;
    private final MainModel mMainModel;

    public MainPresenterImpl(MainView mainView) {
        mMainView = mainView;
        mMainModel = new MainModel(this);
    }

    @Override
    public void showProgress() {
        mMainView.showProgress();
    }

    @Override
    public void hideProgress() {
        mMainView.hideProgress();
    }

    @Override
    public void clearSearchViewFocus() {
        mMainView.clearSearchViewFocus();
    }

    @Override
    public void showNoConnectionError() {
        mMainView.showNoConnectionError();
    }

    @Override
    public void showServiceUnavailableError() {
        mMainView.showServiceUnavailableError();
    }

    @Override
    public void showTracksFragment(TracksResponse response) {
        mMainView.showTracksFragment(response);
    }

    @Override
    public void onCreate() {
        mMainView.setToolbar();
        mMainView.showSearchView();
        mMainView.setProgressColor();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return mMainModel.onQueryTextSubmit(query);
    }
}