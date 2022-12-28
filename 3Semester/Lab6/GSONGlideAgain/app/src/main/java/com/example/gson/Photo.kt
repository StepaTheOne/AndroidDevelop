package com.example.gson

data class Photo(
    val id:String,
    val owner:String,
    //"id":"52579587150","owner":"142183182@N03","secret":"89d1b0f768","server":"65535","farm":66,"title":"Clarence - Lion- Wingham Wildlife Park- UK",
    // "ispublic":1,"isfriend":0,"isfamily":0
    val secret: String,
    val server:Int,
    val farm:Int,
    val title:String,
    val ispublic:Int,
    val isfriend:Int,
    val isfamily:Int
)