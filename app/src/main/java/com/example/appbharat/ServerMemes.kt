package com.example.appbharat

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
data class ServerMemes(
    @SerializedName("data")
    var `data`: Data? = Data(),
    @SerializedName("success")
    var success: Boolean? = false
) : Parcelable {

    @Parcelize
    data class Data(
        @SerializedName("memes")
        var memes: List<Meme>? = null
    ) : Parcelable {


        @Parcelize
        data class Meme(
            @SerializedName("box_count")
            var boxCount: Int? = 0,
            @SerializedName("height")
            var height: Int? = 0,
            @SerializedName("id")
            var id: String? = "",
            @SerializedName("name")
            var name: String? = "",
            @SerializedName("url")
            var url: String? = "",
            @SerializedName("width")
            var width: Int? = 0
        ) : Parcelable

    }
}