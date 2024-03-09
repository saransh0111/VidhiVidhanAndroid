package com.teams.vidhividhan.ui.views.Auth.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codenicely.bebroker.model.defaultModel.MessageResponse
import com.codenicely.bebroker.utils.network.Resource
import com.teams.vidhividhan.data.source.profileRepo.ProfileRepo
import com.teams.vidhividhan.model.authModel.ProfileModel
import com.teams.vidhividhan.model.authModel.VerifyOtpModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepo: ProfileRepo):ViewModel() {

    private val _signupLiveData =MutableLiveData<Resource<ProfileModel>>()
    val signupLiveData get() = _signupLiveData

    fun signup(profileModel: ProfileModel) {
      _signupLiveData.postValue(Resource.loading(null))
      viewModelScope.launch {
          _signupLiveData.postValue(profileRepo.signup(profileModel))
      }
  }

    private val _verifyOtpLiveData = MutableLiveData<Resource<VerifyOtpModel>>()
    val verifyOtpLiveData get() = _verifyOtpLiveData

    fun verifyOtp(verifyOtpModel: VerifyOtpModel){
        _verifyOtpLiveData.postValue(Resource.loading(null))
        viewModelScope.launch{
            _verifyOtpLiveData.postValue(profileRepo.verifyOtp(verifyOtpModel))
        }
    }

    private val _resendOtpLiveData = MutableLiveData<Resource<MessageResponse>>()
    val resendOtpLiveData get() = _resendOtpLiveData

    fun resendOtp(mobile:String){
        _resendOtpLiveData.postValue(Resource.loading(null))
        viewModelScope.launch{
            _resendOtpLiveData.postValue(profileRepo.resendOtp(mobile))
        }
    }


}