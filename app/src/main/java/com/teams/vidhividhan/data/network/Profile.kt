package com.teams.vidhividhan.data.network

import com.codenicely.bebroker.model.defaultModel.MessageResponse
import com.teams.vidhividhan.Urls
import com.teams.vidhividhan.model.authModel.ProfileModel
import com.teams.vidhividhan.model.authModel.VerifyOtpModel
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.POST

interface ProfileApi {

    @POST(Urls.SEND_OTP_URL)
    suspend fun sendOtp(@Body profileModel: ProfileModel):ProfileModel

    @POST(Urls.VERIFY_OTP)
    suspend fun verifyOtp(@Body verifyOtpModel: VerifyOtpModel):VerifyOtpModel

    @POST(Urls.RESEND_OTP_URL)
    suspend fun resendOtp(@Field("mobile")mobile:String):MessageResponse

    @GET(Urls.USER_PROFILE)
    suspend fun getUserProfile():MessageResponse
}