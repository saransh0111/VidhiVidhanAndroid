package com.teams.vidhividhan.ui.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentHomeBinding
import com.teams.vidhividhan.databinding.FragmentProfileBinding
import org.koin.android.ext.android.inject

class ProfileFragment:Fragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        Log.d("#@#","profile")
//        setApiObserver()
    }
}