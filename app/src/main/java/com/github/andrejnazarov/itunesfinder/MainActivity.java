package com.github.andrejnazarov.itunesfinder;

import android.app.SearchManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;
import com.github.andrejnazarov.itunesfinder.net.ApiClient;
import com.github.andrejnazarov.itunesfinder.net.TrackService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainFragment.OnFragmentInteractionListener {

    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initToolbar();
        createSearchView();
    }

    @Override
    public void onFragmentInteraction(String string) {
        Toast.makeText(getApplicationContext(), "here we are " + string, Toast.LENGTH_LONG).show();
    }

    private void createSearchView() {
        SearchView searchView = (SearchView) findViewById(R.id.search);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getDataFromServer(query);
                //startFragment(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    private void initToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
    }

    private void getDataFromServer(String query) {
        TrackService service = ApiClient.getClient().create(TrackService.class);
        Call<TracksResponse> responseCall = service.getTracks(query);

        responseCall.enqueue(new Callback<TracksResponse>() {
            @Override
            public void onResponse(@NonNull Call<TracksResponse> call, @NonNull Response<TracksResponse> response) {
                TracksResponse tracksResponse = response.body();
                if (tracksResponse != null) {
                    Log.d("MainActivity", tracksResponse.toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<TracksResponse> call, @NonNull Throwable t) {
                Log.e("MainActivity", "failure " + t.getMessage());
            }
        });
    }

    private void startFragment(String query) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, MainFragment.newInstance(query, null))
                .addToBackStack(null)
                .commit();
    }


}
