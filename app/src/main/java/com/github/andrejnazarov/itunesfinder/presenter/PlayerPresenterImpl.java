package com.github.andrejnazarov.itunesfinder.presenter;

import com.github.andrejnazarov.itunesfinder.model.PlayerModel;
import com.github.andrejnazarov.itunesfinder.view.PlayerView;

/**
 * @author Nazarov on 09.09.17.
 */

public class PlayerPresenterImpl implements PlayerPresenter {

    private final PlayerView mPlayerView;
    private final PlayerModel mPlayerModel;

    public PlayerPresenterImpl(PlayerView playerView) {
        mPlayerView = playerView;
        mPlayerModel = new PlayerModel(this);
    }

    @Override
    public void onCreate() {
        mPlayerModel.onCreate();
    }

    @Override
    public void onViewCreated() {
        mPlayerModel.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        mPlayerModel.onDestroyView();
    }

    @Override
    public void onPlayButtonClick(String audioUrl) {
        mPlayerModel.onPlayButtonClick(audioUrl);
    }

    @Override
    public void onPauseButtonClick() {
        mPlayerModel.onPauseButtonClick();
    }

    @Override
    public void setSeekBarMax(int max) {
        mPlayerView.setSeekBarMax(max);
    }

    @Override
    public void setSeekBarProgress(int progress) {
        mPlayerView.setSeekBarProgress(progress);
    }

    @Override
    public void setPlayButtonEnabled(boolean enabled) {
        mPlayerView.setPlayButtonEnabled(enabled);
    }

    @Override
    public void setPauseButtonEnabled(boolean enabled) {
        mPlayerView.setPauseButtonEnabled(enabled);
    }
}