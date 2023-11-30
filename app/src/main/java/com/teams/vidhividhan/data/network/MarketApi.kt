package com.teams.vidhividhan.data.network

import com.teams.vidhividhan.Urls
import com.teams.vidhividhan.model.marketplaceModel.MarketPlaceModel
import retrofit2.http.GET

interface MarketApi {

    @GET(Urls.ALL_PRODUCT)
    suspend fun getAllProductList():MarketPlaceModel
}