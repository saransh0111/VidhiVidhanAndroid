package com.teams.vidhividhan.data.source.profileRepo

import com.codenicely.bebroker.model.defaultModel.MessageResponse
import com.codenicely.bebroker.utils.network.Resource
import com.codenicely.bebroker.utils.network.ResponseHandler

import com.teams.vidhividhan.data.network.ProfileApi
import com.teams.vidhividhan.model.authModel.ProfileModel
import com.teams.vidhividhan.model.authModel.VerifyOtpModel

class ProfileRepo(private val profileApi: ProfileApi, private val responseHandler: ResponseHandler) {

    suspend fun signup(profileModel: ProfileModel):Resource<ProfileModel>{
        return try {
            responseHandler.handleSuccess(profileApi.sendOtp(profileModel))
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }

    suspend fun verifyOtp(verifyOtpModel: VerifyOtpModel):Resource<VerifyOtpModel>{
        return try {
            responseHandler.handleSuccess(profileApi.verifyOtp(verifyOtpModel))
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }

    suspend fun resendOtp(mobile:String):Resource<MessageResponse>{
        return try {
            responseHandler.handleSuccess(profileApi.resendOtp(mobile))
        }catch (e:Exception){
            responseHandler.handleException(e)
        }
    }

}