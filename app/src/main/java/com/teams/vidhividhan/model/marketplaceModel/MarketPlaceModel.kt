package com.teams.vidhividhan.model.marketplaceModel

import com.google.gson.annotations.SerializedName

data class MarketPlaceModel(
    @SerializedName("product_name")val productName:String?=null,
    @SerializedName("product_image")val productThumbnail:String?=null,
    @SerializedName("price")val productPrice:String?=null,
    val productDesc:String?=null,
    @SerializedName("category")val productCategory: String?=null,
    @SerializedName("create")val productCreated:String?=null
)