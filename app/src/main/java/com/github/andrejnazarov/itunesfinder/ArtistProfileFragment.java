package com.github.andrejnazarov.itunesfinder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Fragment to show artist profile view
 *
 * @author Nazarov on 24.07.17
 */
public class ArtistProfileFragment extends Fragment {

    private static final String EXTRA_PROFILE_URL = "extra_profile_url";

    @BindView(R.id.web_view)
    WebView mWebView;

    private String mProfileUrl;

    public static ArtistProfileFragment newInstance(String profileUrl) {
        ArtistProfileFragment fragment = new ArtistProfileFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_PROFILE_URL, profileUrl);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mProfileUrl = getArguments().getString(EXTRA_PROFILE_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_artist_profile, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mWebView.loadUrl(mProfileUrl);
        mWebView.setWebViewClient(new WebViewClient());
    }
}