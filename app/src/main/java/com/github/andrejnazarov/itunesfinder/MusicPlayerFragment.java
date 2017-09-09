package com.github.andrejnazarov.itunesfinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.andrejnazarov.itunesfinder.presenter.PlayerPresenter;
import com.github.andrejnazarov.itunesfinder.presenter.PlayerPresenterImpl;
import com.github.andrejnazarov.itunesfinder.view.PlayerView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Fragment to show Music Player
 *
 * @author Nazarov on 24.07.17
 */
public class MusicPlayerFragment extends Fragment implements PlayerView{

    public static final String TAG = MusicPlayerFragment.class.getSimpleName();
    private static final String EXTRA_AUDIO_URL = "extra_audio_url";

    @BindView(R.id.play_button)
    Button mPlayButton;

    @BindView(R.id.pause_button)
    Button mPauseButton;

    @BindView(R.id.seek_bar)
    AppCompatSeekBar mSeekBar;

    private String mAudioUrl;
    private PlayerPresenter mPlayerPresenter;

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
        mPlayerPresenter = new PlayerPresenterImpl(this);
        mPlayerPresenter.onCreate();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_music_player, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPlayerPresenter.onViewCreated();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPlayerPresenter.onDestroyView();
    }

    //endregion

    @OnClick({R.id.play_button, R.id.pause_button})
    public void onButtonClick(Button button) {
        switch (button.getId()) {
            case R.id.play_button:
                mPlayerPresenter.onPlayButtonClick(mAudioUrl);
                break;
            case R.id.pause_button:
                mPlayerPresenter.onPauseButtonClick();
                break;
        }
    }

    @Override
    public void setSeekBarMax(int max) {
        mSeekBar.setMax(max);
    }

    @Override
    public void setSeekBarProgress(int progress) {
        mSeekBar.setProgress(progress);
    }

    @Override
    public void setPlayButtonEnabled(boolean enabled) {
        mPlayButton.setEnabled(enabled);
    }

    @Override
    public void setPauseButtonEnabled(boolean enabled) {
        mPauseButton.setEnabled(enabled);
    }
}