package com.github.andrejnazarov.itunesfinder.net;

import com.github.andrejnazarov.itunesfinder.bean.TracksResponse;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * @author Nazarov on 23.07.17.
 */

public interface TrackService {
    @POST("search")
    Call<TracksResponse> getTracks(@Query("term") String keyWord);
}
