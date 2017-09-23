package com.github.andrejnazarov.itunesfinder.model;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.github.andrejnazarov.itunesfinder.ItunesApp;
import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;
import com.github.andrejnazarov.itunesfinder.net.TrackService;
import com.github.andrejnazarov.itunesfinder.presenter.MainPresenter;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Nazarov on 09.09.17.
 */

public class MainModel {

    @Inject
    ConnectivityManager mConnectivityManager;

    @Inject
    TrackService mTrackService;

    private final MainPresenter mMainPresenter;

    public MainModel(MainPresenter mainPresenter) {
        ItunesApp.getNetComponent().inject(this);
        mMainPresenter = mainPresenter;
    }

    public boolean onQueryTextSubmit(String query) {
        if (hasConnection()) {
            mMainPresenter.showProgress();
            getData(query);
            mMainPresenter.clearSearchViewFocus();
        } else {
            mMainPresenter.showNoConnectionError();
        }
        return true;
    }

    private boolean hasConnection() {
        boolean connected = false;
        if (mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            connected = true;
        }
        return connected;
    }

    private void getData(String query) {
        Call<TracksResponse> responseCall = mTrackService.getTracks(query);
        responseCall.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(Call<TracksResponse> call, Response<TracksResponse> response) {
                TracksResponse tracksResponse = response.body();
                if (tracksResponse != null) {
                    mMainPresenter.hideProgress();
                    mMainPresenter.showTracksFragment(tracksResponse);
                }
            }

            @Override
            public void onFailure(Call<TracksResponse> call, Throwable t) {
                mMainPresenter.showServiceUnavailableError();
                mMainPresenter.hideProgress();
            }
        });
    }
}