package com.github.andrejnazarov.itunesfinder.view;

import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;

/**
 * @author Nazarov on 09.09.17.
 */

public interface MainView {

    void showProgress();

    void hideProgress();

    void setToolbar();

    void showSearchView();

    void clearSearchViewFocus();

    void showNoConnectionError();

    void showServiceUnavailableError();

    void showTracksFragment(TracksResponse response);

    void setProgressColor();
}