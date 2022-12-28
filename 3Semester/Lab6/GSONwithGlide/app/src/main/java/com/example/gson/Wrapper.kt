package com.example.gson

import com.google.gson.annotations.SerializedName

data class Wrapper(
    @SerializedName("photos")
    val photoPage:PhotoPage,
    @SerializedName("stat")
    val stat:String
)
