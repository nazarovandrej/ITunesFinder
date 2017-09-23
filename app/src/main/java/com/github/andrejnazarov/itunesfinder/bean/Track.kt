package com.github.andrejnazarov.itunesfinder.bean

import android.os.Parcelable
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import kotlinx.android.parcel.Parcelize

/**
 * @author Nazarov on 23.09.17.
 */
@Parcelize
@JsonIgnoreProperties(ignoreUnknown = true)
data class Track(@JsonProperty("artistName") val artistName: String?,
                 @JsonProperty("trackName") val trackName: String?,
                 @JsonProperty("artistViewUrl") val artistViewUrl: String?,
                 @JsonProperty("previewUrl") val trackPreviewUrl: String?,
                 @JsonProperty("artworkUrl100") val coverUrl: String?,
                 @JsonProperty("trackPrice") val trackPrice: Double?) : Parcelable