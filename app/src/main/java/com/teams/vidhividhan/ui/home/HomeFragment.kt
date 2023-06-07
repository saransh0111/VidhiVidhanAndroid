package com.teams.vidhividhan.ui.home

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.ViewUtils
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bumptech.glide.Glide
import com.teams.vidhividhan.R
import com.teams.vidhividhan.data.prefrences.SharedPrefs
import com.teams.vidhividhan.databinding.FragmentHomeBinding
import com.teams.vidhividhan.model.homeModel.NewProductModel
import com.teams.vidhividhan.model.homeModel.TopPanditModel
import com.teams.vidhividhan.model.homeModel.TopProductModel
import com.teams.vidhividhan.model.homeModel.TopShopsModel
import com.teams.vidhividhan.utils.MyViewutils
import com.teams.vidhividhan.utils.reusableAdapter.AdapterCallback
import com.teams.vidhividhan.utils.reusableAdapter.ReuseAdapter
import org.koin.android.ext.android.inject
import java.util.Collections.addAll

class HomeFragment:Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val sharedPrefs: SharedPrefs by inject()
    lateinit var navController: NavController

    private val topProductList:MutableList<TopProductModel> = mutableListOf()
    private val newProductList:MutableList<NewProductModel> = mutableListOf()
    private val topPanditList:MutableList<TopPanditModel> = mutableListOf()
    private val topShopList:MutableList<TopShopsModel> = mutableListOf()

    private lateinit var topProductAdapter : ReuseAdapter<TopProductModel>
    private lateinit var newProductAdapter : ReuseAdapter<NewProductModel>
    private lateinit var topPanditAdapter : ReuseAdapter<TopPanditModel>
    private lateinit var topShopAdapter: ReuseAdapter<TopShopsModel>

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
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(view)

        Log.d("#@#","home")
