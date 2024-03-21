package com.example.valosneakpeek.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class BaseApiResponse<T> {

    @SerializedName("data")
    @Expose
    internal val data:T? = null

}