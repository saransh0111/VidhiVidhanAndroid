package com.teams.vidhividhan.ui.viewModel

import androidx.lifecycle.ViewModel
import com.codenicely.bebroker.utils.liveDataWrapper.SingleLiveEvent
import com.teams.vidhividhan.model.authModel.ProfileModel

class CommonViewModel:ViewModel() {

    val signupDetailsLiveData : SingleLiveEvent<ProfileModel> = SingleLiveEvent()
}