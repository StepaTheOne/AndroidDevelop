package com.example.gson

data class PhotoPage (
    //"page":1,"pages":776,"perpage":100,"total":77578,"photo","stat"
    val page:Int,
    val pages:Int,
    val perpage:Int,
    val total:Int,
    val photo:List<Photo>
)