package com.teams.vidhividhan.activities

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.teams.vidhividhan.R
import com.teams.vidhividhan.databinding.ActivityHomeBinding
import com.teams.vidhividhan.databinding.ActivityMainBinding

class HomeActivity:AppCompatActivity() {
    private var _binding: ActivityHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        _binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupNavigationBar()
        Log.d("#@#","homeact")
    }

    override fun onStart() {
        super.onStart()
        navController.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> {
                    binding.btmNav.visibility = View.VISIBLE
                    Log.d("#@#","home click")
                }
                R.id.marketFragment -> {
                    binding.btmNav.visibility = View.VISIBLE
                    Log.d("#@#","market click")
                }
                R.id.panditFragment -> {
                    binding.btmNav.visibility = View.VISIBLE
                }
                R.id.profileFragment-> {
                    binding.btmNav.visibility = View.VISIBLE
                }
                else -> {
                    binding.btmNav.visibility = View.GONE
                    Log.d("NAV_FRAG", navController.currentDestination?.id.toString())
                }
            }
        }
    }

    private fun setupNavigationBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        navController = navHostFragment.navController

        val navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.homeFragment)

        navController.graph = navGraph
        NavigationUI.setupWithNavController(binding.btmNav, navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.nav_bar_menu, menu)
        return true
    }

}