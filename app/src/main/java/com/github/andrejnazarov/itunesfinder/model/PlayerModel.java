package com.github.andrejnazarov.itunesfinder.model;

import android.media.MediaPlayer;
import android.os.Handler;
import android.util.Log;

import com.github.andrejnazarov.itunesfinder.presenter.PlayerPresenter;

/**
 * @author Nazarov on 09.09.17.
 */

public class PlayerModel {

    private static final String TAG = PlayerModel.class.getSimpleName();
    private final PlayerPresenter mPlayerPresenter;

    private boolean mIsAudioPlaying = false;
    private MediaPlayer mMediaPlayer = null;
    private double mStartTime = 0;
    private double mFinalTime = 0;
    private int mOneTimeOnly;
    private Handler mHandler;
    private UpdateSongTime mUpdateSongTime;

    public PlayerModel(PlayerPresenter playerPresenter) {
        mPlayerPresenter = playerPresenter;
    }

    public void onCreate() {
        mHandler = new Handler();
        mUpdateSongTime = new UpdateSongTime();
    }

    public void onViewCreated() {
        mPlayerPresenter.setPauseButtonEnabled(false);
        mMediaPlayer = new MediaPlayer();
        mOneTimeOnly = 0;
    }

    public void onDestroyView() {
        mHandler.removeCallbacks(mUpdateSongTime);
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }
    }

    public void onPlayButtonClick(String audioUrl) {
        try {
            if (mIsAudioPlaying) {
                mIsAudioPlaying = false;
                mMediaPlayer.release();
                mMediaPlayer = null;
            } else {
                mIsAudioPlaying = true;
                if (mOneTimeOnly == 0) {
                    mMediaPlayer.setDataSource(audioUrl);
                    mMediaPlayer.prepare();
                }
                mMediaPlayer.start();
                mFinalTime = mMediaPlayer.getDuration();
                if (mStartTime == mFinalTime) {
                    mStartTime = 0;
                } else {
                    mStartTime = mMediaPlayer.getCurrentPosition();
                }
                if (mOneTimeOnly == 0) {
                    mPlayerPresenter.setSeekBarMax((int) mFinalTime);
                    mOneTimeOnly = 1;
                }
                mPlayerPresenter.setSeekBarProgress((int) mStartTime);
                mHandler.postDelayed(mUpdateSongTime, 100);
                mPlayerPresenter.setPauseButtonEnabled(true);
                mPlayerPresenter.setPlayButtonEnabled(false);
            }
        } catch (Exception e) {
            Log.e(TAG, "exception is here " + e.getMessage());
        }
    }

    public void onPauseButtonClick() {
        mIsAudioPlaying = false;
        mMediaPlayer.pause();
        mPlayerPresenter.setPlayButtonEnabled(true);
        mPlayerPresenter.setPauseButtonEnabled(false);
    }

    private class UpdateSongTime implements Runnable {
        @Override
        public void run() {
            mStartTime = mMediaPlayer.getCurrentPosition();
            mPlayerPresenter.setSeekBarProgress((int) mStartTime);
            mHandler.postDelayed(this, 100);
        }
    }
}