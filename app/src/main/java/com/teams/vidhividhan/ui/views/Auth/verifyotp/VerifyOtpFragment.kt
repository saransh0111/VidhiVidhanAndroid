package com.teams.vidhividhan.ui.views.Auth.verifyotp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.teams.vidhividhan.activities.HomeActivity
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentVerifyOtpBinding
import com.teams.vidhividhan.model.authModel.ProfileModel
import com.teams.vidhividhan.model.authModel.VerifyOtpModel
import com.teams.vidhividhan.ui.viewModel.CommonViewModel
import com.teams.vidhividhan.ui.views.Auth.viewModel.ProfileViewModel
import com.teams.vidhividhan.utils.commonUtils.ViewUtils
import org.koin.android.ext.android.inject

class VerifyOtpFragment:Fragment() {

    private var _binding: FragmentVerifyOtpBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController

    private val profileViewModel : ProfileViewModel by inject()
    private lateinit var commonViewModel : CommonViewModel
    private lateinit var profileModel : ProfileModel
    private var otp:String =""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =  FragmentVerifyOtpBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
        setApiObserver()
        setOnClickListener()
        commonViewModel = ViewModelProvider(requireActivity())[CommonViewModel::class.java]
        commonViewModel.signupDetailsLiveData.observe(viewLifecycleOwner){
            Log.d("#@#","on verify otp"+it)
            profileModel= it
            initViews()
        }

    }

    private fun initViews(){
        binding.mobileNumber.text="Your Number : "+profileModel.mobile
    }

    private fun setOnClickListener(){
        binding.signupSubmitButton.setOnClickListener {
            if (isFormValid()){
                postVerifyOtp()

            }else{
                ViewUtils.showToast("Please Check The OTP")
            }
        }

        binding.resend.setOnClickListener {

        }
    }



    private fun isFormValid():Boolean{
        return binding.signupVerifyOtp.otp.length==4
    }

    private fun postVerifyOtp(){
        otp=binding.signupVerifyOtp.otp.toString()
        profileViewModel.verifyOtp(VerifyOtpModel(profileModel.mobile,otp))
    }

    private fun setApiObserver(){
        profileViewModel.verifyOtpLiveData.observe(viewLifecycleOwner){
            if (!it.isResponseHandled()) {
                when (it.status) {
                    Status.SUCCESS -> {
                        val data = it.data as VerifyOtpModel
                        handleVerifyResponse(data)
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

    private fun handleVerifyResponse(data:VerifyOtpModel){
        sharedPrefs.accessToken=data.result?.token?.access
        sharedPrefs.refreshToken=data.result?.token?.refresh
        sharedPrefs.isLogin=true
        activity?.let {
            it.startActivity(Intent(requireContext(), HomeActivity::class.java))
            it.finish()
        }
    }

}