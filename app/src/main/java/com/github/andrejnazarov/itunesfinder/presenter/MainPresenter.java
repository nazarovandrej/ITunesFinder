package com.github.andrejnazarov.itunesfinder.presenter;

import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;

/**
 * @author Nazarov on 09.09.17.
 */

public interface MainPresenter {

    void showProgress();

    void hideProgress();

    void clearSearchViewFocus();

    void showNoConnectionError();

    void showServiceUnavailableError();

    void showTracksFragment(TracksResponse response);

    void onCreate();

    boolean onQueryTextSubmit(String query);
}