package com.teams.vidhividhan.activities

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.Menu
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.teams.vidhividhan.R
import com.teams.vidhividhan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(){

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    private lateinit var navController: NavController
//    var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        setupNavigation()

    }

//    override fun onStart() {
//        super.onStart()
//        navController.addOnDestinationChangedListener { controller, destination, arguments ->
//            when (destination.id) {
//                R.id.homeFragment -> {
//                    binding.btmNav.visibility = View.VISIBLE
//                }
//                R.id.marketFragment -> {
//                    binding.btmNav.visibility = View.VISIBLE
//                }
//                R.id.panditFragment -> {
//                    binding.btmNav.visibility = View.VISIBLE
//                }
//                R.id.profileFragment-> {
//                    binding.btmNav.visibility = View.VISIBLE
//                }
//                else -> {
//                    binding.btmNav.visibility = View.GONE
//                    Log.d("NAV_FRAG", navController.currentDestination?.id.toString())
//                }
//            }
//        }
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.nav_bar_menu, menu)
//        return true
//    }

    private fun setupNavigation()
    {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.my_nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController
        val navGraph = navController.navInflater.inflate(R.navigation.main_nav_graph)
        navGraph.setStartDestination(R.id.splashScreenFragment)
        navController.graph=navGraph
//        NavigationUI.setupWithNavController(binding.btmNav, navController)
    }



}