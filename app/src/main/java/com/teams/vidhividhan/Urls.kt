package com.teams.vidhividhan


object Urls {
    @JvmField
    var BASE_URL: String = BuildConfig.BASE_URL
    private const val URL_PREFIX = "/api/"
    val G_URL_BASE = "https://maps.googleapis.com/maps/"

    const val SEND_OTP_URL = URL_PREFIX + "users/send-otp/"
    const val VERIFY_OTP = URL_PREFIX + "users/verify-otp/"
    const val RESEND_OTP_URL = URL_PREFIX + "users/resend-otp/"


    const val USER_PROFILE= URL_PREFIX+"users/get-user-profile/"

    const val ALL_PRODUCT= URL_PREFIX+"market/all-products-list/"
}