//        setApiObserver()
        binding.layoutToolbar.toolbarTitle.text="Vidhi Vidhan"
        handleTopProductResponse()
        handleNewProductResponse()
        handleTopPanditResponse()
        handleTopShopResponse()
    }

    private fun handleTopProductResponse(){
        topProductList.addAll(
            arrayListOf(
                TopProductModel("Mitti Diya","100",null),
                TopProductModel("Aanta Diya","100",null),
                TopProductModel("Hawan Lakdi","100",null),
                TopProductModel("Dhoop","100",null),
                TopProductModel("Aarti Set","100",null),
                TopProductModel("Kapoor","100",null),
            )
        )
        setTopProductAdapter()
    }

    private fun setTopProductAdapter(){
        activity.let {
            topProductAdapter=ReuseAdapter<TopProductModel>(it!!.applicationContext)
                .adapterCallback(adapterCallback = topProductAdapterCallback)
                .setLayout(R.layout.item_top_selling_products)
                .isHorizontalView()
                .addData(topProductList)
                .build(binding.topProductsRecycler)
        }
    }

    private var topProductAdapterCallback= object :AdapterCallback<TopProductModel>{
        override fun initComponent(itemView: View, data: TopProductModel, itemIndex: Int) {
            itemView.findViewById<TextView>(R.id.product_name).text = data.productName
            itemView.findViewById<TextView>(R.id.product_price).text = data.productPrice
            if (!data.productThumbnail.isNullOrEmpty()){
                Glide.with(context!!).load(data.productThumbnail).into(itemView.findViewById<ImageView>(R.id.product_image))
            }
        }

        override fun onItemClicked(itemView: View, data: TopProductModel, itemIndex: Int) {
            MyViewutils.showToast(context,"will be implemented later")
        }
    }

    private fun handleNewProductResponse(){

        newProductList.addAll(
            arrayListOf(
                NewProductModel("Digital Photoframe","1000",null),
                NewProductModel("aarti set","1000",null),
                NewProductModel("Murti made of ceremic ","1000",null),
                NewProductModel("Murti made of gold ","1000",null),
                NewProductModel("Murti made of mud ","1000",null),
            )
        )
        setNewProductAdapter()
    }
    private fun setNewProductAdapter()
    {
        activity.let {
            newProductAdapter=ReuseAdapter<NewProductModel>(it!!.applicationContext)
                .adapterCallback(adapterCallback = newProductAdapterCallback)
                .setLayout(R.layout.item_top_selling_products)
                .isHorizontalView()
                .addData(newProductList)
                .build(binding.newProductsRecycler)
        }
    }

    private var newProductAdapterCallback= object : AdapterCallback<NewProductModel>{
        override fun initComponent(itemView: View, data: NewProductModel, itemIndex: Int) {
            itemView.findViewById<TextView>(R.id.product_name).text = data.productName
            itemView.findViewById<TextView>(R.id.product_price).text = data.productPrice
            if (!data.productThumbnail.isNullOrEmpty()){
                Glide.with(context!!).load(data.productThumbnail).into(itemView.findViewById<ImageView>(R.id.product_image))
            }
        }

        override fun onItemClicked(itemView: View, data: NewProductModel, itemIndex: Int) {
            MyViewutils.showToast(context,"will be implemented later")
        }
    }


    private fun handleTopPanditResponse(){

        topPanditList.addAll(
            arrayListOf(
              TopPanditModel("Viyas maharaj","100-200",null),
                TopPanditModel("Raju maharaj","100-200",null),
                TopPanditModel("Raju pandit","100-200",null),
                TopPanditModel("siddh pandit","100-200",null),
            )
        )
        setTopPanditAdapter()
    }

    private fun setTopPanditAdapter(){
        activity.let {
            topPanditAdapter=ReuseAdapter<TopPanditModel>(it!!.applicationContext)
                .adapterCallback(adapterCallback = topPanditAdapterCallback)
                .setLayout(R.layout.item_top_maharajs)
                .isHorizontalView()
                .addData(topPanditList)
                .build(binding.topPanditsRecycler)
        }
    }
    private var topPanditAdapterCallback= object : AdapterCallback<TopPanditModel>{

        override fun initComponent(itemView: View, data: TopPanditModel, itemIndex: Int) {
            itemView.findViewById<TextView>(R.id.pandit_name).text= data.panditName
            itemView.findViewById<TextView>(R.id.pandit_price).text= data.panditPrice
            if(!data.panditThumbnail.isNullOrEmpty()){
                Glide.with(context!!).load(data.panditThumbnail).into(itemView.findViewById<ImageView>(R.id.pandit_image))
            }
        }

        override fun onItemClicked(itemView: View, data: TopPanditModel, itemIndex: Int) {
            MyViewutils.showToast(context,"will be implemented later")
        }

    }

    private fun handleTopShopResponse(){
        topShopList.addAll(
            arrayListOf(
                TopShopsModel("shanti kirana","yaha sab milta h","phalahar, diya, sindoor",null),
                TopShopsModel("shanti kirana","yaha sab milta h","phalahar, diya, sindoor",null),
                TopShopsModel("shanti kirana","yaha sab milta h","phalahar, diya, sindoor",null),
                TopShopsModel("shanti kirana","yaha sab milta h","phalahar, diya, sindoor",null),
            )
        )
        setTopShopAdapter()
    }

    private fun setTopShopAdapter(){
        activity.let {
            topShopAdapter = ReuseAdapter<TopShopsModel>(it!!.applicationContext)
                .adapterCallback(adapterCallback = topShopAdapterCallback)
                .setLayout(R.layout.item_shops)
                .isHorizontalView()
                .addData(topShopList)
                .build(binding.topShopRecycler)
        }
    }

    private var topShopAdapterCallback = object : AdapterCallback<TopShopsModel>{
        override fun initComponent(itemView: View, data: TopShopsModel, itemIndex: Int) {

            itemView.findViewById<TextView>(R.id.shop_name).text=data.shopName
            itemView.findViewById<TextView>(R.id.shop_desc).text=data.shopDesc
            itemView.findViewById<TextView>(R.id.shop_specialty).text=data.shopSpecial
            if(!data.shopThumbnail.isNullOrEmpty()){
                Glide.with(context!!).load(data.shopThumbnail).into(itemView.findViewById<ImageView>(R.id.shop_image))
            }
        }

        override fun onItemClicked(itemView: View, data: TopShopsModel, itemIndex: Int) {
            MyViewutils.showToast(context,"will be implemented later")
        }

    }

}