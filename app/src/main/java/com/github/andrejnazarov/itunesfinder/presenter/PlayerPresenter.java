package com.github.andrejnazarov.itunesfinder.presenter;

/**
 * @author Nazarov on 09.09.17.
 */

public interface PlayerPresenter {

    void onCreate();

    void onViewCreated();

    void onDestroyView();

    void onPlayButtonClick(String audioUrl);

    void onPauseButtonClick();

    void setSeekBarMax(int max);

    void setSeekBarProgress(int progress);

    void setPlayButtonEnabled(boolean enabled);

    void setPauseButtonEnabled(boolean enabled);
}