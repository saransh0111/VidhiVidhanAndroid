package com.codenicely.bebroker.model.defaultModel

import com.google.gson.annotations.SerializedName

data class MessageResponse(
    @SerializedName("message") val message: String
)
