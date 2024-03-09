package com.teams.vidhividhan.model.authModel

import com.google.gson.annotations.SerializedName

data class VerifyOtpModel(

    @SerializedName("mobile")val mobile:String?=null,
    @SerializedName("otp")val otp:String?=null,
    @SerializedName("assess_token")val assessToken:String?=null,
    @SerializedName("detail" ) var detail : String? = null,
    @SerializedName("result" ) var result : Result? = Result()
)
data class Result(
    @SerializedName("token" ) var token : Token? = Token()
)

data class Token(
    @SerializedName("refresh" ) var refresh : String? = null,
    @SerializedName("access"  ) var access  : String? = null
)