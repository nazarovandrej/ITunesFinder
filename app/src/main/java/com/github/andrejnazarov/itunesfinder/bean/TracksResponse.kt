package com.github.andrejnazarov.itunesfinder.bean

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * @author Nazarov on 23.09.17.
 */
@Parcelize
data class TracksResponse(@JsonProperty("results") var tracks : List<Track>?,
                          @JsonProperty("resultCount") var trackCount : Int?) : Parcelable