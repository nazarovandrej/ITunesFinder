package com.github.andrejnazarov.itunesfinder.bean;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.List;

/**
 * @author Nazarov on 23.07.17.
 */

public class TracksResponse implements Serializable {

    private int mTrackCount;

    @NonNull
    private List<Track> mTracks;

    public TracksResponse() {
        // Empty constructor needed by Jackson
    }

    @NonNull
    @JsonGetter("results")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public List<Track> getTracks() {
        return mTracks;
    }

    @JsonSetter("results")
    public void setTracks(@NonNull List<Track> tracks) {
        mTracks = tracks;
    }

    @JsonGetter("resultCount")
    public int getTrackCount() {
        return mTrackCount;
    }

    @JsonSetter("resultCount")
    public void setTrackCount(int trackCount) {
        mTrackCount = trackCount;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TracksResponse that = (TracksResponse) o;
        return mTrackCount == that.mTrackCount &&
                Objects.equal(mTracks, that.mTracks);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(mTrackCount, mTracks);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mTrackCount", mTrackCount)
                .add("mTracks", mTracks)
                .toString();
    }
}