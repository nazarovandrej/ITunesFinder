package com.github.andrejnazarov.itunesfinder;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment to show Music Player
 *
 * @author Nazarov on 24.07.17
 */
public class MusicPlayerFragment extends Fragment {

    public static final String TAG = MusicPlayerFragment.class.getSimpleName();
    private static final String EXTRA_AUDIO_URL = "extra_audio_url";

    @BindView(R.id.play_button)
    Button mPlayButton;

    @BindView(R.id.pause_button)
    Button mPauseButton;

    @BindView(R.id.seek_bar)
    AppCompatSeekBar mSeekBar;

    private String mAudioUrl;

    private boolean mIsAudioPlaying = false;
    private MediaPlayer mMediaPlayer = null;
    private Handler mHandler;
    private double mStartTime = 0;
    private double mFinalTime = 0;
    private int mOneTimeOnly;

    private UpdateSongTime mUpdateSongTime;

    public static MusicPlayerFragment newInstance(String audioUrl) {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_AUDIO_URL, audioUrl);
        fragment.setArguments(args);
        return fragment;
    }

    //region Fragment LifeCycle

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAudioUrl = getArguments().getString(EXTRA_AUDIO_URL);
        }
        mHandler = new Handler();
        mUpdateSongTime = new UpdateSongTime();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_music_player, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mPauseButton.setEnabled(false);
        mMediaPlayer = new MediaPlayer();
        mOneTimeOnly = 0;
    }

    //endregion

    @OnClick({R.id.play_button, R.id.pause_button})
    public void onButtonClick(Button button) {
        switch (button.getId()) {
            case R.id.play_button:
                playAudio();
                break;
            case R.id.pause_button:
                pauseAudio();
                break;
        }
    }

    public void onBackPressed() {
        mHandler.removeCallbacks(mUpdateSongTime);
        if (mMediaPlayer != null)
            mMediaPlayer.release();
    }

    //region private methods

    private void playAudio() {
        try {
            if (!mIsAudioPlaying) {
                mIsAudioPlaying = true;
                if (mOneTimeOnly == 0) {
                    mMediaPlayer.setDataSource(mAudioUrl);
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
                    mSeekBar.setMax((int) mFinalTime);
                    mOneTimeOnly = 1;
                }
                mSeekBar.setProgress((int) mStartTime);
                mHandler.postDelayed(mUpdateSongTime, 100);
                mPauseButton.setEnabled(true);
                mPlayButton.setEnabled(false);
            } else {
                mIsAudioPlaying = false;
                mMediaPlayer.release();
                mMediaPlayer = null;
            }
        } catch (Exception e) {
            Log.e(TAG, "exception is here " + e.getMessage());
        }
    }

    private void pauseAudio() {
        mIsAudioPlaying = false;
        mMediaPlayer.pause();
        mPlayButton.setEnabled(true);
        mPauseButton.setEnabled(false);
    }
    //endregion

    private class UpdateSongTime implements Runnable {
        @Override
        public void run() {
            mStartTime = mMediaPlayer.getCurrentPosition();
            mSeekBar.setProgress((int) mStartTime);
            mHandler.postDelayed(this, 100);
        }
    }
}