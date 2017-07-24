package com.github.andrejnazarov.itunesfinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatSeekBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment to show Music Player
 *
 * @author Nazarov on 24.07.17
 */
public class MusicPlayerFragment extends Fragment {

    private static final String EXTRA_AUDIO_URL = "extra_audio_url";

    @BindView(R.id.play_button)
    Button mPlayButton;

    @BindView(R.id.pause_button)
    Button mPauseButton;

    @BindView(R.id.seek_bar)
    AppCompatSeekBar mSeekBar;

    private String mAudioUrl;

    public MusicPlayerFragment() {
        // Required empty public constructor
    }

    public static MusicPlayerFragment newInstance(String audioUrl) {
        MusicPlayerFragment fragment = new MusicPlayerFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_AUDIO_URL, audioUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mAudioUrl = getArguments().getString(EXTRA_AUDIO_URL);
        }
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
        // TODO: 24.07.17 handle player logics
    }
}