package org.irham3.storyapp.data.remote.response

import com.google.gson.annotations.SerializedName
data class StatusResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String
)