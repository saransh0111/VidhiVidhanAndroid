package com.teams.vidhividhan.ui.marketplace

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentMarketBinding
import com.teams.vidhividhan.ui.marketplace.marketCategories.TrendingProductFragment
import com.teams.vidhividhan.utils.reusableAdapter.ViewPagerAdapter
import org.koin.android.ext.android.inject

class MarketFragment:Fragment() {

    private var _binding: FragmentMarketBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController
    private var adapter: ViewPagerAdapter? = null
    val mutableList: MutableList<Fragment> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.onBackPressedDispatcher?.addCallback(this,object : OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                activity?.finishAffinity()
            }
        })
        arguments?.let {
        }
    }

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
        NAV_CONTROLLER = navController
        clickAction()
        initTab()
        setOnClickListener()
        initViewPager()
        binding.layoutToolbar.toolbarTitle.text="Market Place"
        Log.d("#@#","market")
//        setApiObserver()
    }

    private fun clickAction(){
        binding.layoutToolbar.cartIc.setOnClickListener {

        }
    }

    private fun initTab(){
        mutableList.clear()

        binding.marketTablayout.addTab(binding.marketTablayout.newTab().setText("Trending"))
        mutableList.add(TrendingProductFragment(navController))

        binding.marketTablayout.addTab(binding.marketTablayout.newTab().setText("Sale"))
        mutableList.add(TrendingProductFragment(navController))

        binding.marketTablayout.addTab(binding.marketTablayout.newTab().setText("Packages"))
        mutableList.add(TrendingProductFragment(navController))
    }

    private fun initViewPager(){
        val fragmentManager: FragmentManager = childFragmentManager
        adapter = ViewPagerAdapter(fragmentManager, lifecycle, mutableList)
        binding.marketViewpager.adapter = adapter
    }

    private fun setOnClickListener(){
        binding.marketTablayout.addOnTabSelectedListener(
            object : TabLayout.OnTabSelectedListener{
                override fun onTabSelected(tab: TabLayout.Tab) {
                   binding.marketViewpager.currentItem=tab.position
                }

                override fun onTabUnselected(tab: TabLayout.Tab?) {

                }

                override fun onTabReselected(tab: TabLayout.Tab?) {

                }
            }
        )

        binding.marketViewpager.registerOnPageChangeCallback(
            object :ViewPager2.OnPageChangeCallback(){
                override fun onPageSelected(position: Int) {
                    binding.marketTablayout.selectTab(binding.marketTablayout.getTabAt(position))
                }
            }
        )

    }

    companion object {
        lateinit var NAV_CONTROLLER: NavController
    }

}