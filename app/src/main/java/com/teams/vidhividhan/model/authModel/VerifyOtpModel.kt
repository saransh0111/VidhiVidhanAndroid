package com.teams.vidhividhan.model.authModel

import com.google.gson.annotations.SerializedName

data class VerifyOtpModel(
    @SerializedName("mobile")val mobile:String?=null,
    @SerializedName("otp")val otp:String?=null,
    @SerializedName("assess_token")val assessToken:String?=null,
)
