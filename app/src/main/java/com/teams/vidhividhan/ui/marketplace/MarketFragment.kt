package com.teams.vidhividhan.ui.marketplace

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentMarketBinding
import com.teams.vidhividhan.databinding.HomeBinding
import org.koin.android.ext.android.inject

class MarketFragment:Fragment() {

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMarketBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        Log.d("#@#","market")
//        setApiObserver()
    }
}