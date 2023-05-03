package com.teams.vidhividhan.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.teams.vidhividhan.activities.HomeActivity
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentSplashScreenBinding
import org.koin.android.ext.android.inject

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class SplashScreenFragment:Fragment() {
    private var _binding: FragmentSplashScreenBinding? = null
    private val binding get() = _binding!!
    private var param1: String? = null
    private var param2: String? = null
    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSplashScreenBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)
//        setupUI()
//        setApiObserver()
        Log.d("#@#","splash")
        Handler().postDelayed({
            navigate()
        }, 2000)


        setApiObserver()

    }

    private fun navigate(){
        activity?.let {
            it.startActivity(Intent(requireContext(), HomeActivity::class.java))
            it.finish()
        }
//        navController.navigate(R.id.action_splashScreenFragment_to_homeFragment)
    }
    private fun setApiObserver(){

    }

}