package com.teams.vidhividhan.ui.views .Auth.signup

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isEmpty
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.teams.vidhividhan.R
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentSignupBinding
import com.teams.vidhividhan.model.authModel.ProfileModel
import com.teams.vidhividhan.ui.viewModel.CommonViewModel
import com.teams.vidhividhan.ui.views.Auth.viewModel.ProfileViewModel
import com.teams.vidhividhan.utils.commonUtils.ViewUtils
import org.koin.android.ext.android.inject

class SignUpFragment:Fragment() {
    private var _binding: FragmentSignupBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController

    private val profileViewModel :ProfileViewModel by inject()
    private val commonViewModel :CommonViewModel by activityViewModels()
    private lateinit var profileModel :ProfileModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSignupBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        setApiObserver()
        setOnClickListener()
    }

    private fun setApiObserver(){
        profileViewModel.signupLiveData.observe(viewLifecycleOwner){
            if (!it.isResponseHandled()) {
                when (it.status) {
                    Status.SUCCESS -> {
                        val data = it.data as ProfileModel
                        handleSignupResponse(data)
                    }
                    Status.LOADING -> {

                    }
                    Status.ERROR -> {
                        ViewUtils.showToast(
                            requireContext(),
                            it.message + ""
                        )
                    }
                }
            }
        }
    }

    private fun setOnClickListener(){
        binding.signupSubmitButton.setOnClickListener {
            if (isFormValid()){
                postForm()
            }else{
               ViewUtils.showToast("Please Check the data above")
            }
        }
    }

    private fun isFormValid():Boolean{
        if (binding.signupName.isEmpty()){
            binding.signupName.error="Required"
            return false
        }
        if (binding.signupMobile.editText?.text.toString().length<10){
            binding.signupMobile.error="Required"
            return false
        }
        if (binding.signupAddress.isEmpty()){
            binding.signupAddress.error="Required"
            return false
        }
        if (binding.signupPincode.isEmpty()){
            binding.signupPincode.error="Required"
            return false
        }
        return true
    }

    private fun postForm(){
        profileModel=ProfileModel(
            binding.signupMobile.editText?.text.toString(),
            binding.signupName.editText?.text.toString(),
            binding.signupAddress.editText?.text.toString(),
            binding.signupPincode.editText?.text.toString()
        )
        profileViewModel.signup(
            profileModel
        )
    }

    private fun handleSignupResponse(data :ProfileModel){
        commonViewModel.signupDetailsLiveData.postValue(ProfileModel( binding.signupMobile.editText?.text.toString(),data.fullName,data.address,data.pincode))
        navController.navigate(R.id.action_signUpFragment_to_verifyOtpFragment)
    }

}