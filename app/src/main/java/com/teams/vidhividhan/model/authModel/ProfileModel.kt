package com.teams.vidhividhan.model.authModel

import com.google.gson.annotations.SerializedName

data class ProfileModel(
    @SerializedName("mobile")val mobile:String?=null,
    @SerializedName("full_name")val fullName:String?=null,
    @SerializedName("address")val address:String?=null,
    @SerializedName("pin_code")val pincode:String?=null
)
