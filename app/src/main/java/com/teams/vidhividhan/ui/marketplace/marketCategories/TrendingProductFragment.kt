package com.teams.vidhividhan.ui.marketplace.marketCategories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import com.teams.vidhividhan.R
import com.teams.vidhividhan.databinding.FragmentTrendingProductBinding
import com.teams.vidhividhan.model.homeModel.TopProductModel
import com.teams.vidhividhan.model.marketplaceModel.MarketPlaceModel
import com.teams.vidhividhan.ui.marketplace.MarketFragment
import com.teams.vidhividhan.utils.reusableAdapter.AdapterCallback
import com.teams.vidhividhan.utils.reusableAdapter.ReuseAdapter

class TrendingProductFragment() : Fragment() {

    constructor(navConttroller: NavController) : this() {
        navController = navConttroller
    }

    private var _binding: FragmentTrendingProductBinding? = null
    private val binding: FragmentTrendingProductBinding get() = _binding!!

    private lateinit var navController: NavController

    private val trendingProductList:MutableList<MarketPlaceModel> = mutableListOf()
    private lateinit var trendingProductAdapter : ReuseAdapter<MarketPlaceModel>

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
        _binding = FragmentTrendingProductBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController= MarketFragment.NAV_CONTROLLER

        handleTrendingProductResponse()
    }

    private fun handleTrendingProductResponse(){
        trendingProductList.addAll(
            arrayListOf(
                MarketPlaceModel("item 1",null,"100","this is desc"),
                MarketPlaceModel("item 2",null,"100","this is desc"),
                MarketPlaceModel("item 3",null,"100","this is desc"),
                MarketPlaceModel("item 4",null,"100","this is desc"),
                MarketPlaceModel("item 5",null,"100","this is desc"),
                MarketPlaceModel("item 6",null,"100","this is desc"),
                MarketPlaceModel("item 7",null,"100","this is desc"),
                MarketPlaceModel("item 8",null,"100","this is desc"),
                MarketPlaceModel("item 9",null,"100","this is desc"),
                MarketPlaceModel("item 10",null,"100","this is desc"),
            )
        )

        setTrendingProductAdapter()
    }

    private fun setTrendingProductAdapter(){
        activity.let {
            trendingProductAdapter=ReuseAdapter<MarketPlaceModel>(it!!.applicationContext)
                .adapterCallback(adapterCallback = trendingProductAdapterCallback)
                .setLayout(R.layout.item_trending_products)
                .isVerticalView()
                .addData(trendingProductList)
                .build(binding.trendingProductRecycler)
        }
    }

    private var trendingProductAdapterCallback =object :AdapterCallback<MarketPlaceModel>{
        override fun initComponent(itemView: View, data: MarketPlaceModel, itemIndex: Int) {
          itemView.findViewById<TextView>(R.id.product_name).text=data.productName
            itemView.findViewById<TextView>(R.id.product_price).text=data.productPrice
            itemView.findViewById<TextView>(R.id.product_desc).text=data.productDesc
            if (!data.productThumbnail.isNullOrEmpty()){
                Glide.with(context!!).load(data.productThumbnail).into(itemView.findViewById<ImageView>(R.id.product_image))
            }
        }

        override fun onItemClicked(itemView: View, data: MarketPlaceModel, itemIndex: Int) {
            TODO("Not yet implemented")
        }

    }

}