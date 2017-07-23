package com.github.andrejnazarov.itunesfinder.bean;

import android.support.annotation.NonNull;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.google.common.base.Objects;

import java.io.Serializable;

/**
 * @author Nazarov on 23.07.17.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Track implements Serializable {

    @NonNull
    private String mArtistName;

    @NonNull
    private String mTrackName;

    @NonNull
    private String mArtistViewUrl;

    @NonNull
    private String mTrackPreviewUrl;

    @NonNull
    private String mCoverUrl;

    private double mTrackPrice;

    public Track() {
        // Empty constructor needed by Jackson
    }

    @NonNull
    @JsonGetter("artistName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getArtistName() {
        return mArtistName;
    }

    @JsonSetter("artistName")
    public void setArtistName(@NonNull String artistName) {
        mArtistName = artistName;
    }

    @NonNull
    @JsonGetter("trackName")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTrackName() {
        return mTrackName;
    }

    @JsonSetter("trackName")
    public void setTrackName(@NonNull String trackName) {
        mTrackName = trackName;
    }

    @NonNull
    @JsonGetter("artistViewUrl")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getArtistViewUrl() {
        return mArtistViewUrl;
    }

    @JsonSetter("artistViewUrl")
    public void setArtistViewUrl(@NonNull String artistViewUrl) {
        mArtistViewUrl = artistViewUrl;
    }

    @NonNull
    @JsonGetter("previewUrl")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getTrackPreviewUrl() {
        return mTrackPreviewUrl;
    }

    @JsonSetter("previewUrl")
    public void setTrackPreviewUrl(@NonNull String trackPreviewUrl) {
        mTrackPreviewUrl = trackPreviewUrl;
    }

    @NonNull
    @JsonGetter("artworkUrl100")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public String getCoverUrl() {
        return mCoverUrl;
    }

    @JsonSetter("artworkUrl100")
    public void setCoverUrl(@NonNull String coverUrl) {
        mCoverUrl = coverUrl;
    }

    @JsonGetter("trackPrice")
    public double getTrackPrice() {
        return mTrackPrice;
    }

    @JsonSetter("trackPrice")
    public void setTrackPrice(double trackPrice) {
        mTrackPrice = trackPrice;
    }

    @JsonIgnore
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Track track = (Track) o;
        return Double.compare(track.mTrackPrice, mTrackPrice) == 0 &&
                Objects.equal(mArtistName, track.mArtistName) &&
                Objects.equal(mTrackName, track.mTrackName) &&
                Objects.equal(mArtistViewUrl, track.mArtistViewUrl) &&
                Objects.equal(mTrackPreviewUrl, track.mTrackPreviewUrl) &&
                Objects.equal(mCoverUrl, track.mCoverUrl);
    }

    @JsonIgnore
    @Override
    public int hashCode() {
        return Objects.hashCode(
                mArtistName,
                mTrackName,
                mArtistViewUrl,
                mTrackPreviewUrl,
                mCoverUrl,
                mTrackPrice);
    }

    @JsonIgnore
    @Override
    public String toString() {
        return Objects.toStringHelper(this)
                .add("mArtistName", mArtistName)
                .add("mTrackName", mTrackName)
                .add("mArtistViewUrl", mArtistViewUrl)
                .add("mTrackPreviewUrl", mTrackPreviewUrl)
                .add("mCoverUrl", mCoverUrl)
                .add("mTrackPrice", mTrackPrice)
                .toString();
    }
}